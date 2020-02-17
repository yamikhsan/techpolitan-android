package com.yami.studio.banana.jsonapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yami.studio.banana.jsonapp.R;
import com.yami.studio.banana.jsonapp.activity.DetailActivity;
import com.yami.studio.banana.jsonapp.entity.Product;

import java.util.List;

import static com.yami.studio.banana.jsonapp.activity.DetailActivity.EXTRA_DATA;

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

        private Context context;
        private ImageView img;
        private TextView productName, merchantName;
        private ProgressBar progressImage;
        private Product product;

        MainHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_product);
            productName = itemView.findViewById(R.id.tv_product_name);
            merchantName = itemView.findViewById(R.id.tv_merchant_name);
            progressImage = itemView.findViewById(R.id.progress_image);

            context = itemView.getContext();

            itemView.setOnClickListener(listener);
        }

        void onBind(Product product){
            this.product = product;
            productName.setText(product.getName());
            merchantName.setText(product.getMerchant().getName());
            Picasso.get()
                    .load(product.getImage())
                    .error(R.drawable.ic_launcher_background)
                    .fit()
                    .into(img, new Callback() {
                        @Override
                        public void onSuccess() {
                            progressImage.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError(Exception e) {
                            progressImage.setVisibility(View.INVISIBLE);
                            e.printStackTrace();
                        }
                    });
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DetailActivity.class);
                i.putExtra(EXTRA_DATA, product);
                context.startActivity(i);
            }
        };

    }
}
