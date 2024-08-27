package com.example.dogcare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class cart extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private List<product> productList;





    private DatabaseReference databaseReference;





    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart1);






        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        productList = new ArrayList<>();
        // Add sample data
        productList.add(new product("Product 1", "Rs:1200.00", R.drawable.nu1dr));
        productList.add(new product("Product 2", "Rs:1000.00", R.drawable.nu2dr));
        productList.add(new product("Product 3", "Rs:1200.00", R.drawable.nu3dr));
        productList.add(new product("Product 4", "Rs:1500.00", R.drawable.nu4wet));
        productList.add(new product("Product 5", "Rs:1000.00", R.drawable.nu5wet));
        productList.add(new product("Product 6", "Rs:1350.00", R.drawable.nu6wet));
        productList.add(new product("Product 7", "Rs:1500.00", R.drawable.nu7gr));
        productList.add(new product("Product 8", "Rs:700.00", R.drawable.nu8gr));
        productList.add(new product("Product 9", "Rs:1400.00", R.drawable.nu9li));
        productList.add(new product("Product 10", "Rs:2000.00", R.drawable.nu10li));
        productList.add(new product("Product 11", "Rs:1000.00", R.drawable.nu11sp));
        productList.add(new product("Product 12", "Rs:900.00", R.drawable.nu12sp));



        productAdapter = new ProductAdapter(productList, this);
        recyclerView.setAdapter(productAdapter);






        findViewById(R.id.view_cart_button).setOnClickListener(v -> {
           Intent intent = new Intent(cart.this, activity_add_to_cart.class);
            startActivity(intent);
       });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public static class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
        private List<product> cartItems;
        private Context context;

        public CartAdapter(List<product> cartItems, Context context) {
            this.cartItems = cartItems;
            this.context = context;
        }

        @NonNull
        @Override
        public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
            return new CartViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
            product product = cartItems.get(position);
            holder.cartName.setText(product.getName());
            holder.cartPrice.setText(product.getPrice());
            holder.cartImage.setImageResource(product.getImageResourceId());





        }

        @Override
        public int getItemCount() {
            return cartItems.size();
        }

        public static class CartViewHolder extends RecyclerView.ViewHolder {
            ImageView cartImage;
            TextView cartName;
            TextView cartPrice;

            public CartViewHolder(@NonNull View itemView) {
                super(itemView);
                cartImage = itemView.findViewById(R.id.cart_image);
                cartName = itemView.findViewById(R.id.cart_name);
                cartPrice = itemView.findViewById(R.id.cart_price);
            }
        }
    }
}