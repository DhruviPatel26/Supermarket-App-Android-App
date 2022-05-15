package com.all_product.commerce.Utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.all_product.commerce.R;


public class Alert_dialog extends Dialog {
    private TextView txtAlertTitle;
    private TextView txtAlertBody;
    private TextView btnAlertPositive;
    private RelativeLayout iv_x;

    public Alert_dialog(@NonNull Context context) {
        super(context);
    }

    DoInBackGroundCaller callbackHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.alert_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        setCancelable(false);
        iv_x = (RelativeLayout) findViewById(R.id.iv_x);

        iv_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        txtAlertTitle = (TextView) findViewById(R.id.txtAlertTitle);
        txtAlertBody = (TextView) findViewById(R.id.txtAlertBody);
        btnAlertPositive = (TextView) findViewById(R.id.btnAlertPositive);
        btnAlertPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                if (callbackHandler != null) {
                    callbackHandler.doTask();
                }


            }
        });
    }

    public void show(String title, String message, String buttonText) {
        show();
        txtAlertTitle.setText(title);
        txtAlertBody.setText(message);
        btnAlertPositive.setText(buttonText);


    }

    public void show(String title, String message, String buttonText, DoInBackGroundCaller doInBackGroundCaller) {
        show();
        txtAlertTitle.setText(title);
        txtAlertBody.setText(message);
        btnAlertPositive.setText(buttonText);
        this.callbackHandler = doInBackGroundCaller;

    }


}
