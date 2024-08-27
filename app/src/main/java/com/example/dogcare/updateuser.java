package com.example.dogcare;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dogcare.databinding.ActivityUpdateuserBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class updateuser extends AppCompatActivity {


    ActivityUpdateuserBinding binding;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdateuserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name=binding.username.getText().toString();
                String Email=binding.useremail.getText().toString();
                String Address=binding.usereaddress.getText().toString();
                String Telephone_number=binding.usertele.getText().toString();

                updataData(Name,Email,Address,Telephone_number);
                



            }

            private void updataData(String name, String email, String address, String telephoneNumber) {

                HashMap User =new HashMap<>();
                User.put("name",name);
                User.put("email",email);
                User.put("Address",address);
                User.put("Telephone_number",telephoneNumber);


                databaseReference = FirebaseDatabase.getInstance().getReference("Users");
                databaseReference.child(name).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {

                        if(task.isSuccessful()){
                            
                            binding.username.setText("");
                            binding.useremail.setText("");
                            binding.usereaddress.setText("");
                            binding.usertele.setText("");

                            Toast.makeText(updateuser.this, "Successfully updated..", Toast.LENGTH_SHORT).show();
                                  


                        }else{

                            Toast.makeText(updateuser.this, "Failed to update..", Toast.LENGTH_SHORT).show();

                        }


                    }
                });



            }
        });


    }
}