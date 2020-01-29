package com.studio.yami.banana.activityonresult;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public final static String EXTRA_MAIN = "MainActivity EXTRA MAIN";
    public final static String EXTRA_RECEIVED = "MainActivity EXTRA RECEIVED";
    public final static int TEXT_REQ = 1;

    TextView head, body;
    EditText msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        head = findViewById(R.id.tv_main_head);
        body = findViewById(R.id.tv_main_body);
        msg = findViewById(R.id.edit_main);

    }

    public void onSend(View view) {

        String m = msg.getText().toString();
        Intent i = new Intent(this, ReceivedActivity.class);
        i.putExtra(EXTRA_MAIN, m);
        startActivityForResult(i, TEXT_REQ);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQ) {
            if (resultCode == RESULT_OK) {

                String r = data.getStringExtra(EXTRA_RECEIVED);
                if(!TextUtils.isEmpty(r)){

                    head.setVisibility(View.VISIBLE);
                    body.setVisibility(View.VISIBLE);
                    body.setText(r);

                }


            }
        }

    }
}




