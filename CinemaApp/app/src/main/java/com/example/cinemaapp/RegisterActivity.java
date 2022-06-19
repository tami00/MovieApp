package com.example.cinemaapp;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    EditText textName, textPassword, textEmail, textPhoneNo;
    Button submit;
    // FirebaseDatabase rootNode;
    // DatabaseReference ref;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        String userID=  user.getUid();

        textName=(EditText)findViewById(R.id.username);
        textPassword=(EditText)findViewById(R.id.password);
        textEmail=(EditText)findViewById(R.id.email);
        textPhoneNo=(EditText)findViewById(R.id.phoneNo);
        submit = (Button)findViewById(R.id.button);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = textName.getText().toString().trim();
                String email = textEmail.getText().toString().trim();
                String phoneNo = textPhoneNo.getText().toString().trim();
                String password = textPassword.getText().toString().trim();

                if (password.length() < 8) {
                    textPassword.setError("Password must not be shorter than 8 characters");
                }
                if (password.contains(" ")) {
                    textPassword.setError("Password must not contain any spaces");
                }
                if (!email.contains("@")) {
                    textEmail.setError("Please enter a valid email address");
                }
                if (phoneNo.length() <10) {
                    textPhoneNo.setError("Please enter a valid phone number");
                }
                if (phoneNo.length() >10) {
                    textPhoneNo.setError("Please enter a valid phone number");
                }
                if(username.length() ==0){
                    Toast.makeText(RegisterActivity.this, "Please enter a username", Toast.LENGTH_LONG).show();
                }

                if(password.length() > 7 && email.contains("@") && phoneNo.length() ==10 && username.length() !=0) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(task -> {

                                if (task.isSuccessful()) {
                                    RegisterPOJO pojo = new RegisterPOJO(username, email, phoneNo, password);

                                    FirebaseDatabase.getInstance().getReference("users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(pojo).addOnCompleteListener(task1 -> {
                                        if (task1.isSuccessful()) {
                                            Toast.makeText(RegisterActivity.this, "You have successfully signed up", Toast.LENGTH_LONG).show();
                                            Intent intent = new Intent(RegisterActivity.this, HomePageActivity.class);
                                            RegisterActivity.this.startActivity(intent);
                                        }
                                    });
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Failed to register, please try again", Toast.LENGTH_LONG).show();
                                }
                            });
                }
            }
        });

    }
}