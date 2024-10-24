package com.example.sleepapp;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String[] dates = {"Oct 19", "Oct 20", "Oct 21", "Oct 22", "Oct 23"};
    int[] sleepingHours = {6, 7, 8, 5, 7};

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("UserData", getContext().MODE_PRIVATE);
        String sleepHours = sharedPreferences.getString("sleepingHours", "7"); // Default is 7 hours if no data
        float lastSleepHours = Float.parseFloat(sleepHours);

        // Add the retrieved sleep hours to your array
        int[] updatedSleepingHours = new int[sleepingHours.length + 1];
        System.arraycopy(sleepingHours, 0, updatedSleepingHours, 0, sleepingHours.length);
        updatedSleepingHours[updatedSleepingHours.length - 1] = (int) lastSleepHours;

        String[] updatedDates = new String[dates.length + 1];
        System.arraycopy(dates, 0, updatedDates, 0, dates.length);
        updatedDates[updatedDates.length - 1] = "Today";
        BarChart barChart = view.findViewById(R.id.barChart);

        // Get the username from arguments
        Bundle args = getArguments();
        String userName = args != null ? args.getString("USER_NAME") : "Guest";

        // Set the username to the TextView
        TextView userNameTextView = view.findViewById(R.id.UserName);
        userNameTextView.setText(userName);
        ImageView imageView = view.findViewById(R.id.profilepic);
        Glide.with(this)
                .load(R.drawable.personimg)  // replace this with the image URL or drawable resource
                .apply(RequestOptions.circleCropTransform())  // circular transformation
                .into(imageView);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < updatedSleepingHours.length; i++) {
            barEntries.add(new BarEntry(i, updatedSleepingHours[i]));
        }

        BarDataSet barDataSet = new BarDataSet(barEntries, "Sleeping Hours");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.getDescription().setEnabled(false);
        barChart.animateY(1000);

        // Step 4: Customize X-Axis with Dates
        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                if (value >= 0 && value < updatedDates.length) {
                    return updatedDates[(int) value];  // Return the corresponding date
                } else {
                    return "";  // Return empty string if index is out of bounds
                }
            }
        });

        return view;
    }}