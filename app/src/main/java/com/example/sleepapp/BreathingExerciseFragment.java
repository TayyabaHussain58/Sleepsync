package com.example.sleepapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BreathingExerciseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BreathingExerciseFragment extends Fragment {
    private ListView listView;
    private BreathingExerciseAdapter adapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BreathingExerciseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BreathingExerciseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BreathingExerciseFragment newInstance(String param1, String param2) {
        BreathingExerciseFragment fragment = new BreathingExerciseFragment();
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
        View view = inflater.inflate(R.layout.fragment_breathing_exercise, container, false);

        listView = view.findViewById(R.id.breathinglist);

        // Create a list of breathing exercises
        List<BreathingExercise> exercises = new ArrayList<>();
        exercises.add(new BreathingExercise("Diaphragmatic Breathing (Belly Breathing)", R.raw.belly_breathing));
        exercises.add(new BreathingExercise("4-7-8 Breathing (Relaxing Breath)", R.raw.relaxing_breath));
        exercises.add(new BreathingExercise("Box Breathing (Square Breathing)", R.raw.box_breathing));
        exercises.add(new BreathingExercise("Pursed-Lip Breathing", R.raw.pursed_lip_breathing));

        // Set up the adapter
        adapter = new BreathingExerciseAdapter(getActivity(), exercises);
        listView.setAdapter(adapter);

        return view;
    }
}