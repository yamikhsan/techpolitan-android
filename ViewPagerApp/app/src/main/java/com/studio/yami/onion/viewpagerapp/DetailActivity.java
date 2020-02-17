package com.studio.yami.onion.viewpagerapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.studio.yami.onion.viewpagerapp.entity.MenuEntity;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_MENU = "EXTRA MENU";

    private ImageView img;
    private TextView title, price, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        MenuEntity menu = getIntent().getParcelableExtra(EXTRA_MENU);

        if(menu != null){
            img.setImageResource(menu.getImg());
            title.setText(menu.getName());
            price.setText(menu.getPrice());
            desc.setText(menu.getDesc());
        }

    }

    private void init(){
        img = findViewById(R.id.img_detail);
        title = findViewById(R.id.tv_title_detail);
        price = findViewById(R.id.tv_price_detail);
        desc = findViewById(R.id.tv_desc_detail);
    }
}
