package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Activity class for displaying a list of songs
public class SongListActivity extends AppCompatActivity {
    private RecyclerView recyclerView; // RecyclerView to display the list of songs
    private SongAdapter adapter; // Adapter for managing the song data in the RecyclerView
    private String category; // The category of songs to be displayed

    // Called when the activity is first created
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list); // Set the layout for this activity

        // Get the category passed from the previous activity
        String category = getIntent().getStringExtra("CATEGORY");
        Log.d("SongListActivity", "Category received: " + category); // Log the received category

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerViewSongs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)); // Set the layout manager for the RecyclerView
        fetchSongs(category); // Fetch the songs for the given category
    }

    // Fetches songs for the given category from the API
    private void fetchSongs(String category) {
        // Create an instance of the ApiService
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        // Make the API call to get the songs by genre
        Call<List<Song>> call = apiService.getSongsByGenre(category);
        call.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Log the success of the API call
                    Log.d("API Call", "Songs fetched successfully");
                    // Initialize the adapter with the fetched songs and set it to the RecyclerView
                    adapter = new SongAdapter(SongListActivity.this, response.body());
                    recyclerView.setAdapter(adapter);
                } else {
                    // Log if the response is not successful or empty
                    Log.d("API Call", "Response not successful or empty");
                }
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                // Log the failure of the API call
                Log.d("API Call", "Failed to fetch songs: " + t.getMessage());
            }
        });
    }
}
