package com.example.cinemaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    EditText textPassword, textEmail;
    Button submit;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();

        textPassword=(EditText)findViewById(R.id.signup_password);
        textEmail=(EditText)findViewById(R.id.signup_email);

        submit = (Button)findViewById(R.id.signup_button);

        submit.setOnClickListener(v -> {
            String email = textEmail.getText().toString();
            String password = textPassword.getText().toString();

            if(email.isEmpty()) {
                textEmail.setError("Email is required");
            }
            if(password.isEmpty()){
                textPassword.setError("Password is required");
            }
            else {
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

                    if(task.isSuccessful()){
                        Intent intent = new Intent(SignInActivity.this, HomePageActivity.class);
                        SignInActivity.this.startActivity(intent);
                    }else{
                        Toast.makeText(SignInActivity.this, "Failed to login, please try again", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}