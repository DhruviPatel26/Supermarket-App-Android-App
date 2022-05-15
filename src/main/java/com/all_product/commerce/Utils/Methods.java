package com.all_product.commerce.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.all_product.commerce.Databases.DataBaseHelper;
import com.all_product.commerce.Models.ItemProduct;
import com.all_product.commerce.R;

public class Methods {

    private Activity activity;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private DataBaseHelper dataBaseHelper;


    public Methods(Activity activity) {
        this.activity = activity;
        this.dataBaseHelper = new DataBaseHelper(activity.getApplicationContext());
        this.sharedPreferences = activity.getSharedPreferences("mAppPref", Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }


    public void initApp() {

        if (sharedPreferences.getBoolean("isAppFirst", true)) {
            initHome();
            editor.putBoolean("isAppFirst", false);
            editor.apply();
        }
    }

    private void initHome() {

        dataBaseHelper.insertData(new ItemProduct("1", "Chair - sleeping", "Sleeping and relax Chair for home", "5999", "Home & Kitchen","1", R.drawable.chair_1));
        dataBaseHelper.insertData(new ItemProduct("1", "Office Chair", "The professional office chair - for employee and staffs", "2599", "Home & Kitchen","1", R.drawable.chair_2));
        dataBaseHelper.insertData(new ItemProduct("1", "Bracelet", "24 karat gold bracelet with some new Gifts", "125000", "Gold","1", R.drawable.bracelet_1));
        dataBaseHelper.insertData(new ItemProduct("1", "Watch-Silver", "The professional watch for man, silver color and lather belt", "360","1", "Fashion", R.drawable.watch_1));
        dataBaseHelper.insertData(new ItemProduct("1", "Watch for Man", "The mechanism watch for boys and stylish manufacture", "299", "Fashion","1", R.drawable.watch_2));

    }

    public String getMultiplyPrice(String price, String numOfMultiply) {

        int varPrice = Integer.parseInt(price);
        int varNum = Integer.parseInt(numOfMultiply);

        int result = varPrice * varNum;

        return String.valueOf(result);
    }
}
