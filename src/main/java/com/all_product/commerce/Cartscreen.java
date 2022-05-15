package com.all_product.commerce;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.all_product.commerce.Databases.DataBaseHelper;
import com.all_product.commerce.Models.ItemProduct;
import com.all_product.commerce.Utils.Alert_dialog;
import com.all_product.commerce.Utils.DoInBackGroundCaller;
import com.all_product.commerce.Utils.TAGS;
import com.google.android.material.textfield.TextInputEditText;

import de.hdodenhof.circleimageview.CircleImageView;

public class Cartscreen extends AppCompatActivity {
    CircleImageView productImage;
    RadioButton online, cod;
    RelativeLayout rl_prepaid, rl_cod;
    TextInputEditText edtName, edtMobile;
    TextInputEditText editNameOnCard, editCardNumber, editExpiryDate, editCVVCode;
    String name, mobile, cardNumber, nameOnCard, expiryDate, cvvCode;
    Button btn_save;
    DataBaseHelper dataBaseHelper;
    RadioGroup radioGroup;
    String qty = "1";
    boolean isFromCard = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent().hasExtra("qty")) {
            qty = getIntent().getStringExtra("qty");
        }
        setContentView(R.layout.activity_cartscreen);
        dataBaseHelper = new DataBaseHelper(this);


        productImage = findViewById(R.id.productImage);
        rl_prepaid = findViewById(R.id.rl_prepaid);
        radioGroup = findViewById(R.id.radioGroup);
        rl_cod = findViewById(R.id.rl_cod);
        cod = findViewById(R.id.cod);
        online = findViewById(R.id.online);
        edtName = findViewById(R.id.edtName);
        edtMobile = findViewById(R.id.edtMobile);
        btn_save = findViewById(R.id.btn_save);
        editCardNumber = findViewById(R.id.edtCardDrtails);
        editNameOnCard = findViewById(R.id.edtNameCard);
        editExpiryDate = findViewById(R.id.expiryDate);
        editCVVCode = findViewById(R.id.cvvCode);

        productImage.setImageResource(TAGS.product.getImageId());

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.online:
                        isFromCard = true;
                        rl_prepaid.setVisibility(View.VISIBLE);
                        rl_cod.setVisibility(View.GONE);
                        break;
                    case R.id.cod:
                        isFromCard = false;
                        rl_prepaid.setVisibility(View.GONE);
                        rl_cod.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = edtName.getText().toString();
                mobile = edtMobile.getText().toString();
                cardNumber = editCardNumber.getText().toString();
                nameOnCard = editNameOnCard.getText().toString();
                expiryDate = editExpiryDate.getText().toString();
                cvvCode = editCVVCode.getText().toString();

                if (TextUtils.isEmpty(name)) {
                    new Alert_dialog(Cartscreen.this).show("Alert", "Name mendatory.", "OK", null);
                } else if (TextUtils.isEmpty(mobile)) {
                    new Alert_dialog(Cartscreen.this).show("Alert", "Mobile no mendatory.", "OK", null);
                } else if (isFromCard && cardNumber.length() > 16 || cardNumber.length() < 16) {
                    new Alert_dialog(Cartscreen.this).show("Alert", "Card no must be 16 digit.", "OK", null);
                }
                /*else if(cardNumber.length() > 16 || cardNumber.length() < 16){
                    new Alert_dialog(Cartscreen.this).show("Alert", "Card no must be 16 digit.", "OK", null);
                }*/
                else if (isFromCard && TextUtils.isEmpty(expiryDate)) {
                    new Alert_dialog(Cartscreen.this).show("Alert", "Date mendatory.", "OK", null);
                } else if (isFromCard && TextUtils.isEmpty(cvvCode)) {
                    new Alert_dialog(Cartscreen.this).show("Alert", "CVV no mendatory.", "OK", null);
                } else {
                    dataBaseHelper.insertData(new ItemProduct(TAGS.product.getId(),
                            TAGS.product.getName(),
                            TAGS.product.getDescription(),
                            TAGS.product.getPrice(),
                            TAGS.product.getCategoryName(),
                            qty,
                            TAGS.product.getImageId()));
                    new Alert_dialog(Cartscreen.this).show("Sucess", "Order placed sucessfully.", "OK", new DoInBackGroundCaller() {
                        @Override
                        public void doTask() {
                            startActivity(new Intent(Cartscreen.this, MainActivity.class));
                            finishAffinity();
                        }

                        @Override
                        public void doTaskComplete() {

                        }
                    });

                }
            }
        });


    }
}