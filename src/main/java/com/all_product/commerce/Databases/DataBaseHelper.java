package com.all_product.commerce.Databases;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.all_product.commerce.Models.ItemProduct;
import com.all_product.commerce.Utils.TAGS;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    private Activity activity;
    private static final String DATABASE_NAME = "my_store";

    private static final String PRODUCT_TABLE = "product";
    private static final String CART_TABLE = "cart";

    private static final String CREATE_PRODUCTION = "CREATE TABLE " + PRODUCT_TABLE + " (" + TAGS.TAG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TAGS.TAG_NAME + " TEXT,"
            + TAGS.TAG_DESCRIPTION + " TEXT,"
            + TAGS.TAG_PRICE + " TEXT,"
            + TAGS.TAG_CATEGORY_NAME + " TEXT,"
            + TAGS.TAG_QTY + " TEXT,"
            + TAGS.TAG_RESOURCE_ID + " INTEGER )";

    private static final String CREATE_CART_TABLE = "CREATE TABLE " + CART_TABLE + " (" + TAGS.TAG_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + TAGS.TAG_NAME + " TEXT,"
            + TAGS.TAG_DESCRIPTION + " TEXT,"
            + TAGS.TAG_PRICE + " TEXT,"
            + TAGS.TAG_CATEGORY_NAME + " TEXT,"
            + TAGS.TAG_QTY + " TEXT,"
            + TAGS.TAG_RESOURCE_ID + " INTEGER )";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void initializeDataBase(Activity activity) {
        if (this.activity == null) {
            this.activity = activity;
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PRODUCTION);
        db.execSQL(CREATE_CART_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PRODUCT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CART_TABLE);
        onCreate(db);
    }

    public void insertData(ItemProduct product) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TAGS.TAG_NAME, product.getName());
        values.put(TAGS.TAG_DESCRIPTION, product.getDescription());
        values.put(TAGS.TAG_PRICE, product.getPrice());
        values.put(TAGS.TAG_CATEGORY_NAME, product.getCategoryName());
        values.put(TAGS.TAG_QTY, product.getQty());
        values.put(TAGS.TAG_RESOURCE_ID, product.getImageId());
        database.insert(PRODUCT_TABLE, null, values);
        database.close();
    }


    public void addToCart(ItemProduct product) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TAGS.TAG_NAME, product.getName());
        values.put(TAGS.TAG_DESCRIPTION, product.getDescription());
        values.put(TAGS.TAG_PRICE, product.getPrice());
        values.put(TAGS.TAG_CATEGORY_NAME, product.getCategoryName());
        values.put(TAGS.TAG_QTY, product.getQty());
        values.put(TAGS.TAG_RESOURCE_ID, product.getImageId());
        database.insert(CART_TABLE, null, values);
        database.close();
    }


    public void updateData(String id, ItemProduct product) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TAGS.TAG_NAME, product.getName());
        cv.put(TAGS.TAG_DESCRIPTION, product.getDescription());
        cv.put(TAGS.TAG_PRICE, product.getPrice());
        cv.put(TAGS.TAG_CATEGORY_NAME, product.getCategoryName());
        cv.put(TAGS.TAG_QTY, product.getQty());
        cv.put(TAGS.TAG_RESOURCE_ID, product.getImageId());
        database.update(PRODUCT_TABLE, cv, TAGS.TAG_ID + " = ?", new String[]{id});
    }

    public ArrayList<ItemProduct> retrieveData() {
        SQLiteDatabase database = getWritableDatabase();
        ArrayList<ItemProduct> productList = new ArrayList<>();
        String selectQuery = "Select * from " + PRODUCT_TABLE;

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String price = cursor.getString(3);
                String category = cursor.getString(4);
                String qty = cursor.getString(5);
                int imageId = cursor.getInt(6);
                // Adding contact to list
                productList.add(new ItemProduct(String.valueOf(id), name, description, price, category, qty, imageId));
            } while (cursor.moveToNext());
        }

        return productList;
    }

    public ArrayList<ItemProduct> retrieveCart() {
        SQLiteDatabase database = getWritableDatabase();
        ArrayList<ItemProduct> productList = new ArrayList<>();
        String selectQuery = "Select * from " + CART_TABLE;

        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(0);
                String name = cursor.getString(1);
                String description = cursor.getString(2);
                String price = cursor.getString(3);
                String category = cursor.getString(4);
                String qty = cursor.getString(5);
                int imageId = cursor.getInt(6);
                // Adding contact to list
                productList.add(new ItemProduct(String.valueOf(id), name, description, price, category, qty, imageId));
            } while (cursor.moveToNext());
        }

        return productList;
    }

    public int deleteData(String id) {
        SQLiteDatabase database = getWritableDatabase();
        int i = database.delete(PRODUCT_TABLE, "ID = ?", new String[]{id});
        database.close();
        return i;
    }

    public int removeFromCart(String id) {
        SQLiteDatabase database = getWritableDatabase();
        int i = database.delete(CART_TABLE, "ID = ?", new String[]{id});
        database.close();
        return i;
    }
}
