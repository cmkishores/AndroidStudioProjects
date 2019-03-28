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
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigDecimal;


public class Home extends Activity
{
   private String[] labels;
    private TextView mTextMessage;
    private Web3j web3;
    //FIXME: Add your own password here
    private final String password = "medium";
    private String walletPath;
    private File walletDir;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            switch (item.getItemId())
            {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Resources res = getResources();
        labels = res.getStringArray(R.array.headings);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
                                    int position, long id)
            {

                // Sending image id to FullScreenActivity
                Intent i = new Intent(getApplicationContext(), ProductActivity.class);
                // passing array index
                i.putExtra("id", position);
                startActivity(i);

            }

        });

    }
    public void connectToEthNetwork(View v)
    {
        toastAsync("Connecting to Ethereum network...");
        // FIXME: Add your own API key here
        web3 = Web3j.build(new HttpService("https://rinkeby.infura.io/v3/YOURKEY"));
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
            TransactionReceipt receipt = Transfer.sendFunds(web3,credentials,"0x31B98D14007bDEe637298086988A0bBd31184523",new BigDecimal(1),Convert.Unit.ETHER).sendAsync().get();
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