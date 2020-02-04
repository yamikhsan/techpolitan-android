package com.studio.yami.onion.fragmentapp.viewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.studio.yami.onion.fragmentapp.R;

public class ModelActivity extends AppCompatActivity {

    private TextView tvCount;

    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model);

        model = new ViewModelProvider(this).get(MainViewModel.class);

        tvCount = findViewById(R.id.tv_count);
        tvCount.setText(model.getCount());

        Button btnCount = findViewById(R.id.btn_count);
        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.setCount();
                tvCount.setText(model.getCount());
            }
        });

    }
}
