package com.example.myapplication;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Date;
import java.util.List;
import java.util.Locale;

// Adapter class for handling comment data in a ListView
public class CommentsAdapter extends BaseAdapter {
    private Context context; // Context for accessing resources and LayoutInflater
    private List<Comment> comments; // List of comments to be displayed

    // Constructor for the adapter, takes a context and a list of comments
    public CommentsAdapter(Context context, List<Comment> comments) {
        this.context = context; // Initialize the context
        this.comments = comments; // Initialize the list of comments
    }

    // Returns the total number of items in the data set held by the adapter
    @Override
    public int getCount() {
        return comments.size();
    }

    // Returns the data item at the specified position
    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    // Returns the row id of the item at the specified position
    @Override
    public long getItemId(int position) {
        return position;
    }

    // Provides a view for an adapter view (ListView) at the specified position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // If the convertView is null, inflate the comment_item layout
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        }

        // Get references to the TextViews in the comment_item layout
        TextView commentText = convertView.findViewById(R.id.commentText);
        TextView username = convertView.findViewById(R.id.username);
        TextView timeStamp = convertView.findViewById(R.id.timeStamp);

        // Get the comment at the specified position
        Comment comment = comments.get(position);
        // Set the comment text and username in the respective TextViews
        commentText.setText(comment.getComment());
        username.setText(comment.getUsername());

        // Format the timestamp before setting it
        try {
            // Parse the timestamp from ISO
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            Date date = isoFormat.parse(comment.getTimeStamp());
            // Format the Date object
            SimpleDateFormat desiredFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
            String formattedDate = desiredFormat.format(date);
            // Set the formatted date in the timeStamp TextView
            timeStamp.setText(formattedDate);
        } catch (Exception e) {
            // If an error occurs during formatting, set the original timestamp
            timeStamp.setText(comment.getTimeStamp());
        }

        return convertView; // Return the view for the current row
    }
}
