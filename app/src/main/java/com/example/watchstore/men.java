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

public class men extends AppCompatActivity {

    GridView gridView;
    String [] watch_brand = {"Alpiner", "Bvlgari" , "Bubble" ,
            "Delbana","Ebel" ,"Fabergé"};

    String [] Reference = {"AL-650B4AE6" , "103868" ,"L082/04507" , "41601.748.6.184", "1216618" , "1694"};
    String [] Collection = {"Alpiner" , "Bvlgari Bvlgari" , "Bubble" ,"Classic" ,"1911" , "Fabergé Visionnaire"};
    String [] CaseSize = {"41 x 42.50mm" , "41mm" , "47mm" ,"40mm" ,"44mm" , "43mm"};
    String [] CaseMaterial = {"Stainless Steel" , "Aluminium" , "Black PVD, Stainless Steel" ,
                              "Stainless Steel"  ,"Stainless Steel" ,"Titanium, White Gold"};

    int [] price = {2295  ,4567 ,8250 , 920 ,1911 , 4660 ,38150};
    int [] watch_images = {R.drawable.men1 , R.drawable.men2 , R.drawable.men3 ,
            R.drawable.men4 , R.drawable.men5 , R.drawable.men6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_men);

        gridView = findViewById(R.id.gridView);
        customAdapter customAdapter = new customAdapter();
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



