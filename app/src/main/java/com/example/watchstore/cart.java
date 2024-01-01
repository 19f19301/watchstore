package com.example.watchstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class cart extends AppCompatActivity {

    TextView name  , price_;
    ImageView image;
    TextView num_of_items;
    int value = 1;
    Button buy;
    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    String userID;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        name = findViewById(R.id.griddata);
        image = findViewById(R.id.imageView);
        price_ = findViewById(R.id.griddataa);
        buy = findViewById(R.id.button5);

        progressBar = findViewById(R.id.progressBar2);

        firebaseAuth = FirebaseAuth.getInstance();


        DatabaseReference watchStoreReference = FirebaseDatabase.getInstance().getReference("Users info & orders");


        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        image.setImageResource(intent.getIntExtra("image", 0));


        int pricetotal = intent.getIntExtra("price", 0);
        price_.setText("$  " +String.valueOf(pricetotal));

        num_of_items = findViewById(R.id.textView13);
        TextView plusButton = findViewById(R.id.textView12);
        TextView minusButton = findViewById(R.id.textView14);

        num_of_items.setText(String.valueOf(value));


        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value++;
                updateValueTextView();
                int total = value * pricetotal;
                price_.setText("$  " + String.valueOf(total));
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (value > 1) {
                    value--;
                    updateValueTextView();
                    int total = value * pricetotal;
                    price_.setText("$  " + String.valueOf(total));
                }
            }
        });

        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                String namewatch = name.getText().toString();
                String price = price_.getText().toString();
                String itemnum = num_of_items.getText().toString();

                if(firebaseAuth.getCurrentUser() !=null){
                    userID = firebaseAuth.getCurrentUser().getUid();
                    DatabaseReference currentwatchStoreUserReference = watchStoreReference.child(userID).child("order info");

                    currentwatchStoreUserReference.child("watch name").setValue(namewatch);
                    currentwatchStoreUserReference.child("item number").setValue(itemnum);
                    currentwatchStoreUserReference.child("price").setValue(price);

                }
                watchStoreReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(cart.this, "your order has been submit to our store.", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(cart.this, "Error adding your order.", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });



    }
    private void updateValueTextView() {
        num_of_items.setText(String.valueOf(value));
    }
}




