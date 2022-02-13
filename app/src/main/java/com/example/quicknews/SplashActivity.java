package com.example.quicknews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        SharedPreferences sharedPref = getSharedPreferences("MySharedPreference", MODE_PRIVATE);
        String user_email = sharedPref.getString("usr_email", "false");
        String login = sharedPref.getString("login", "false");


        Thread thread = new Thread(){
            public void  run(){
                try {
                    sleep(1000);


                }catch (Exception e){
                    e.printStackTrace();
                }
                finally {
                    if(login.equals("yes")){
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }else{
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
                }
            }
        };

        thread.start();


    }
}