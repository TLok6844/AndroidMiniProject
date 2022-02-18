package com.example.ea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;

public class InsideActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;

    private Button button1, button2, button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside);
        // define button
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        //button1 function  : open the image activity//
        button1.setOnClickListener(new Button.OnClickListener() {

            @Override

            public void onClick(View v) {

                Intent B = new Intent(InsideActivity.this, imageActivity.class);

                startActivity(B);


            }

        });
        // button 2 function : open website//
        button2.setOnClickListener(new Button.OnClickListener() {

            @Override

            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.hongkongdisneyland.com/zh-hk/");

                Intent i = new Intent(Intent.ACTION_VIEW, uri);

                startActivity(i);


            }

        });


        //button3 function: check user data//
        button3.setOnClickListener(new Button.OnClickListener() {

            @Override

            public void onClick(View v) {
                Intent user = new Intent(InsideActivity.this, UserdataActivity.class);

                startActivity(user);


            }

        });


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
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {


            case R.id.MenuItem:

                Intent s = new Intent();

                s.setClass(this, signInActivity.class);

                startActivityForResult(s, 0);

                break;

            case R.id.MeunHome:
                Intent h = new Intent();

                h.setClass(this, MainActivity.class);

                startActivityForResult(h, 0);

                break;
            case R.id.MeunContect:

                Uri urc = Uri.parse("tel:85235503388");

                Intent c = new Intent(Intent.ACTION_VIEW, urc);

                startActivity(c);
                break;

            case R.id.MeunMap:
                Uri urm = Uri.parse("geo:22.3167,114.0451");

                Intent m = new Intent(Intent.ACTION_VIEW, urm);

                startActivity(m);
                break;



        }
        return true;
    }

}