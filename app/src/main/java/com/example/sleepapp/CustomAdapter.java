package com.example.sleepapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private List<ListItem> items;

    // Constructor
    public CustomAdapter(Context context, List<ListItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.edulistview, parent, false);
        }

        ListItem currentItem = (ListItem) getItem(position);


        TextView titleTextView = convertView.findViewById(R.id.list_item_title);
        TextView subtitleTextView = convertView.findViewById(R.id.list_item_subtitle);

        // Set data
        titleTextView.setText(currentItem.getTitle());
        subtitleTextView.setText(currentItem.getSubtitle());

        // Apply a different background gradient based on the position
        switch (position) {
            case 0:
                convertView.setBackgroundResource(R.drawable.gradient1);
                convertView.setOnClickListener(v -> openLink(currentItem.getLink()));
                break;
            case 1:
                convertView.setBackgroundResource(R.drawable.gradient2);
                convertView.setOnClickListener(v -> openLink(currentItem.getLink()));
                break;
            case 2:
                convertView.setBackgroundResource(R.drawable.gradient3);
                convertView.setOnClickListener(v -> openLink(currentItem.getLink()));
                break;
            case 3:
                convertView.setBackgroundResource(R.drawable.gradient4);
                convertView.setOnClickListener(v -> openLink(currentItem.getLink()));
                break;
            default:
                convertView.setBackgroundResource(R.drawable.gradient1);  // Default
        }
        convertView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(currentItem.getLink()));
            context.startActivity(intent);
        });


        return convertView;
    }

    private void openLink(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }
}
