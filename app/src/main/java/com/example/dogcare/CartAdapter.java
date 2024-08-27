package com.example.dogcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
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

