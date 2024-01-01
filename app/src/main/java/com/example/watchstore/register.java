package com.example.watchstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class register extends AppCompatActivity {

    Button create;

    EditText username , Email , Password , mopilephone;

    FirebaseAuth firebaseAuth;

    ProgressBar progressBar;

    String userID;

    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();


        DatabaseReference watchStoreReference = FirebaseDatabase.getInstance().getReference("Users info & orders");

        username = findViewById(R.id.editTextText);
        Email = findViewById(R.id.editTextText2);
        mopilephone = findViewById(R.id.editTextText3);
        Password = findViewById(R.id.editTextText4);
        create = findViewById(R.id.button4);

        progressBar = findViewById(R.id.progressBar);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);

                String email , password , mopilephone_ , username_;
                email = String.valueOf(Email.getText());
                password = String.valueOf(Password.getText());
                mopilephone_=  String.valueOf(mopilephone.getText());
                username_ = String.valueOf(username.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(register.this , "enter your email please" , Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(register.this , "enter your password please" , Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mopilephone_)){
                    Toast.makeText(register.this , "enter your mopile number please" , Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(username_)){
                    Toast.makeText(register.this , "enter your  name please" , Toast.LENGTH_SHORT).show();
                    return;
                }

                String Gender;
                RadioButton GenderRadioButton = findViewById(R.id.radioButton);

                if (GenderRadioButton.isChecked()) {
                    Gender = "Male";
                } else {
                    Gender = "Female";
                }

                if(firebaseAuth.getCurrentUser() !=null){
                    userID = firebaseAuth.getCurrentUser().getUid();
                    DatabaseReference currentwatchStoreUserReference = watchStoreReference.child(userID).child("order info");

                    currentwatchStoreUserReference.child("user name").setValue(username_);
                    currentwatchStoreUserReference.child("phone number").setValue(mopilephone_);


                }



                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(register.this, "Account created.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(register.this , login.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Toast.makeText(register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });
    }
}