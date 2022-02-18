package com.example.ea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class FogotPassward extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private EditText emailEditText;
    private Button resetPasswordButton;
    private ProgressBar progressBar;

    FirebaseAuth auth;
    // define navigation bar
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fogot_passward);

        // define navigation bar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();


        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);



        // define iuput data
        emailEditText = (EditText)findViewById(R.id.ResEmail);
        resetPasswordButton = (Button)findViewById((R.id.ResPassword));
        progressBar=(ProgressBar) findViewById(R.id.FPprogressBar);

        auth= FirebaseAuth.getInstance();

        // button function
        resetPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();




            }
        });
    }
    private void resetPassword(){
        //set the rule for the input data//
        String email = emailEditText.getText().toString().trim();

        if (email.isEmpty()){
            emailEditText.setError("email cannot empty");
            emailEditText.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailEditText.setError("please enter a correct email");
            emailEditText.requestFocus();
            return;
        }

        //connect to firebase to sned a reset password email to user email//
        progressBar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(FogotPassward.this,"check your email reset your password!",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(FogotPassward.this,"Try again !!",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch ( menuItem.getItemId() ) {


            case R.id.Menu2Exit:

                Intent s = new Intent();

                s.setClass(this, MainActivity.class);

                startActivityForResult(s, 0);

                break;


        }
        return true;
    }
}

