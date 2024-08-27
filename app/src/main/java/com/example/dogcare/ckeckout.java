package com.example.dogcare;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ckeckout extends AppCompatActivity {

    private RecyclerView recyclerViewCart;
    private CartAdapter2 cartAdapter;
    private List<CartItem> cartItemList;
    private TextView totalPrice;
    private Button checkoutButton;


    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);

        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        totalPrice = findViewById(R.id.totalPrice);
        checkoutButton = findViewById(R.id.checkoutButton);

        cartItemList = new ArrayList<>();
        // Sample data
        cartItemList.add(new CartItem("Sample Product", 1, 10.00));
        cartItemList.add(new CartItem("Another Product", 2, 20.00));
        cartItemList.add(new CartItem("Another Product", 2, 20.00));

        cartAdapter = new CartAdapter2(cartItemList);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCart.setAdapter(cartAdapter);


        // Initialize Firebase Database
        databaseReference = FirebaseDatabase.getInstance().getReference("cartItems");

        // Load cart items from Firebase
        loadCartItems();

        updateTotalPrice();

        checkoutButton.setOnClickListener(v -> {

            Toast.makeText(ckeckout.this, "updated", Toast.LENGTH_SHORT).show();


            // Handle checkout logic here
        });
    }

    private void loadCartItems() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                cartItemList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    CartItem item = snapshot.getValue(CartItem.class);
                    cartItemList.add(item);
                }
                cartAdapter.notifyDataSetChanged();
                updateTotalPrice();
            }



            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("CartActivity", "Failed to read cart items", databaseError.toException());
            }
        });

    }

    private void updateTotalPrice() {
        double total = 0;
        for (CartItem item : cartItemList) {
            total += item.getPrice() * item.getQuantity();
        }
        totalPrice.setText("Total: $" + String.format("%.2f", total));
    }
}
