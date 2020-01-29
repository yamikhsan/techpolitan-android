package com.studio.yami.banana.calculatevolume;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView ivSplash = findViewById(R.id.iv_splash);
        TextView tvSplash = findViewById(R.id.tv_spalsh);

        ScaleAnimation scale = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(1000);
        scale.setStartOffset(500);

        AlphaAnimation alpha = new AlphaAnimation(0f, 1f);
        alpha.setDuration(1000);
        alpha.setStartOffset(1500);

        ivSplash.setAnimation(scale);
        tvSplash.setAnimation(alpha);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        }, 3000);

    }
}
