package com.group14.cse.arshoppingapp;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;



public class Home extends Activity
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

        Adapter myAdapter =new Adapter(getApplicationContext(),labels);
        gridView.setAdapter(myAdapter);

        /**
         * On Click event for Single Gridview Item
         * */
        gridView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    int i, long l)
            {
                Toast.makeText(getApplicationContext(),labels[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ProductActivity.class);
                final Intent name = intent.putExtra("name", labels[i]);
                intent.putExtra("image", images[i]);
                startActivity(intent);

            }

        });

    }

}