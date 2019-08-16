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


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

public class MainActivity extends AppCompatActivity
{

    private Web3j web3;
    private final String password = "ARSHOPPINGAPP";

    private String walletPath;

    private File walletDir;

    private EditText Username_editText;
    private FirebaseAuth auth;
    private EditText Password_editText;
    private Button login_button;
    private Button register_button;
    SharedPreferences sp;
    boolean t;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        walletPath = getFilesDir().getAbsolutePath();
        walletDir = new File(walletPath);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        sp= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(sp.getBoolean("flag",false))
        {  Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
        }
        auth = FirebaseAuth.getInstance();
        final EditText Name = findViewById(R.id.MailID_editText);
        final EditText Password = findViewById(R.id.Password_editText);
        Button Login = findViewById(R.id.login_button);
        Button Register = findViewById(R.id.rgRegister_Button);
        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final String email = Name.getText().toString().trim();
                final String password = Password.getText().toString().trim();

                Task<AuthResult> authResultTask = auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> authResultTask)
                    {
                        if (authResultTask.isSuccessful())
                        {
                            t = connectToEthNetwork(v);


                            if (t == true)
                            {





                                createWallet(v);



                                SharedPreferences.Editor ed = sp.edit();
                                ed.putBoolean("wflag", (true));
                                ed.commit();
                                Intent intent = new Intent(MainActivity.this, Home.class);
                                startActivity(intent);

                            } else {
                                Log.w("createUserWithEmail:failure", authResultTask.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                        }


                    }

                });


                Register.setOnClickListener(v1 -> {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                });

            }

            public boolean connectToEthNetwork(View v) {
                toastAsync("Connecting to Ethereum network...");
                web3 = Web3j.build(new HttpService("https://ropsten.infura.io/v3/b5000509579e421faeaf38cbfebe84ef"));
                try {
                    Web3ClientVersion clientVersion = web3.web3ClientVersion().sendAsync().get();
                    if (!clientVersion.hasError()) {
                        toastAsync("Connected!");
                        toastAsync("Wallet generated");
                        return true;
                    } else {
                     //   toastAsync(clientVersion.getError().getMessage());
                        return false;
                    }
                } catch (Exception e) {
                  //  toastAsync(e.getMessage());
                }
                return false;
            }

            public void createWallet(View v) {
                try {
                    WalletUtils.generateNewWalletFile(password, walletDir);



                } catch (Exception e) {

                }
            }



            public void toastAsync(String message)
            {
                runOnUiThread(Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG)::show);
            }
        });
    }
}
