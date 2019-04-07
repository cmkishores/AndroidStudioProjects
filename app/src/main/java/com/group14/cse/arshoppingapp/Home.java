package com.group14.cse.arshoppingapp;


import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;



public class Home extends AppCompatActivity
{
   private String[] labels;
   private int[] images;
    private TextView mTextMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Resources res = getResources();
        images = res.getIntArray(R.array.image);
        labels = res.getStringArray(R.array.headings);
        mTextMessage = (TextView) findViewById(R.id.message);


        GridView gridView = (GridView) findViewById(R.id.GridView);

        // Instance of ImageAdapter Class

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//              Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ProductActivity.class);
                intent.putExtra("name",labels[i]);
                intent.putExtra("image",images[i]);
                startActivity(intent);

            }
        });


    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return images.length;
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
            View view1 = getLayoutInflater().inflate(R.layout.grid_item,null);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.grid_text);
            ImageView image = view1.findViewById(R.id.grid_image);

            name.setText(labels[i]);
            image.setImageResource(images[i]);
            return view1;



        }
    }
}
