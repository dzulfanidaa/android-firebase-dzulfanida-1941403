package com.example.tombol;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                    finish();
                }
            }
        };

        Button tombol_logout = findViewById(R.id.Button02);
        tombol_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    //sign out method
    public void signOut() {
        auth.signOut();
        finish();
        startActivity(new Intent(HomeActivity.this, MainActivity.class));
    }
}