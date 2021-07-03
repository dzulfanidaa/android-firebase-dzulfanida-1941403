package com.example.tombol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // loginamik
    // z&HXtxY6DQX!dpjY#tm1

    public EditText username;
    public String username_text;
    public EditText password;
    public String password_text;
    private FirebaseAuth auth;
//    public ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
            finish();
        }

        // act click
        Button tombol = findViewById(R.id.Button01);
        tombol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username_text = username.getText().toString();
                password_text = password.getText().toString();
                Log.i("username", username_text);
                Log.i("password", password_text);
                if (TextUtils.isEmpty(username_text) || TextUtils.isEmpty(password_text)) {
                    Toast.makeText(MainActivity.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                } else {
//                    progressBar.setVisibility(View.VISIBLE);
                    //authenticate user
                    auth.signInWithEmailAndPassword(username_text, password_text)
                            .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    // If sign in fails, display a message to the user. If sign in succeeds
                                    // the auth state listener will be notified and logic to handle the
                                    // signed in user can be handled in the listener.
//                                    progressBar.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(MainActivity.this, getString(R.string.auth_failed), Toast.LENGTH_LONG).show();
                                    } else {
                                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                }
            }
        });
    }
}