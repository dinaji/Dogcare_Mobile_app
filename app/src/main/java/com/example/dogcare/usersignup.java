package com.example.dogcare;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dogcare.databinding.ActivityUsersignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class usersignup extends AppCompatActivity {


    Button rbtn;

    FirebaseAuth mAuth;
    ActivityUsersignupBinding binding;
    EditText Name, Email, Password;
    FirebaseDatabase db;
    DatabaseReference reference;

    private Button login;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), userlogin.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUsersignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        Name=(EditText)findViewById(R.id.Name);
        Email=(EditText)findViewById(R.id.Email);
        Password=(EditText) findViewById(R.id.Password);
        rbtn=findViewById(R.id.rbtn) ;

        login=(Button)findViewById(R.id.login);


        binding.rbtn.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View v) {

                String Name, Email, Password;

                Name=binding.Name.getText().toString();
                Email =binding.Email.getText().toString();
                Password =binding.Password.getText().toString();

                //validations
                if(TextUtils.isEmpty(binding.Email.getText().toString())){
                    binding.Email.setError("Email is required");
                    return;

                }else if(Email.isEmpty() || !Email.contains("@")){
                    binding.Email.setError("Email is not vallid");
                    return;
                }

                if(TextUtils.isEmpty(binding.Password.getText().toString())){
                    binding.Password.setError("Password is reqiured");
                    return;
                }else if (Password.isEmpty()|| Password.length()<7){
                    binding.Password.setError("Password must be 7 character");
                }



                if(!Name.isEmpty() && !Email.isEmpty() && !Password.isEmpty()){

                    Users users=new Users(Name,Email,Password);
                    db=FirebaseDatabase.getInstance();
                    reference= db.getReference("Users");
                    reference.child(Name).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {


                            binding.Name.setText("");
                            binding.Email.setText("");
                            binding.Password.setText("");
                            Toast.makeText(usersignup.this, "Succesfully updated...", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(usersignup.this, userlogin.class);
                            startActivity(intent);
                            finish();

                        }
                    });

                }else{
                    Toast.makeText(usersignup.this, "Authentication failed.",
                            Toast.LENGTH_SHORT).show();

                }



            }




        });


login.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(usersignup.this, userlogin.class);
        startActivity(intent);

    }
});









    }
}