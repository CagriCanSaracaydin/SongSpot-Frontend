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

public class CommentsAdapter extends BaseAdapter {
    private Context context;
    private List<Comment> comments;

    public CommentsAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false);
        }
        TextView commentText = convertView.findViewById(R.id.commentText);
        TextView username = convertView.findViewById(R.id.username);
        TextView timeStamp = convertView.findViewById(R.id.timeStamp);

        Comment comment = comments.get(position);
        commentText.setText(comment.getComment());
        username.setText(comment.getUsername());

        // Format the timestamp before setting it
        try {
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault());
            Date date = isoFormat.parse(comment.getTimeStamp());
            SimpleDateFormat desiredFormat = new SimpleDateFormat("MMM dd, yyyy HH:mm", Locale.getDefault());
            String formattedDate = desiredFormat.format(date);
            timeStamp.setText(formattedDate);
        } catch (Exception e) {
            timeStamp.setText(comment.getTimeStamp());
        }

        return convertView;
    }
}
