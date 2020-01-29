package com.studio.yami.banana.activityonresult;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static com.studio.yami.banana.activityonresult.MainActivity.EXTRA_MAIN;
import static com.studio.yami.banana.activityonresult.MainActivity.EXTRA_RECEIVED;

public class ReceivedActivity extends AppCompatActivity {

    TextView head, body;
    EditText msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiver);

        head = findViewById(R.id.tv_received_head);
        body = findViewById(R.id.tv_received_body);
        msg = findViewById(R.id.edit_received);

        String iMsg = getIntent().getStringExtra(EXTRA_MAIN);
        if(!TextUtils.isEmpty(iMsg)){

            head.setVisibility(View.VISIBLE);
            body.setVisibility(View.VISIBLE);
            body.setText(iMsg);

        }

    }

    public void onReply(View view) {

        String m = msg.getText().toString();
        Intent i = new Intent();
        i.putExtra(EXTRA_RECEIVED, m);
        setResult(RESULT_OK, i);
        finish();

    }
}
