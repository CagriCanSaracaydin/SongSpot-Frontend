package com.example.myapplication;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongDetailsActivity extends AppCompatActivity {
    private TextView songName, artistName, songYear, songGenre;
    private RatingBar averageRatingBar, userRatingBar;
    private EditText commentInput, userNameInput;
    private Button submitComment, viewComments;
    private String songId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_details);

        songId = getIntent().getStringExtra("SONG_ID");
        songName = findViewById(R.id.songName);
        artistName = findViewById(R.id.artistName);
        songYear = findViewById(R.id.songYear);
        songGenre = findViewById(R.id.songGenre);
        averageRatingBar = findViewById(R.id.averageRatingBar);
        userRatingBar = findViewById(R.id.userRatingBar);
        commentInput = findViewById(R.id.commentInput);
        submitComment = findViewById(R.id.submitComment);
        viewComments = findViewById(R.id.viewComments);
        userNameInput = findViewById(R.id.userNameInput);

        fetchSongDetails(songId);
        fetchAndDisplayAverageRating(songId);
        initRatingBar();

        submitComment.setOnClickListener(v -> {
            String userName = userNameInput.getText().toString().trim();
            String commentText = commentInput.getText().toString().trim();
            if (!userName.isEmpty() && !commentText.isEmpty()) {
                postComment(userName, commentText);
            } else {
                Toast.makeText(SongDetailsActivity.this, "Both name and comment must be filled out.", Toast.LENGTH_SHORT).show();
            }
        });

        viewComments.setOnClickListener(v -> viewComments());
    }

    private void fetchSongDetails(String id) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<Song> call = apiService.getSongById(id);
        call.enqueue(new Callback<Song>() {
            @Override
            public void onResponse(Call<Song> call, Response<Song> response) {
                if (response.isSuccessful()) {
                    Song song = response.body();
                    if (song != null) {
                        songName.setText(song.getName());
                        artistName.setText(song.getArtist());
                        songYear.setText(String.valueOf(song.getYear()));
                        songGenre.setText(song.getGenre());
                    }
                }
            }

            @Override
            public void onFailure(Call<Song> call, Throwable t) {
                Toast.makeText(SongDetailsActivity.this, "Failed to fetch song details: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initRatingBar() {
        userRatingBar.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            if (fromUser) {
                postRating(songId, rating);
            }
        });
    }

    private void postRating(String songId, float rating) {
        String userName = "Username"; // Replace with actual user name logic
        String currentTimeStamp = getCurrentTimeStamp();

        Rating ratingObj = new Rating(songId, userName, (int) rating, currentTimeStamp);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResult> call = apiService.postRating(ratingObj);
        call.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SongDetailsActivity.this, "Rating submitted successfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SongDetailsActivity.this, "Failed to submit rating", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Toast.makeText(SongDetailsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void fetchAndDisplayAverageRating(String songId) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Rating>> call = apiService.getRatingsBySongId(songId);
        call.enqueue(new Callback<List<Rating>>() {
            @Override
            public void onResponse(Call<List<Rating>> call, Response<List<Rating>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Rating> ratings = response.body();
                    int sum = 0;
                    for (Rating rating : ratings) {
                        sum += rating.getRating();
                    }
                    int average = ratings.isEmpty() ? 0 : sum / ratings.size();
                    averageRatingBar.setRating(average);
                } else {
                    Toast.makeText(SongDetailsActivity.this, "Failed to fetch ratings: " + response.code() + " " + response.message(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Rating>> call, Throwable t) {
                Toast.makeText(SongDetailsActivity.this, "Network error: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void postComment(String userName, String commentText) {
        Comment comment = new Comment(songId, userName, commentText, getCurrentTimeStamp());
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<ApiResult> call = apiService.postComment(comment);
        call.enqueue(new Callback<ApiResult>() {
            @Override
            public void onResponse(Call<ApiResult> call, Response<ApiResult> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SongDetailsActivity.this, "Comment successfully posted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SongDetailsActivity.this, "Failed to post comment", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResult> call, Throwable t) {
                Toast.makeText(SongDetailsActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewComments() {
        Intent intent = new Intent(this, CommentsActivity.class);
        intent.putExtra("SONG_ID", songId);
        startActivity(intent);
    }

    private String getCurrentTimeStamp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
}
