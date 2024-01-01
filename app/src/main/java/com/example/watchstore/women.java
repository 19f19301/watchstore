package com.example.watchstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class women extends AppCompatActivity {

    GridView gridView;
    String [] watch_brand = {"Hermès", "Junghans" , "Louis Vuitton" ,
            "Nivada Grenchen","Ebel" ,"Fabergé"};

    String [] Reference = {"403169WW00" , "59/7324.02" ,"W1PG10" , "41601.748.6.184", "35005M41" , "1694"};
    String [] Collection = {" Arceau" , "max bill" , "Tambour" ,"Classic" ,"1911" , "Antarctic"};
    String [] CaseSize = {"38mm" , "38mm" , "40mm" ,"38mm" ,"35mm" , "32mm"};
    String [] CaseMaterial = {"Rose Gold" , "Yellow Gold Plated Steel" , "Rose Gold" ,
            "Stainless Steel"  ,"Stainless Steel" ,"Titanium, White Gold"};

    int [] price = {71000  ,4567 ,8890 , 9920 ,8967 , 4785 ,3467};
    int [] watch_images = {R.drawable.women1 , R.drawable.women2 , R.drawable.women3 ,
            R.drawable.women4 , R.drawable.women5 , R.drawable.women6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women);

        gridView = findViewById(R.id.gridView);
        women.customAdapter customAdapter = new women.customAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext() , watches_items.class);
                intent.putExtra("name" , watch_brand[i] );
                intent.putExtra("image" , watch_images[i] );
                intent.putExtra("Reference" , Reference[i] );
                intent.putExtra("Collection" , Collection[i] );
                intent.putExtra("CaseSize" , CaseSize[i] );
                intent.putExtra("CaseMaterial" , CaseMaterial[i] );
                intent.putExtra("price" , price [i] );
                startActivity(intent);
            }
        });
    }

    private class customAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return watch_brand.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.rows_data, null);
            TextView name = view1.findViewById(R.id.watches);
            ImageView imageView = view1.findViewById(R.id.watches_images);
            TextView price_ = view1.findViewById(R.id.price_);

            name.setText(watch_brand[i]);
            imageView.setImageResource(watch_images[i]);
            price_.setText(String.valueOf(price[i]));



            return view1;
        }
    }
}



