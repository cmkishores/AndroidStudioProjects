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

    String[] labels = {"Couch","Table","Bookcase","Flower Vase","Flower Vase","Television","Chair,Light Stand"};
    int[] modelimages = {R.drawable.model1,R.drawable.model2,R.drawable.model3,R.drawable.model4,R.drawable.model5,R.drawable.model6,R.drawable.model7,R.drawable.model8};
    String[] prizes = {"10eth","7eth","8eth","4eth","4.5eth","7eth","6eth","5eth"};
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
//              Toast.makeText(getApplicationContext(),fruitNames[i],Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(),ProductActivity.class);
                intent.putExtra("name",labels[i]);
                intent.putExtra("image",modelimages[i]);
                intent.putExtra("prize",prizes[i]);
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
            View view1 = getLayoutInflater().inflate(R.layout.grid_item,null,false);
            //getting view in row_data
            TextView name = view1.findViewById(R.id.label);
            TextView prize = view1.findViewById(R.id.prize);
            ImageView image = view1.findViewById(R.id.model_images);

            name.setText(labels[i]);
            prize.setText(prizes[i]);
            image.setImageResource(modelimages[i]);
            return view1;



        }
    }
}