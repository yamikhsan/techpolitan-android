package com.studio.yami.banana.intentreceived;

import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvMain = findViewById(R.id.tv_main);

        Uri uri = getIntent().getData();
        if(uri != null){
            tvMain.setText(uri.toString());
        }

    }
}
