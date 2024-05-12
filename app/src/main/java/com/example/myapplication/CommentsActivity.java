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

public class CommentsActivity extends AppCompatActivity {
    private ListView commentsList;
    private CommentsAdapter adapter;
    private String songId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);

        songId = getIntent().getStringExtra("SONG_ID");
        commentsList = findViewById(R.id.commentsList);
        TextView emptyTextView = findViewById(R.id.emptyTextView); // Assume this TextView is added to your layout
        commentsList.setEmptyView(emptyTextView); // Set the empty view for the ListView

        fetchComments();
    }

    private void fetchComments() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<List<Comment>> call = apiService.getAllCommentsForSong(songId);
        call.enqueue(new Callback<List<Comment>>() {
            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    adapter = new CommentsAdapter(CommentsActivity.this, response.body());
                    commentsList.setAdapter(adapter);
                    // Check if the list is empty and update the empty view text
                    if (adapter.getCount() == 0) {
                        findViewById(R.id.emptyTextView).setVisibility(View.VISIBLE);
                    } else {
                        findViewById(R.id.emptyTextView).setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t) {
                // Handle the failure case
                Log.e("CommentsActivity", "Failed to fetch comments", t);

                // Display an error message to the user
                Toast.makeText(CommentsActivity.this, "Failed to load comments", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
