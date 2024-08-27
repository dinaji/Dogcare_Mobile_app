package com.example.dogcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class educational extends AppCompatActivity {

    private Button e1;
    private Button e2;
    private Button e3;
    private Button e4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_educational);

        e1=(Button)findViewById(R.id.btnbreeds);
        e2=(Button)findViewById(R.id.btnlife) ;
        e3=(Button)findViewById(R.id.btndietary) ;
        e4=(Button)findViewById(R.id.btntips);



        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(educational.this,dogtype.class);
                startActivity(intent);

            }
        });


        e2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(educational.this,life.class);
                startActivity(intent);

            }
        });



        e3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(educational.this,dogrequirement.class);
                startActivity(intent);

            }
        });


        e4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(educational.this,healthtips.class);
                startActivity(intent);

            }
        });













        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}