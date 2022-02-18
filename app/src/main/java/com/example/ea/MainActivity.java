package com.example.ea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnLogin, btnRegister,btnProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(this);

        btnProfile = (Button) findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(this);

    }

    @Override
    //switch page
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                startActivity(new Intent(this,signInActivity.class));
                break;

            case R.id.btnRegister:
                startActivity(new Intent(this,RegisterActivity.class));
                break;

            case R.id.btnProfile:
                startActivity(new Intent(this,ProfileActivity.class));
                break;
        }
    }
}