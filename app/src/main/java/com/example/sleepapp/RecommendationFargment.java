package com.example.sleepapp;

import static android.content.Context.MODE_PRIVATE;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.SharedPreferences;
import android.widget.TextView;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecommendationFargment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecommendationFargment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RecommendationFargment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecommendationFargment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecommendationFargment newInstance(String param1, String param2) {
        RecommendationFargment fragment = new RecommendationFargment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommendation_fargment, container, false);
        String recommendations = generateRecommendations();

        TextView recommendationsTextView = view.findViewById(R.id.recommendationsTextView);
        recommendationsTextView.setText(recommendations);

        return view;
    }

    private String generateRecommendations() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserData", MODE_PRIVATE);

        String bedTime = sharedPreferences.getString("bedTime", "00:00");
        String wakeTime = sharedPreferences.getString("wakeTime", "07:00"); // Default wake time
        String mealTiming = sharedPreferences.getString("mealTiming", "00:00");
        String sugarIntakeStr = sharedPreferences.getString("sugarIntake", "0");
        String fiberIntakeStr = sharedPreferences.getString("fiberIntake", "0");
        String proteinIntakeStr = sharedPreferences.getString("proteinIntake", "0");
        String sleepQualityRatingstr = sharedPreferences.getString("sleepQualityRating", "10"); // Default to 10 for good rating
        int sleepQualityRating = Integer.parseInt(sleepQualityRatingstr);

        // Parse the intake values
        int sugarIntake = Integer.parseInt(sugarIntakeStr);
        int fiberIntake = Integer.parseInt(fiberIntakeStr);
        int proteinIntake = Integer.parseInt(proteinIntakeStr);

        // Convert the time strings to hours (simple subtraction)
        int bedHour = Integer.parseInt(bedTime.split(":")[0]);
        int mealHour = Integer.parseInt(mealTiming.split(":")[0]);
        int wakeHour = Integer.parseInt(wakeTime.split(":")[0]);

        // Calculate the difference between meal time and bed time (simple subtraction)
        int mealToSleepHoursDiff = bedHour - mealHour;

        List<String> recommendations = new ArrayList<>();

        // Sleep Duration
        if ((wakeHour - bedHour) < 8) {
            recommendations.add("Try to get at least 8 hours of sleep for better health.");
        }

        // Bedtime
        if (bedHour >= 24 || bedHour == 0) {
            recommendations.add("Try to go to bed before midnight for better sleep quality.");
        }

        // Wake-Up Time
        if (wakeHour < 7) {
            recommendations.add("Aim to wake up after 7 AM for a more balanced routine.");
        }

        // Sugar Intake
        if (sugarIntake > 100) {
            recommendations.add("Consider reducing your sugar intake, as high sugar consumption can affect sleep quality.");
        }

        // Fiber Intake
        if (fiberIntake < 25) {
            recommendations.add("Increase your fiber intake by including more fruits, vegetables, and whole grains.");
        }

        // Protein Intake
        if (proteinIntake < 50) {
            recommendations.add("Make sure you get enough protein in your diet for overall health.");
        }

        // Meal Timing Before Sleep
        if (mealToSleepHoursDiff < 2) {
            recommendations.add("Try to eat at least 2 hours before sleeping to enhance digestion and sleep quality.");
        }

        // Sleep Quality Rating
        if (sleepQualityRating < 8) {
            recommendations.add("Consider setting your environment to be quieter and more relaxing for better sleep quality.");
        }

        // Compile recommendations
        return String.join("\n", recommendations);
    }

}