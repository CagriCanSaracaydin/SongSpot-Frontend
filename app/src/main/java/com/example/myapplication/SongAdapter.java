package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Adapter class for handling song data in a RecyclerView
public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {
    private List<Song> songs; // List of songs to be displayed
    private LayoutInflater inflater; // LayoutInflater to inflate the song item layout

    // Constructor for the adapter
    public SongAdapter(Context context, List<Song> songs) {
        this.inflater = LayoutInflater.from(context); // Initialize the LayoutInflater
        this.songs = songs; // Initialize the list of songs
    }

    // Called when RecyclerView needs a new ViewHolder of the given type to represent an item
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the song_item layout
        View view = inflater.inflate(R.layout.song_item, parent, false);
        return new ViewHolder(view); // Return a new ViewHolder instance
    }

    // Called by RecyclerView to display the data at the specified position
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Get the song at the specified position
        Song song = songs.get(position);
        // Set the song name and artist name in the respective TextViews
        holder.songName.setText(song.getName());
        holder.artistName.setText(song.getArtist());
    }

    // Returns the total number of items in the data set held by the adapter
    @Override
    public int getItemCount() {
        return songs.size();
    }

    // ViewHolder class to hold the song item views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView songName; // TextView for displaying the song name
        public TextView artistName; // TextView for displaying the artist name

        // Constructor for the ViewHolder
        public ViewHolder(View itemView) {
            super(itemView);
            // Initialize the TextViews
            songName = itemView.findViewById(R.id.songName);
            artistName = itemView.findViewById(R.id.artistName);
            // Set the OnClickListener for the item view
            itemView.setOnClickListener(this);
        }

        // Called when the item view is clicked
        @Override
        public void onClick(View v) {
            int position = getBindingAdapterPosition(); // Get the position of the clicked item
            if (position != RecyclerView.NO_POSITION) {
                // Get the song at the clicked position
                Song song = songs.get(position);
                // Create an Intent to start the SongDetailsActivity
                Intent intent = new Intent(v.getContext(), SongDetailsActivity.class);
                // Pass the song ID to the SongDetailsActivity
                intent.putExtra("SONG_ID", song.getId());
                // Start the SongDetailsActivity
                v.getContext().startActivity(intent);
            }
        }
    }
}
