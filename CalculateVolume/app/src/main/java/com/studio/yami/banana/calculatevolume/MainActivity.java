package com.studio.yami.banana.calculatevolume;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final String LENGHT = "STATE LENGHT";
    private final String WIDTH = "STATE WIDTH";
    private final String HEIGHT = "STATE HEIGHT";
    private final String RESULT = "STATE RESULT";

    private EditText eLenght, eWidth, eHeight;
    private TextView tvResult;
    private Button btnCount;

    private double lenght, width, height, result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        checkState(savedInstanceState);

        Log.d("Calculate", "Height => " + height);

        btnCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getText();

                result = (lenght * height * width) / 2;
                tvResult.setText(String.valueOf(result));

                resetText();
            }
        });

    }

    private void init(){
        eLenght = findViewById(R.id.edit_lenght);
        eWidth = findViewById(R.id.edit_width);
        eHeight = findViewById(R.id.edit_height);
        tvResult = findViewById(R.id.tv_result);
        btnCount = findViewById(R.id.btn_count);
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
        eLenght.setText("");
        eHeight.setText("");
        eWidth.setText("");
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

            setText(height, eHeight);
            setText(width, eWidth);
            setText(lenght, eLenght);
            setText(result, tvResult);

        }

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
}
