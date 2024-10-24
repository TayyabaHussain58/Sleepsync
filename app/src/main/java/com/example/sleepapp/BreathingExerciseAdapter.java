package com.example.sleepapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class BreathingExerciseAdapter extends ArrayAdapter<BreathingExercise> {
    private MediaPlayer mediaPlayer;
    private int currentlyPlayingPosition = -1;
    public BreathingExerciseAdapter(@NonNull Context context, List<BreathingExercise> exercises) {
        super(context, 0, exercises);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        BreathingExercise exercise = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.breathingcustomlist, parent, false);
        }

        TextView exerciseName = convertView.findViewById(R.id.exercise_name);
        Button playButton = convertView.findViewById(R.id.play_button);

        exerciseName.setText(exercise.getName());

        // Set the button text based on whether it's currently playing
        if (position == currentlyPlayingPosition) {
            playButton.setText("Pause");
        } else {
            playButton.setText("Play");
        }

        // Play button click listener
        playButton.setOnClickListener(v -> {
            // If the current position is the same as the one being clicked, pause the audio
            if (position == currentlyPlayingPosition) {
                if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playButton.setText("Play"); // Change text to Play
                }
            } else {
                // Stop any currently playing audio
                if (mediaPlayer != null) {
                    mediaPlayer.release();
                }

                // Create and start the MediaPlayer
                mediaPlayer = MediaPlayer.create(getContext(), exercise.getAudioResId());
                mediaPlayer.setOnCompletionListener(mp -> {
                    // Release MediaPlayer when done
                    mp.release();
                    mediaPlayer = null;
                    currentlyPlayingPosition = -1; // Reset currently playing position
                    notifyDataSetChanged(); // Refresh the adapter to update button text
                });
                mediaPlayer.start();
                playButton.setText("Pause"); // Change text to Pause
                currentlyPlayingPosition = position; // Update the currently playing position
            }
        });

        return convertView;
    }

    // Clean up the MediaPlayer when the adapter is no longer in use
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            currentlyPlayingPosition = -1; // Reset position
        }
    }
}