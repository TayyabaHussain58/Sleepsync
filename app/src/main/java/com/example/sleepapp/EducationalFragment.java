package com.example.sleepapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class EducationalFragment extends Fragment {
    private ListView listView;  // Correctly define listView as ListView
    private CustomAdapter adapter;

    public EducationalFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_educational, container, false);

        listView = view.findViewById(R.id.edulist); // Use the inflated view to find the ListView

        List<ListItem> items = new ArrayList<>();
        items.add(new ListItem( "Importance of Sleep", "Learn about the importance of sleep", "https://newsinhealth.nih.gov/2021/04/good-sleep-good-health#:~:text=But%20sleep%20is%20as%20important,stroke%20to%20obesity%20and%20dementia."));
        items.add(new ListItem("Exercises for Sleep", "Check out exercises that help you sleep better", "https://example.com/exercises-for-sleep"));
        items.add(new ListItem( "Diet for Better Sleep", "Discover foods that improve sleep quality", "https://example.com/diet-for-sleep"));
        items.add(new ListItem( "Meditation for Relaxation", "Explore meditation techniques to relax your mind", "https://example.com/meditation-relaxation"));

        adapter = new CustomAdapter(getActivity(), items); // Use getActivity() to pass the context
        listView.setAdapter(adapter);

        return view; // Return the inflated view
    }
}
