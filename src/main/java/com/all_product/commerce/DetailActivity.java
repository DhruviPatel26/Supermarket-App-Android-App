package com.all_product.commerce;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.all_product.commerce.Utils.Methods;
import com.all_product.commerce.Utils.TAGS;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    CircleImageView imageView;
    TextView name, category, price, description,totalprice;
    Spinner spinner;
    String qty = "1";
    Methods methods;
    String[] arrayOrQty = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        methods = new Methods(this);
        setContentView(R.layout.activity_detail);

        name = findViewById(R.id.name);
        category = findViewById(R.id.category);
        price = findViewById(R.id.price);
        totalprice = findViewById(R.id.totalprice);
        description = findViewById(R.id.description);
        imageView = findViewById(R.id.imageProduct);
        spinner = findViewById(R.id.spinner);

        imageView.setImageResource(TAGS.product.getImageId());
        name.setText("Category For : "+TAGS.product.getName());
        category.setText("Category : "+TAGS.product.getCategoryName());
        price.setText("Price : "+TAGS.product.getPrice());
        totalprice.setText("Price :"+TAGS.product.getPrice());
         description.setText("Description : "+TAGS.product.getDescription());

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Cartscreen.class).putExtra("qty", qty));
            }
        });


        spinner.setAdapter(new ArrayAdapter<String>(DetailActivity.this, android.R.layout.simple_spinner_dropdown_item, arrayOrQty));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                qty = arrayOrQty[position];
                totalprice.setText("Total : "+methods.getMultiplyPrice(TAGS.product.getPrice(), arrayOrQty[position]));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


}