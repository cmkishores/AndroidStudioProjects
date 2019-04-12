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

public class ProductActivity extends AppCompatActivity
{
    Button btn1,btn2;
    boolean t;
    private Web3j web3;
    private final String password = "ARSHOPPINGAPP";

    private String walletPath;

    private File walletDir;


    TextView gridData;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        walletPath = getFilesDir().getAbsolutePath();
        walletDir = new File(walletPath);

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


        btn1.setOnClickListener(v ->
        {
            t= connectToEthNetwork(v);


            if(t==true)
            {


                toastAsync("Wallet generated");


                createWallet(v);
                toastAsync("Wallet generated");
                getAddress(v);
                sendTransaction(v);


            }
        });
    }

    public boolean connectToEthNetwork(View v)
    {
        toastAsync("Connecting to Ethereum network...");
        web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/b5000509579e421faeaf38cbfebe84ef"));
        try
        {
            Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
            if(!clientVersion.hasError())
            {
                toastAsync("Connected!");
                return true;
            }
            else
            {
                toastAsync(clientVersion.getError().getMessage());
                return false;
            }
        }
        catch (Exception e)
        {
            toastAsync(e.getMessage());
        }
        return false;
    }
    public void createWallet(View v)
    {
        try
        {
            WalletUtils.generateNewWalletFile(password, walletDir);
            toastAsync("Wallet generated");

            getAddress(v);

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
                Toast.makeText(this, message, Toast.LENGTH_LONG).show());
    }
}