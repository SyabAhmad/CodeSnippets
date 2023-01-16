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

public class signUp extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        Button signupButton = (Button) findViewById(R.id.signupSuccess);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText signupEmail = (EditText) findViewById(R.id.signupEmailAddress);
                EditText signuppass = (EditText) findViewById(R.id.signupPassword);

                String mail1 = signupEmail.getText().toString().trim();
                String password1 = signuppass.getText().toString().trim();

                firebaseAuth = FirebaseAuth.getInstance();

                if (signupEmail.getText().toString().equals("") || signuppass.getText().toString().equals("") || signuppass.getText().toString().length()<7)
                {
                    Toast.makeText(getApplicationContext(), "All Fields should contain data (Password should be 7 or more)", Toast.LENGTH_SHORT).show();
                } else
                {
                    firebaseAuth.createUserWithEmailAndPassword(mail1,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(), "Sign Up Successfully", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent intent = new Intent(signUp.this, MainActivity.class);
                                startActivity(intent);
                            } else
                            {
                                Toast.makeText(getApplicationContext(), "Some thing went Wrong", Toast.LENGTH_SHORT).show();
                            }
                            signupEmail.setText("");
                            signuppass.setText("");
                        }
                    });

                }
            }
        });


        //// to the log in page
        Button loginPage = (Button) findViewById(R.id.toTheLoginPage);
        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Existing User, Welcome back to Code Snippets", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(signUp.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}