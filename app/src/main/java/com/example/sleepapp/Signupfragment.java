package com.example.sleepapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Signupfragment extends Fragment {

    private EditText usernameEditText, passwordEditText, confirmPasswordEditText, emailEditText;
    private Button signupButton;

    private SharedPreferences accounts;
    private SharedPreferences.Editor editor;

    public Signupfragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_signupfragment, container, false);

        usernameEditText = view.findViewById(R.id.Name);
        passwordEditText = view.findViewById(R.id.editTextTextPassword);
        confirmPasswordEditText = view.findViewById(R.id.ConfirmPassword);
        emailEditText = view.findViewById(R.id.NaEmail);
        signupButton = view.findViewById(R.id.signupbutton);

        // Initialize shared preferences
        accounts = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);
        editor = accounts.edit();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getActivity(), "Please enter all details", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(getActivity(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    // Save user credentials in shared preferences
                    editor.putString("username", username);
                    editor.putString("password", password);
                    editor.apply(); // Save the changes

                    Toast.makeText(getActivity(), "Signup successful!", Toast.LENGTH_SHORT).show();

                    Intent intent=new Intent(getActivity(),Dashboared.class);
                    intent.putExtra("USER_NAME", username);
                    startActivity(intent);
                    startActivity(intent);
                }
            }
        });

        return view;
    }
}
