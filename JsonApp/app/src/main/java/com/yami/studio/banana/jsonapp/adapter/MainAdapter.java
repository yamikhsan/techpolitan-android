package com.yami.studio.banana.jsonapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yami.studio.banana.jsonapp.R;
import com.yami.studio.banana.jsonapp.entity.Product;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainHolder> {

    private List<Product> products;

    @NonNull
    @Override
    public MainHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MainHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainHolder holder, int position) {
        if(products != null){
            holder.onBind(products.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    public void setProducts(List<Product> products){
        this.products = products;
        notifyDataSetChanged();
    }

    class MainHolder extends RecyclerView.ViewHolder {

        private ImageView img;
        private TextView productName, merchantName;

        MainHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_product);
            productName = itemView.findViewById(R.id.tv_product_name);
            merchantName = itemView.findViewById(R.id.tv_merchant_name);
        }

        void onBind(Product product){
            productName.setText(product.getName());
            merchantName.setText(product.getMerchant().getName());
        }

    }
}
