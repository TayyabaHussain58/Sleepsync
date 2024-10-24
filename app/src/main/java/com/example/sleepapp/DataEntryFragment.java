package com.example.sleepapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DataEntryFragment extends DialogFragment {
    private EditText bedTimeEditText, wakeTimeEditText, mealTimingEditText, sleepquality,sugarintake,proteinintake,fiberintake;
    private RadioGroup mealTypeRadioGroup, quantityRadioGroup;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_data_entry, container, false);

        bedTimeEditText = view.findViewById(R.id.bedTimeEditText);
        wakeTimeEditText = view.findViewById(R.id.wakeTimeEditText);
        mealTimingEditText = view.findViewById(R.id.mealTimingEditText);
        mealTypeRadioGroup = view.findViewById(R.id.mealTypeRadioGroup);
        quantityRadioGroup = view.findViewById(R.id.quantityRadioGroup);
        sleepquality=view.findViewById(R.id.sleepQualityRatingEditText);
        sugarintake=view.findViewById(R.id.sugarIntakeEditText);
        proteinintake=view.findViewById(R.id.proteinIntakeEditText);
        fiberintake=view.findViewById(R.id.fiberIntakeEditText);

        Button saveButton = view.findViewById(R.id.saveButton);
        Button cancelButton = view.findViewById(R.id.cancelButton);

        saveButton.setOnClickListener(v -> saveUserData());
        cancelButton.setOnClickListener(v -> dismiss());

        return view;
    }

    private void saveUserData() {
            String bedTime = bedTimeEditText.getText().toString();
            String wakeTime = wakeTimeEditText.getText().toString();
            String mealTiming = mealTimingEditText.getText().toString();
            String sleepQuality = sleepquality.getText().toString();
            String sugarIntake = sugarintake.getText().toString();
            String proteinIntake = proteinintake.getText().toString();
            String fiberIntake = fiberintake.getText().toString();

            // Get selected meal type
            int selectedMealTypeId = mealTypeRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedMealTypeButton = view.findViewById(selectedMealTypeId);
            String mealType = selectedMealTypeButton != null ? selectedMealTypeButton.getText().toString() : "";

            // Get selected quantity
            int selectedQuantityId = quantityRadioGroup.getCheckedRadioButtonId();
            RadioButton selectedQuantityButton = view.findViewById(selectedQuantityId);
            String quantity = selectedQuantityButton != null ? selectedQuantityButton.getText().toString() : "";

            if (bedTime.isEmpty() || wakeTime.isEmpty() || mealTiming.isEmpty() || mealType.isEmpty() ||
                    quantity.isEmpty() || sleepQuality.isEmpty() || sugarIntake.isEmpty() || proteinIntake.isEmpty() ||
                    fiberIntake.isEmpty()) {
                Toast.makeText(getActivity(), "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("UserData", getActivity().MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("bedTime", bedTime);
                editor.putString("wakeTime", wakeTime);
                editor.putString("mealTiming", mealTiming);
                editor.putString("mealType", mealType);
                editor.putString("quantity", quantity);
                editor.putString("sleepQuality", sleepQuality);
                editor.putString("sugarIntake", sugarIntake);
                editor.putString("proteinIntake", proteinIntake);
                editor.putString("fiberIntake", fiberIntake);
                editor.putBoolean("dataEntryComplete", true);
                editor.apply();

                Toast.makeText(getActivity(), "Data saved successfully", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        }}