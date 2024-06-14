package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    // Define the categories as a constant
    private static final List<String> CATEGORIES = Arrays.asList(
            "Pop", "Rock", "Hip-Hop", "R&B", "Electronic", "Folk",
            "Country", "Jazz", "World Music", "Reggae"
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRecyclerView();
    }

    /**
     * Sets up the RecyclerView with a LinearLayoutManager and CategoryAdapter.
     * Handles potential exceptions that could be thrown during setup.
     */
    private void setupRecyclerView() {
        try {
            recyclerView = findViewById(R.id.recyclerViewCategories);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            adapter = new CategoryAdapter(this, CATEGORIES);
            recyclerView.setAdapter(adapter);
        } catch (Exception e) {
            // Log the exception and display a user-friendly error message
            Log.e("MainActivity", "Error setting up RecyclerView", e);
            Toast.makeText(this, "An error occurred while setting up the view. Please try again.", Toast.LENGTH_SHORT).show();
        }
    }
}