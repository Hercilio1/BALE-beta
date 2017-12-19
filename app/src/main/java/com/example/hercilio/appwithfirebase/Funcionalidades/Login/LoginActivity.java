package com.example.hercilio.appwithfirebase.Funcionalidades.Login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.hercilio.appwithfirebase.R;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        getSupportActionBar().hide();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container,new LoginFragment())
                .commit();
    }
}
