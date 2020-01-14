package com.studio.yami.banana.counterapp;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final String COUNT = "MY_COUNT";
    private TextView tvCount;

    private int count, orientation;
    private float defaultCount, smallCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCount = findViewById(R.id.tv_count);
        Button btnCount = findViewById(R.id.btn_count);
        Button btnReset = findViewById(R.id.btn_reset);

        defaultCount = getResources().getDimensionPixelSize(R.dimen.default_count);
        smallCount = getResources().getDimensionPixelSize(R.dimen.small_count);

        orientation = getResources().getConfiguration().orientation;

        btnCount.setOnClickListener(this);
        btnReset.setOnClickListener(this);

        if(savedInstanceState != null){
            count = savedInstanceState.getInt(COUNT);
        } else {
            count = 0;
        }

        tvCount.setText(String.valueOf(count));
        checkDimen();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_count:
                count += 50;
                checkDimen();
                break;
            case R.id.btn_reset:
                count = 0;
                if(orientation == Configuration.ORIENTATION_PORTRAIT){
                    tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX, defaultCount);
                }
                break;

        }
        tvCount.setText(String.valueOf(count));
    }

    private void checkDimen(){
        if(count >= 1000){
            tvCount.setTextSize(TypedValue.COMPLEX_UNIT_PX, smallCount);
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(COUNT, count);
    }
}
