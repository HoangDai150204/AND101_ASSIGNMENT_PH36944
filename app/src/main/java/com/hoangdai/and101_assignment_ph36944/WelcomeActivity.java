package com.hoangdai.and101_assignment_ph36944;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        new CountDownTimer(3000,3000){

            @Override
            public void onTick(long millisUntilFinished) {
                //trong vòng bao nhiêu giây thực hiện 1 công việc nào đó

            }

            @Override
            public void onFinish() {
                //
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}