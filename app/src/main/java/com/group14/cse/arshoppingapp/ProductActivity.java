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

public class ProductActivity extends AppCompatActivity
{
    Button btn1,btn2;
    private Web3j web3;
    //FIXME: Add your own password here
    private final String password = "medium";
    private String walletPath;
    private File walletDir;

    TextView gridData;
    TextView gridPrize;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        gridData = findViewById(R.id.griddata);
        gridPrize = findViewById(R.id.gridprize);
        imageView = findViewById(R.id.imageView);
        Intent intent1 = getIntent();
        String receivedName =  intent1.getStringExtra("name");
        String receivedPrize =  intent1.getStringExtra("prize");
        int receivedImage = intent1.getIntExtra("image",0);

        gridData.setText(receivedName);
        gridPrize.setText(receivedPrize);
        imageView.setImageResource(receivedImage);
        //enable back Button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btn2=(Button)findViewById(R.id.BuyButton3D);
        btn1=(Button)findViewById(R.id.buybutton);
        btn2.setOnClickListener(v -> {
            Intent intent = new Intent(ProductActivity.this, ARFRAG.class);
            startActivity(intent);
        });
        btn1.setOnClickListener(v -> {
            connectToEthNetwork(v);
            createWallet(v);
            getAddress(v);
            sendTransaction(v);

        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == android.R.id.home)
        {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();

    }


    public ActionBar getSupportActionBar() {
        return null;
    }

    public void connectToEthNetwork(View v)
    {
        toastAsync("Connecting to Ethereum network...");
        // FIXME: Add your own API key here
        web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/832579"));
        try
        {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if(!clientVersion.hasError())
            {
                toastAsync("Connected!");
            }
            else
            {
                toastAsync(clientVersion.getError().getMessage());
            }
        }
        catch (Exception e)
        {
            toastAsync(e.getMessage());
        }
    }
    public void createWallet(View v)
    {
        try
        {
            WalletUtils.generateNewWalletFile(password, walletDir);
            toastAsync("Wallet generated");
        }
        catch (Exception e)
        {
            toastAsync(e.getMessage());
        }
    }
    public void getAddress(View v)
    {
        try
        {
            Credentials credentials = WalletUtils.loadCredentials(password, walletDir);
            toastAsync("Your address is " + credentials.getAddress());
        }
        catch (Exception e)
        {
            toastAsync(e.getMessage());
        }
    }
    public void sendTransaction(View v)
    {
        try
        {
            Credentials credentials = WalletUtils.loadCredentials(password, walletDir);
            TransactionReceipt receipt = Transfer.sendFunds(web3,credentials,"0x31B98D14007bDEe637298086988A0bBd31184523",new BigDecimal(1), Convert.Unit.ETHER).sendAsync().get();
            toastAsync("Transaction complete: " +receipt.getTransactionHash());
        }
        catch (Exception e)
        {
            toastAsync(e.getMessage());
        }
    }
    public void toastAsync(String message)
    {
        runOnUiThread(() ->
        {
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        });
    }
}
