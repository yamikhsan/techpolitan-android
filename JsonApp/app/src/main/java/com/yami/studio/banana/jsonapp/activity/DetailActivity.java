package com.yami.studio.banana.jsonapp.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.yami.studio.banana.jsonapp.R;
import com.yami.studio.banana.jsonapp.entity.Product;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "EXTRA PRODUCT DATA";

    private ImageView image;
    private TextView name, qty, category, merchant;
    private ProgressBar progressImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        Product product = getIntent().getParcelableExtra(EXTRA_DATA);
        if(product != null){

            String _image = product.getImage();
            String _name = product.getName();
            String _qty = String.valueOf(product.getQty());
            String _category = product.getCategory().getName();
            String _merchant = product.getMerchant().getName();

            setImage(_image);

            name.setText(_name);
            qty.setText(_qty);
            category.setText(_category);
            merchant.setText(_merchant);

        }


    }

    private void setImage(String url){

        Picasso.get()
                .load(url)
                .error(R.drawable.ic_launcher_background)
                .fit()
                .into(image, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressImage.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        progressImage.setVisibility(View.GONE);
                        e.printStackTrace();
                    }
                });

    }

    private void init(){
        image = findViewById(R.id.img_detail);
        name = findViewById(R.id.tv_detail_name);
        qty = findViewById(R.id.tv_detail_qty);
        category = findViewById(R.id.tv_detail_category);
        merchant = findViewById(R.id.tv_detail_merchant);
        progressImage = findViewById(R.id.progress_detail);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
