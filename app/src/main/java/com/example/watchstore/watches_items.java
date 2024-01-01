package com.example.watchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class watches_items extends AppCompatActivity {

    TextView name , Reference ,Collection , CaseSize , CaseMaterial , price_;
    ImageView image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watches_items);

        name = findViewById(R.id.griddata);
        image = findViewById(R.id.imageView);
        Reference = findViewById(R.id.textView4);
        Collection = findViewById(R.id.textView6);
        CaseSize = findViewById(R.id.textView8);
        CaseMaterial = findViewById(R.id.textView10);
        price_ = findViewById(R.id.textView12);



        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        image.setImageResource(intent.getIntExtra("image" ,0));
        Reference.setText(intent.getStringExtra("Reference"));
        Collection.setText(intent.getStringExtra("Collection"));
        CaseSize.setText(intent.getStringExtra("CaseSize"));
        CaseMaterial.setText(intent.getStringExtra("CaseMaterial"));
        price_.setText("$  "+String.valueOf(intent.getIntExtra("price", 0)));



        Button add_to_cart  = findViewById(R.id.button5);
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_ = intent.getStringExtra("name");
                int price = intent.getIntExtra("price" , 0);
                int image_ = intent.getIntExtra("image", 0);
                Intent intent = new Intent(getApplicationContext() , cart.class);
                intent.putExtra("name" , name_);
                intent.putExtra("price" , price);
                intent.putExtra("image" , image_);
                startActivity(intent);
            }
        });

    }
}