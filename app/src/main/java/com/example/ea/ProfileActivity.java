package com.example.ea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;

public class ProfileActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TEXT_TO_SEND = "com.example.anroidTutoriai.TEXT_TO_SEND";
    private EditText text;
    private Button Enter;
    private String textSend;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // input data//
        text = findViewById(R.id.DataText);
        Enter = findViewById(R.id.Enter);
        // define navigation bar//
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);

        // send data to display activity//
        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textSend = text.getText().toString();
                ToDisplayActivity();
            }
        });
    }


    private void ToDisplayActivity() {
        Intent intent = new Intent(this, DisplayActivity.class);
        intent.putExtra(TEXT_TO_SEND, textSend);
        startActivity(intent);
    }

        //  navigation bar menu
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch ( menuItem.getItemId() ) {


            case R.id.Menu2Exit:

                Intent s = new Intent();

                s.setClass(ProfileActivity.this, MainActivity.class);

                startActivityForResult(s, 0);

                break;
            case R.id.Menu2Fogot:

                Intent f = new Intent();

                f.setClass(ProfileActivity.this, FogotPassward.class);

                startActivityForResult(f, 0);

                break;
            case R.id.Menu2login:

                Intent L = new Intent();

                L.setClass(ProfileActivity.this, signInActivity.class);

                startActivityForResult(L, 0);

                break;
            case R.id.Menu2Register:

                Intent r = new Intent();

                r.setClass(ProfileActivity.this, RegisterActivity.class);

                startActivityForResult(r, 0);

                break;

        }
            return true;
    }
}
