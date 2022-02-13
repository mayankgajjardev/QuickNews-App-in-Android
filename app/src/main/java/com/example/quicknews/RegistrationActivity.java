package com.example.quicknews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegistrationActivity extends AppCompatActivity {

    Button signButton;
    TextView bottomB1;
    EditText name,email,pass;


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

        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPass = pass.getText().toString();

        if(userEmail.contains("@") && userPass.length() > 6){
            signButton.setEnabled(true);
        } else {
            signButton.setEnabled(false);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();

        signButton = (Button) findViewById(R.id.signInButton);
        bottomB1 = (TextView) findViewById(R.id.signInBottom);
        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);


        // set listeners
        name.addTextChangedListener(mTextWatcher);
        email.addTextChangedListener(mTextWatcher);
        pass.addTextChangedListener(mTextWatcher);

        checkFieldsForEmptyValues();

        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        bottomB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

    }
}