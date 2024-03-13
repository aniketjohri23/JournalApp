package com.example.journalapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    //widgets
    Button loginbtn,createaccountbtn;
    private EditText emailET,passET;


    private FirebaseAuth firebaseAuth;

    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseUser currUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createaccountbtn = findViewById(R.id.create);

        createaccountbtn.setOnClickListener(v ->{
            Intent i = new Intent(MainActivity.this,SignUpActivity.class);
            startActivity(i);
        });


        //login

        loginbtn = findViewById(R.id.login);
        emailET = findViewById(R.id.email);
        passET = findViewById(R.id.password);

        //Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance();

        loginbtn.setOnClickListener(v -> {
            logEmailPassUser(
                    emailET.getText().toString().trim(),
                    passET.getText().toString().trim()
            );
        });


    }

    private void logEmailPassUser(String email, String pass) {
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
            firebaseAuth.signInWithEmailAndPassword(email,pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {

                    FirebaseUser user = firebaseAuth.getCurrentUser();

                    Intent i = new Intent(MainActivity.this, JournalListActivity.class);
                    startActivity(i);

                }
            });
        }

    }
}