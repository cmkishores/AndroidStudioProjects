package com.group14.cse.arshoppingapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class ProductActivity extends Activity {
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        btn=(Button)findViewById(R.id.BuyButton3D);
        btn.setOnClickListener(v -> {
            Intent intent = new Intent(ProductActivity.this, ARFRAG.class);
            startActivity(intent);
        });
    }
}
