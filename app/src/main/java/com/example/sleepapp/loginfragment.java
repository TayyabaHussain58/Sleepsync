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
import android.widget.TextView;
import android.widget.Toast;



public class loginfragment extends Fragment {

    private EditText usernameEditText, passwordEditText;
    private Button loginButton;
    private TextView registerText;

    private SharedPreferences acounts;

    public loginfragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loginfragment, container, false);

        usernameEditText = view.findViewById(R.id.Name);
        passwordEditText = view.findViewById(R.id.editTextTextPassword);
        loginButton = view.findViewById(R.id.loginbutton);
        registerText = view.findViewById(R.id.register);

        // Initialize shared preferences
        acounts = getActivity().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = usernameEditText.getText().toString();
                String inputPassword = passwordEditText.getText().toString();

                // Retrieve saved username and password from SharedPreferences
                String savedUsername = acounts.getString("username", "");
                String savedPassword = acounts.getString("password", "");

                // Validate credentials
                if (inputUsername.equals(savedUsername) && inputPassword.equals(savedPassword)) {
                    Toast.makeText(getActivity(), "Login successful", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), Dashboared.class);
                    intent.putExtra("USER_NAME", savedUsername);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set OnClickListener for the register text
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignupActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
