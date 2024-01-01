package com.example.watchstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class login extends AppCompatActivity {

    TextView register;

    Button login;

    EditText Email_, Password_;

    FirebaseAuth firebaseAuth;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.textView7);
        login = findViewById(R.id.button4);

        Email_  =findViewById(R.id.editTextTextEmailAddress);
        Password_  =findViewById(R.id.editTextTextPassword);

        firebaseAuth= FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressBar4);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                String email , password , mopilephone_ , username_;
                email = String.valueOf(Email_.getText());
                password = String.valueOf(Password_.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(login.this , "enter your email please" , Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(login.this , "enter your password please" , Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(login.this, "Login Successful.", Toast.LENGTH_SHORT).show();
                                    startActivity( new Intent(login.this , MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(login.this , register.class));
            }
        });
    }
}