package com.studio.yami.banana.calculatevolume;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_NAME = "MainActivity EXTRA NAME";
    private EditText edName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edName = findViewById(R.id.edit_name);
    }

    public void onClick(View view) {

        String strName = edName.getText().toString();
        if(strName.equals("")){
            edName.setError("Harus di isi");
        } else {
            Intent tri = new Intent(this, MenuActivity.class);
            tri.putExtra(EXTRA_NAME, strName);
            startActivity(tri);
        }

    }
}
