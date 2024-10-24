package com.example.sleepapp;
public class BreathingExercise {
    private String name;
    private int audioResId;  // Resource ID for the audio file

    public BreathingExercise(String name, int audioResId) {
        this.name = name;
        this.audioResId = audioResId;
    }

    public String getName() {
        return name;
    }

    public int getAudioResId() {
        return audioResId;
    }
}
