package com.all_product.commerce;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.all_product.commerce.Fragments.HomeFragment;
import com.all_product.commerce.Fragments.MyOrdersFragment;
import com.all_product.commerce.Utils.Methods;
import com.all_product.commerce.Utils.Notify;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener, Notify {

    private Methods methods;
    private BottomNavigationView bottomNavigationView;

    private void bindView() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        methods = new Methods(this);
        setContentView(R.layout.activity_main);
        bindView();

        bottomNavigationView.setOnItemSelectedListener(this);

        loadFragment(new HomeFragment());

    }

    private void loadFragment(Fragment fragment) {

        if (fragment != null) {

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.frameContainer, fragment);
            //transaction.addToBackStack(null);
            transaction.commit();

        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;

        switch (item.getItemId()) {
            case R.id.home:
                fragment = new HomeFragment();
                break;
            case R.id.myOrders:
                fragment = new MyOrdersFragment();
                break;
        }

        loadFragment(fragment);

        return false;
    }

    @Override
    public void notifyAsSave() {
        loadFragment(new HomeFragment());
    }
}