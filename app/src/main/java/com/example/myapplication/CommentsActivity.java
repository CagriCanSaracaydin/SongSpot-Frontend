package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

// Activity class for displaying comments
public class CommentsActivity extends AppCompatActivity {
    private ListView commentsList; // ListView to display the list of comments
    private CommentsAdapter adapter; // Adapter for managing the comment data in the ListView
    private String songId; // ID of the song for which comments are to be displayed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments); // Set the layout for this activity

        // Retrieve the song ID from the intent extras
        songId = getIntent().getStringExtra("SONG_ID");

        // Initialize the ListView
        commentsList = findViewById(R.id.commentsList);

        // Set up the empty view for the ListView
        TextView emptyTextView = findViewById(R.id.emptyTextView);
        commentsList.setEmptyView(emptyTextView);

        // Fetch and display the comments
        fetchComments();
    }

    // Fetches and displays the comments for the song
    private void fetchComments() {
        // Create an instance of the ApiService
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        // Make the API call to get all comments for the song
        Call<List<Comment>> call = apiService.getAllCommentsForSong(songId);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Initialize the adapter with the fetched comments and set it to the ListView
                    adapter = new CommentsAdapter(CommentsActivity.this, response.body());
                    commentsList.setAdapter(adapter);

                    // Check if the list is empty and update the empty view visibility
                    if (adapter.getCount() == 0) {
                        findViewById(R.id.emptyTextView).setVisibility(View.VISIBLE);
                    } else {
                        findViewById(R.id.emptyTextView).setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                // Log the failure
                Log.e("CommentsActivity", "Failed to fetch comments", t);

                // Display an error message to the user
                Toast.makeText(CommentsActivity.this, "Failed to load comments", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
