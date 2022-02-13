package com.example.quicknews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView unm,pass;
    Button logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile");

        logout = findViewById(R.id.btn_logout);
        unm = findViewById(R.id.profile_unm);
        pass = findViewById(R.id.profile_pass);


        // Initialize Shared Preference
        SharedPreferences sharedPref = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        String user_email = sharedPref.getString("usr_email", "false");
        String login = sharedPref.getString("login", "false");

        unm.setText(user_email);
//        pass.setText(pass);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPref.edit().clear();
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });



    }
}