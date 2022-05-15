package com.all_product.commerce;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.all_product.commerce.Utils.Methods;

public class SplashScreen extends AppCompatActivity {

    private Methods methods;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        methods = new Methods(this);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), AboutActivity.class));
                finish();
            }
        }, 2000);

    }
}