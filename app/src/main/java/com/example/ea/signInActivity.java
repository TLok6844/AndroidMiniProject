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

public class signInActivity extends AppCompatActivity implements View.OnClickListener ,NavigationView.OnNavigationItemSelectedListener{

    private TextView Register ,forgetPassword;
    private EditText editTextEmail,editTextPassword;
    private Button signIn;
    private FirebaseAuth mAuth;
    private ProgressBar ProgressBar2;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Register =(TextView) findViewById(R.id.Register) ;
        Register.setOnClickListener(this);


        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        editTextEmail= (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        ProgressBar2= (ProgressBar) findViewById(R.id.ProgressBar2);

        mAuth = FirebaseAuth.getInstance();

        forgetPassword = (TextView) findViewById(R.id.forgotPassword);
        forgetPassword.setOnClickListener(this);


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

    @Override
    //switch page//
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.Register:
                startActivity(new Intent(this,RegisterActivity.class));
                break;

            case  R.id.signIn:
                userLogin();
                break;

            case R.id.forgotPassword:
                startActivity(new Intent(this,FogotPassward.class));
                break;
        }

    }

    private void userLogin() {
        //set the rule for the input data//
            String Signemail = editTextEmail.getText().toString().trim();
            String Signpassword = editTextPassword.getText().toString().trim();

            if (Signemail.isEmpty()) {
                editTextEmail.setError("email cannot empty");
                editTextEmail.requestFocus();
                return;
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(Signemail).matches()) {
                editTextEmail.setError("please enter a correct email");
                editTextEmail.requestFocus();
                return;
            }

            if (Signpassword.isEmpty()) {
                editTextPassword.setError("Password cannot empty");
                editTextPassword.requestFocus();
                return;
            }

            if (Signpassword.length() < 6) {
                editTextPassword.setError("Minimum password length should be 6 characters!");
                editTextPassword.requestFocus();
                return;
            }

            // connect firebase and read the data and sign in //
            ProgressBar2.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(Signemail, Signpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        startActivity(new Intent(signInActivity.this, InsideActivity.class));

                    } else {
                        Toast.makeText(signInActivity.this, "Failed to Login", Toast.LENGTH_LONG).show();
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

            case R.id.Menu_Signi_Exit:

                Intent se = new Intent();

                se.setClass(this, MainActivity.class);

                startActivityForResult(se, 0);

                break;

            case R.id.Menu_Signi_Fogot:

                Intent f = new Intent();

                f.setClass(this, FogotPassward.class);

                startActivityForResult(f, 0);

                break;

            case R.id.Menu_Signi_Register:

                Intent r = new Intent();

                r.setClass(this, RegisterActivity.class);

                startActivityForResult(r, 0);

                break;


        }
        return true;
    }
}
