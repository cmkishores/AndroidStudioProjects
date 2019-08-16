package com.group14.cse.arshoppingapp;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigDecimal;
import java.util.Objects;

import retrofit2.http.HEAD;

public class ProductActivity extends AppCompatActivity {
    Button btn1, btn2;



    TextView gridData;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);



        gridData = findViewById(R.id.gridData);
        imageView = findViewById(R.id.imageView);
        Intent intent1 = getIntent();
        String receivedName = intent1.getStringExtra("name");
        int receivedImage = intent1.getIntExtra("image", 0);
        gridData.setText(receivedName);
        imageView.setImageResource(receivedImage);
//29cf16c50a8ecf3a4ccfcb9f678b51d0410f33bf4e6edb916b701f8396189e60
        btn2 = (Button) findViewById(R.id.BuyButton3D);
        btn1 = (Button) findViewById(R.id.buybutton);

        btn2.setOnClickListener(v -> {
            if (receivedName.equals("Couch - 10eth")) {
                Intent intent = new Intent(ProductActivity.this, ARFRAG.class);
                startActivity(intent);
            } else if (receivedName.equals("Book Case - 8eth")) {
                Intent intent = new Intent(ProductActivity.this, ARFRAG3.class);
                startActivity(intent);
            } else if (receivedName.equals("Flower Vase - 5eth")) {
                Intent intent = new Intent(ProductActivity.this, ARFRAG4.class);
                startActivity(intent);
            } else if (receivedName.equals("Flower Vase - 4eth")) {
                Intent intent = new Intent(ProductActivity.this, ARFRAG5.class);
                startActivity(intent);
            }



        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

    }
}