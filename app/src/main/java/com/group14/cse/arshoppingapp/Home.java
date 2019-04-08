package com.group14.cse.arshoppingapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    GridView gridView;

    String[] labels = {"Couch - 10eth","Book Case - 8eth","Flower Vase - 5eth","Flower Vase - 4eth"};
    int[] modelimages = {R.drawable.model1,R.drawable.model3,R.drawable.model4,R.drawable.model5};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //finding listview
        gridView = findViewById(R.id.gridview);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ProductActivity.class);
                intent.putExtra("name",labels[i]);
                intent.putExtra("image",modelimages[i]);
                startActivity(intent);

            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return modelimages.length;
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
            TextView name = view1.findViewById(R.id.textView);
            ImageView image = view1.findViewById(R.id.imageView);

            name.setText(labels[i]);
            image.setImageResource(modelimages[i]);
            return view1;



        }
    }
}

