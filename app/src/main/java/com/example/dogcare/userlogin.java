package com.example.dogcare;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class userlogin extends AppCompatActivity {

    private EditText email, password;
    private Button login, register;


    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);





        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),usersignup.class);
                startActivity(intent);
                finish();
            }
        });

        // Initialize Firebase Database reference
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

       login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();


                if (TextUtils.isEmpty(Email)) {
                    email.setError("Email is required");
                    return;
                }

                if (TextUtils.isEmpty(Password)) {
                    password.setError("Password is required");
                    return;
                }

                loginUser(Email, Password);
            }
        });
    }

    private void loginUser(final String email, final String password) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean userFound = false;

                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    String dbEmail = userSnapshot.child("email").getValue(String.class);
                    String dbPassword = userSnapshot.child("password").getValue(String.class);
                    String dbUsername = userSnapshot.child("username").getValue(String.class);

                    if (email.equals(dbEmail) && password.equals(dbPassword)) {userFound = true;
                        Toast.makeText(userlogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(userlogin.this, Dashboard.class);
                        intent.putExtra("username", dbUsername);
                        startActivity(intent);
                        finish();
                        break;


                    }
                }

                if (!userFound) {
                    Toast.makeText(userlogin.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(userlogin.this, "Database Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}