package com.example.ea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

    public class UserdataActivity extends AppCompatActivity {
        private FirebaseUser user;
        private DatabaseReference reference;

        private String userID;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_userdata);


            user = FirebaseAuth.getInstance().getCurrentUser();
            reference = FirebaseDatabase.getInstance().getReference("Users");
            userID = user.getUid();

            //define//
            final TextView GapTextview = (TextView) findViewById(R.id.Gap);
            final TextView emailTextview = (TextView) findViewById(R.id.EmailAddress);
            final TextView fullnameTextview = (TextView) findViewById(R.id.FullName);
            final TextView AgeTextview = (TextView) findViewById(R.id.Age);

            //display user data function//
            reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    User userprofile = snapshot.getValue(User.class);

                    if (userprofile != null) {
                        String fullname = userprofile.fullname;
                        String emailaddress = userprofile.email;
                        String Age = userprofile.age;

                        GapTextview.setText("welcome " + fullname + "!");
                        emailTextview.setText(emailaddress);
                        fullnameTextview.setText(fullname);
                        AgeTextview.setText(Age);


                    }

                }

                // Error messages
                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(UserdataActivity.this, "Something Wrong!", Toast.LENGTH_LONG).show();

                }
            });


        }

    }