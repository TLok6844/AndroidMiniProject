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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,NavigationView.OnNavigationItemSelectedListener {

    private TextView banner, registerUser;
    private EditText editTextName,editTextAge, editTextEmail, editTextPassword;
    private ProgressBar progressBar;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // define firebase
        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);
        // register user
        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);

        editTextName = (EditText) findViewById(R.id.fullName);
        editTextAge = (EditText) findViewById(R.id.age);
        editTextEmail = (EditText) findViewById(R.id.RegEmail);
        editTextPassword =(EditText) findViewById(R.id.RegPassword);

        progressBar =  findViewById(R.id.progressBar);

        // navigation bar//
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


    }
        //switch page
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            // when click the banner will go back the homepage (main activity)
            case R.id.banner:
                startActivity(new Intent(this,MainActivity.class));
                break;
                // button for save data
            case R.id.registerUser:
                registerUser();
                break;

        }

    }
    //set the rule for the input data//
    private void registerUser() {
        //set the rule for the input data//
        String Regemail = editTextEmail.getText().toString().trim();
        String RegPassword = editTextPassword.getText().toString().trim();
        String Name = editTextName.getText().toString().trim();
        String Age = editTextAge.getText().toString().trim();


        if (Name.isEmpty()){
            editTextName.setError("please fill up the Name ");
            editTextName.requestFocus();
            return;
        }

        if (Age.isEmpty()){
            editTextAge.setError("please fill up the Age ");
            editTextAge.requestFocus();
            return;
        }

        if (Regemail.isEmpty()){
            editTextEmail.setError("please fill up the Email ");
            editTextEmail.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(Regemail).matches()){
            editTextEmail.setError("please provide valid email!");
            editTextEmail.requestFocus();
            return;
        }
        if (RegPassword.isEmpty()){
            editTextEmail.setError("please fill up the Password ");
            editTextEmail.requestFocus();
            return;
        }
        if(RegPassword.length() < 6){
            editTextPassword.setError("Minimum password length should be 6 characters!" );
            editTextPassword.requestFocus();
            return;
        }

        // connect the firebase and save data to firebase
        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(Regemail,RegPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(Name,Age,Regemail);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if (task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this,"User registered successfully" , Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                    else {
                                        Toast.makeText(RegisterActivity.this, "User registered successfully", Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                        }else{Toast.makeText(RegisterActivity.this, "successfully", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    };





                });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch ( menuItem.getItemId() ) {


            case R.id.Menu_reg_Exit:

                Intent s = new Intent();

                s.setClass(this, MainActivity.class);

                startActivityForResult(s, 0);

                break;

            case R.id.Menu_reg_login:

                Intent L = new Intent();

                L.setClass(RegisterActivity.this, signInActivity.class);

                startActivityForResult(L, 0);

                break;

            case R.id.Menu_reg_Fogot:

                Intent F = new Intent();

                F.setClass(RegisterActivity.this, FogotPassward.class);

                startActivityForResult(F, 0);

                break;



        }
        return true;
    }
}

