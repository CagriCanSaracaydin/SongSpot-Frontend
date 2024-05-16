package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// Adapter class for handling category data in a RecyclerView
public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private List<String> categories; // List of categories to be displayed
    private LayoutInflater inflater; // LayoutInflater to inflate the category item layout

    // Constructor for the adapter, takes a context and a list of categories
    public CategoryAdapter(Context context, List<String> categories) {
        this.inflater = LayoutInflater.from(context); // Initialize the LayoutInflater
        this.categories = categories; // Initialize the list of categories
    }

    // Called when RecyclerView needs a new ViewHolder of the given type to represent an item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the category_item layout
        View view = inflater.inflate(R.layout.category_item, parent, false);
        return new ViewHolder(view); // Return a new ViewHolder instance
    }

    // Called by RecyclerView to display the data at the specified position
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the category at the specified position
        String category = categories.get(position);
        // Set the category name in the TextView
        holder.categoryName.setText(category);
    }

    // Returns the total number of items in the data set held by the adapter
    @Override
    public int getItemCount() {
        return categories.size();
    }

    // ViewHolder class to hold the category item views
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView categoryName; // TextView for displaying the category name

        // Constructor for the ViewHolder
        public ViewHolder(View itemView) {
            super(itemView);
            // Initialize the TextView
            categoryName = itemView.findViewById(R.id.categoryName);
            // Set the OnClickListener for the item view
            itemView.setOnClickListener(this);
        }

        // Called when the item view is clicked
        @Override
        public void onClick(View view) {
            int position = getBindingAdapterPosition(); // Get the position of the clicked item
            if (position != RecyclerView.NO_POSITION) {
                // Get the category at the clicked position
                String category = categories.get(position);
                // Create an Intent to start the SongListActivity
                Intent intent = new Intent(view.getContext(), SongListActivity.class);
                // Pass the category to the SongListActivity
                intent.putExtra("CATEGORY", category);
                // Start the SongListActivity
                view.getContext().startActivity(intent);
            }
        }
    }
}
