package com.example.code;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        //// email address and passwords
        EditText mail = (EditText) findViewById(R.id.loginEmailAddress);
        EditText pass = (EditText) findViewById(R.id.loginPassword);



        //// log in Button
        Button logInButton = (Button) findViewById(R.id.logInSuccess);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                //// email address and passwords
                EditText mail = (EditText) findViewById(R.id.loginEmailAddress);
                EditText pass = (EditText) findViewById(R.id.loginPassword);

                String mail1 = mail.getText().toString().trim();
                String pass1 = pass.getText().toString().trim();

                if (mail.getText().toString().equals("") || pass.getText().toString().equals("") || pass.getText().toString().length()<7)
                {
                    Toast.makeText(getApplicationContext(), "All Fields should contain data (Password should be 7 or more)", Toast.LENGTH_SHORT).show();
                } else
                {
                    firebaseAuth.signInWithEmailAndPassword(mail1, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Log In Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent = new Intent(MainActivity.this, Interface.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "Some thing Went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });



        //// to the sign up page
        Button signupPage = (Button) findViewById(R.id.toTheSignupPage);
        signupPage.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "New User, Welcome to Code Snippets", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(MainActivity.this, signUp.class);
                startActivity(intent);
            }
        });



    }
}