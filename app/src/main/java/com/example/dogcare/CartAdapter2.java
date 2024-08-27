package com.example.dogcare;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CartAdapter2 extends RecyclerView.Adapter<CartAdapter2.CartViewHolder> {

    private List<CartItem> cartItemList;

    public CartAdapter2(List<CartItem> cartItemList) {
        this.cartItemList = cartItemList;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem item = cartItemList.get(position);
        holder.productName.setText(item.getName());
        holder.productQuantity.setText("Quantity: " + item.getQuantity());
        holder.productPrice.setText("Price: $" + item.getPrice());
    }

    @Override
    public int getItemCount() {
        return cartItemList.size();
    }

    static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productQuantity, productPrice;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.productName);
            productQuantity = itemView.findViewById(R.id.productQuantity);
            productPrice = itemView.findViewById(R.id.productPrice);
        }
    }
}
