package com.group14.cse.arshoppingapp;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.File;
import java.math.BigDecimal;


public class PaymentActivity extends AppCompatActivity {


    private Button buy;
    private Web3j web3;
    private final String password = "ARSHOPPINGAPP";

    private String walletPath;

    private File walletDir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Button buy = findViewById(R.id.buybut);
        buy.setOnClickListener(new View.OnClickListener() {
                                   @Override
                                   public void onClick(View v) {
                                       getAddress(v);
                                       sendTransaction(v);

                                   }
                               }
        );

    }

    public void getAddress(View v) {
        try {

            Credentials credentials = WalletUtils.loadCredentials(password, walletDir);
            toastAsync("Your address is " + credentials.getAddress());

        } catch (Exception e) {
            toastAsync(e.getMessage());
        }
    }
    //0xD5e30d4561e8d468E877538072c3DE24c837a829

    public void sendTransaction(View v) {
        try {
            Credentials credentials = WalletUtils.loadCredentials(password, walletDir);
            TransactionReceipt receipt = Transfer.sendFunds(web3, credentials, "0x31B98D14007bDEe637298086988A0bBd31184523", new BigDecimal(1), Convert.Unit.ETHER).sendAsync().get();
            toastAsync("Transaction complete: " + receipt.getTransactionHash());
        } catch (Exception e) {
            toastAsync(e.getMessage());
        }

    }

    public void toastAsync(String message) {
        runOnUiThread(Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG)::show);
    }

}

