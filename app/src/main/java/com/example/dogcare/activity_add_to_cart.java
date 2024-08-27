package com.example.dogcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_add_to_cart extends AppCompatActivity {

    private ImageView productImage;
    private TextView productName, productPrice;
    private EditText productQuantity;
    private Button addToCartButton;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_cart);

        productImage = findViewById(R.id.productImage);
        productName = findViewById(R.id.productName);
        productPrice = findViewById(R.id.productPrice);
        productQuantity = findViewById(R.id.productQuantity);
        addToCartButton = findViewById(R.id.addToCartButton);


        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("cartItems");

        // Set sample data (in real app, data would come from an intent or database)
        productImage.setImageResource(R.drawable.nu1dr);
        productName.setText("Sample Product");
        productPrice.setText("Price: $10.00");

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String quantityStr = productQuantity.getText().toString();
                if (quantityStr.isEmpty() || Integer.parseInt(quantityStr) <= 0) {
                    Toast.makeText(activity_add_to_cart.this, "Please enter a valid quantity", Toast.LENGTH_SHORT).show();
                } else {
                    int quantity = Integer.parseInt(quantityStr);
                    addItemToCart(new CartItem("Sample Product", quantity, 10.00));
                    Toast.makeText(activity_add_to_cart.this, "Added to cart", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity_add_to_cart.this,Buy.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void addItemToCart(CartItem cartItem) {
        // Generate a unique key for each item
        String key = databaseReference.push().getKey();
        assert key != null;
        databaseReference.child(key).setValue(cartItem);
    }
}