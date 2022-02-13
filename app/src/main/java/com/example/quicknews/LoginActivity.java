package com.example.quicknews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button signButton;
    TextView bottomB1;
    EditText email,pass;


    private TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            checkFieldsForEmptyValues();
        }
    };

    public void checkFieldsForEmptyValues(){

        String userEmail = email.getText().toString();
        String userPass = pass.getText().toString();

        if(userEmail.contains("@") && userPass.length() > 2){
            signButton.setEnabled(true);
        } else {
            signButton.setEnabled(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

//        Initialize Shared Preference
        SharedPreferences sharedPref = getSharedPreferences("MySharedPreference", MODE_PRIVATE);

        signButton = (Button) findViewById(R.id.signInButton);
        bottomB1 = (TextView) findViewById(R.id.signInBottom);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);


        // set listeners
        email.addTextChangedListener(mTextWatcher);
        pass.addTextChangedListener(mTextWatcher);
        checkFieldsForEmptyValues();


        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(email.getText().toString().equals("mn@asd")){

                    SharedPreferences.Editor myEdit = sharedPref.edit();
                    myEdit.putString("usr_email", email.getText().toString());
                    myEdit.putString("login", "yes");
                    myEdit.commit();

                    Intent i = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                } else{
                    Toast.makeText(getApplicationContext(), "Invalid Email And Password", Toast.LENGTH_LONG).show();
                }
            }
        });


        bottomB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegistrationActivity.class);
                startActivity(i);
            }
        });
    }


}