package com.all_product.commerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.all_product.commerce.Utils.Confirm_dialog;
import com.all_product.commerce.Utils.DoInBackGroundCaller;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {

        new Confirm_dialog(AboutActivity.this).show("Exit App!", "Are you sure you want to exit App", "YES", "NO", new DoInBackGroundCaller() {
            @Override
            public void doTask() {
                Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                homeIntent.addCategory(Intent.CATEGORY_HOME);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(homeIntent);
            }

            @Override
            public void doTaskComplete() {

            }
        }, new DoInBackGroundCaller() {
            @Override
            public void doTask() {

            }

            @Override
            public void doTaskComplete() {

            }
        });
    }
}