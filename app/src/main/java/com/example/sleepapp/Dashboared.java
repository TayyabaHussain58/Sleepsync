package com.example.sleepapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.atomic.AtomicReference;

public class Dashboared extends AppCompatActivity {
    public boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboared);
        AtomicReference<Intent> intent = new AtomicReference<>(getIntent());
        String userName = intent.get().getStringExtra("USER_NAME");

        DrawerLayout drawerLayout = findViewById(R.id.main);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ImageButton buttondrawertoggle = findViewById(R.id.menuButton);

        buttondrawertoggle.setOnClickListener(v -> drawerLayout.open());
        if (savedInstanceState == null) {
            Fragment profileFragment = new ProfileFragment();
            Bundle bundle = new Bundle();
            bundle.putString("USER_NAME", userName); // Pass user name to profile fragment
            profileFragment.setArguments(bundle);

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, profileFragment) // Load ProfileFragment initially
                    .commit();
        }

        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment fragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.Profile) {
                fragment = new ProfileFragment();
                Bundle bundle = new Bundle();
                bundle.putString("USER_NAME", userName);
                fragment.setArguments(bundle);
            } else if (itemId == R.id.Education) {
                fragment = new EducationalFragment();
            } else if (itemId == R.id.Breathing) {
                boolean flag=isDataEntryComplete();

                if (isDataEntryComplete()) {
                    fragment = new BreathingExerciseFragment();
                } else {
                    showDataEntryFragment();
                }
            } else if (itemId == R.id.Recommendation) {
                if (isDataEntryComplete()) {
                    fragment = new RecommendationFargment();
                } else {
                    showDataEntryFragment();
                }
            } else if (itemId == R.id.logout) {
                clearData();
                intent.set(new Intent(Dashboared.this, LoginActivity.class));
                startActivity(intent.get());

            }

            if (fragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment)
                        .commit();
            }

            drawerLayout.close();
            return false;
        });
    }

    private boolean isDataEntryComplete() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);

        String bedTime = sharedPreferences.getString("bedTime", "");
        String wakeTime = sharedPreferences.getString("wakeTime", "");
        String mealTiming = sharedPreferences.getString("mealTiming", "");
        String mealType = sharedPreferences.getString("mealType", "");
        String quantity = sharedPreferences.getString("quantity", "");
        String sleepQualityRating = sharedPreferences.getString("sleepQualityRating", "");
        String sugarIntake = sharedPreferences.getString("sugarIntake", "");
        String fiberIntake = sharedPreferences.getString("fiberIntake", "");
        String proteinIntake = sharedPreferences.getString("proteinIntake", "");

        // Check if data entry flag is set to true
        boolean dataEntryComplete = sharedPreferences.getBoolean("dataEntryComplete", false);

        // Return true only if all fields are filled and data entry is complete
        return dataEntryComplete;
    }

    private void clearData() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("bedTime", "");
        editor.putString("wakeTime", "");
        editor.putString("mealTiming", "");
        editor.putString("mealType", "");
        editor.putString("quantity", "");
        editor.putString("sleepQualityRating", ""); // Clear sleep quality rating
        editor.putString("sugarIntake", ""); // Clear sugar intake
        editor.putString("fiberIntake", ""); // Clear fiber intake
        editor.putString("proteinIntake", ""); // Clear protein intake
        editor.putBoolean("dataEntryComplete", false);
        editor.apply();
    }
    private void showDataEntryFragment() {
        DataEntryFragment dataEntryFragment = new DataEntryFragment();
        dataEntryFragment.show(getSupportFragmentManager(), "DataEntryFragment");
    }
}