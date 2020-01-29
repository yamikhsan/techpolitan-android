package com.studio.yami.banana.calculatevolume;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class TriangularPrismActivity extends AppCompatActivity {

    public final String TRI_PRISM_SHARED = "TriangularPrismActivity Shared Preference";

    private final String LENGHT = "STATE LENGHT";
    private final String WIDTH = "STATE WIDTH";
    private final String HEIGHT = "STATE HEIGHT";
    private final String RESULT = "STATE RESULT";

    private EditText eLenght, eWidth, eHeight;
    private TextView tvResult;
    private Button btnCount, btnDelete;

    private double lenght, width, height, result;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangular_prism);

        init();
        checkState(savedInstanceState);

        Log.d("Calculate", "Height => " + height);

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getText();

                result = (lenght * height * width) / 2;
                tvResult.setText(String.valueOf(result));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetText();
            }
        });

    }

    private void setPref(){
        @SuppressLint("CommitPrefEdits")
        SharedPreferences.Editor editor = pref.edit();

        String sL = eLenght.getText().toString();
        String sW = eWidth.getText().toString();
        String sH = eHeight.getText().toString();
        String sR = tvResult.getText().toString();

        float l = getFloat(sL);
        float h = getFloat(sH);
        float w = getFloat(sW);
        float r = getFloat(sR);

        editor.putFloat(LENGHT, l);
        editor.putFloat(HEIGHT, h);
        editor.putFloat(WIDTH, w);
        editor.putFloat(RESULT, r);
        editor.apply();
    }

    private float getFloat(String s){
        float res;
        if(s.equals("")){
            res = 0f;
        } else {
            res = Float.parseFloat(s);
        }
        return res;
    }

    private void getPref(){
        lenght = pref.getFloat(LENGHT, 0f);
        height = pref.getFloat(HEIGHT, 0f);
        width = pref.getFloat(WIDTH, 0f);
        result = pref.getFloat(RESULT, 0f);
    }

    private void init(){
        eLenght = findViewById(R.id.edit_lenght);
        eWidth = findViewById(R.id.edit_width);
        eHeight = findViewById(R.id.edit_height);
        tvResult = findViewById(R.id.tv_result);
        btnCount = findViewById(R.id.btn_count);
        btnDelete = findViewById(R.id.btn_del);

        pref = getSharedPreferences(TRI_PRISM_SHARED, MODE_PRIVATE);
    }

    private void getText(){
        String strLenght = eLenght.getText().toString();
        String strWidth = eWidth.getText().toString();
        String strHeight = eHeight.getText().toString();

        if(strLenght.equals("") || strHeight.equals("") || strWidth.equals("")){
            Toast.makeText(this, "Semua Colom harus diisi", Toast.LENGTH_SHORT).show();
        } else {
            height = Double.valueOf(strHeight);
            width = Double.valueOf(strWidth);
            lenght = Double.valueOf(strLenght);
        }
    }

    private void resetText(){
        eLenght.setText("0");
        eHeight.setText("0");
        eWidth.setText("0");
        tvResult.setText("0");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(LENGHT, lenght);
        outState.putDouble(HEIGHT, height);
        outState.putDouble(WIDTH, width);
        outState.putDouble(RESULT, result);
    }

    private void checkState(Bundle savedInstanceState){

        if(savedInstanceState != null){
            height = savedInstanceState.getDouble(HEIGHT);
            width = savedInstanceState.getDouble(WIDTH);
            lenght = savedInstanceState.getDouble(LENGHT);
            result = savedInstanceState.getDouble(RESULT);
        } else {
            getPref();
        }

        setText(height, eHeight);
        setText(width, eWidth);
        setText(lenght, eLenght);
        setText(result, tvResult);
    }

    private void setText(double num, EditText edit){
        if(num > 0.0){
            edit.setText(String.valueOf(num));
        }
    }

    private void setText(double num, TextView edit){
        if(num > 0.0){
            edit.setText(String.valueOf(num));
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {

        setPref();

        super.onDestroy();
    }
}
