package com.example.dogcare;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Buy extends AppCompatActivity {

    private ListView listViewItems;
    private TextView textViewTotalPrice;
    private Button buttonCheckout;

    private List<String> items;
    private List<Double> prices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);

        listViewItems = findViewById(R.id.listViewItems);
        textViewTotalPrice = findViewById(R.id.textViewTotalPrice);
        buttonCheckout = findViewById(R.id.check);

        // Sample data: Items and their prices
        items = new ArrayList<>();
        prices = new ArrayList<>();

        items.add("product 1");
        prices.add(1200.00);

       // items.add("Item 2");
       // prices.add(20.00);

       // items.add("Item 3");
       // prices.add(30.00);

        // Populate ListView with item names
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listViewItems.setAdapter(adapter);

        // Calculate total price
        double totalPrice = calculateTotalPrice();
        textViewTotalPrice.setText("Total: $" + String.format("%.2f", totalPrice));

        // Set OnClickListener for the checkout button
        buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the checkout process
                Toast.makeText(Buy.this, "Proceeding to payment...", Toast.LENGTH_SHORT).show();
                // You can add payment gateway integration here
            }
        });
    }

    private double calculateTotalPrice() {
        double total = 0.0;
        for (double price : prices) {
            total += price;
        }
        return total;
    }
}
