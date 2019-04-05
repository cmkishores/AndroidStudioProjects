package com.group14.cse.arshoppingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.model1, R.drawable.model2,
            R.drawable.model3, R.drawable.model4,
            R.drawable.model5, R.drawable.model6,
            R.drawable.model7, R.drawable.model8,

    };

    private String[] imageLabels;
    private LayoutInflater thisInflater;

    // Constructor
    public Adapter(Context c, String[] labs) {

        this.mContext = c;
        this.thisInflater = LayoutInflater.from(c);
        this.imageLabels = labs;

    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = thisInflater.inflate(R.layout.grid_item, parent, false);

            TextView textHeading = (TextView) convertView.findViewById(R.id.grid_text);
            ImageView thumbnailImage = (ImageView) convertView.findViewById(R.id.grid_image);

            textHeading.setText(imageLabels[position]);
            thumbnailImage.setImageResource(mThumbIds[position]);
        }

        return convertView;

    }
}