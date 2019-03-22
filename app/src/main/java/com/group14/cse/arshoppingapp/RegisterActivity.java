package com.group14.cse.arshoppingapp;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity
{

    private FirebaseAuth auth;
    private EditText rgEmailID_editText;
    private EditText rgPassword_editText;
    private Button rgRegister_Button;
    private Button login_button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        Button  Register =  findViewById(R.id.rgRegister_Button);
        Button Login = findViewById(R.id.login_button)

        final EditText MailID =  findViewById(R.id.rgEmailID_editText);
        final EditText Password = findViewById(R.id.rgPassword_editText);
        Register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = MailID.getText().toString().trim();
                String password = Password.getText().toString().trim();

                Task<AuthResult> authResultTask= auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                       if  (task.isSuccessful())
                       {
                            Intent intent = new Intent(RegisterActivity.this, Home.class);
                            startActivity(intent);
                       }
                        else
                            {
                            Log.w("createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            }
                    }


                });
            };
        } );
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}

