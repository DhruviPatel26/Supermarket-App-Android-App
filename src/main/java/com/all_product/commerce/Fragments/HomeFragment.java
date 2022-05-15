package com.all_product.commerce.Fragments;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.all_product.commerce.Adapters.HomeAdapters;
import com.all_product.commerce.Databases.DataBaseHelper;
import com.all_product.commerce.DetailActivity;
import com.all_product.commerce.Models.ItemProduct;
import com.all_product.commerce.R;
import com.all_product.commerce.Utils.Methods;
import com.all_product.commerce.Utils.TAGS;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private Methods methods;
    private HomeAdapters homeAdapters;
    private DataBaseHelper dataBaseHelper;
    ArrayList<ItemProduct> productArrayList = new ArrayList<>();

    public HomeFragment() {
        // Required empty public constructor
    }

    private void bindView() {
        recyclerView = rootView.findViewById(R.id.recyclerView);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        methods = new Methods(requireActivity());
        dataBaseHelper = new DataBaseHelper(requireActivity());
        //productArrayList = dataBaseHelper.retrieveData();
        bindView();

        new InitArray().execute();

        //setUpRecycler();

        return rootView;
    }

    private class InitArray extends AsyncTask<Void, Void, ArrayList<ItemProduct>> {
        @Override
        protected ArrayList<ItemProduct> doInBackground(Void... voids) {

            productArrayList.add(new ItemProduct("1", "Chair - sleeping", "Sleeping and relax Chair for home", "5999", "Home & Kitchen","1", R.drawable.chair_1));
            productArrayList.add(new ItemProduct("1", "Office Chair", "The professional office chair - for employee and staffs", "2599", "Home & Kitchen","1", R.drawable.chair_2));
            productArrayList.add(new ItemProduct("1", "Bracelet", "24 karat gold bracelet with some new Gifts", "125000", "Gold","1", R.drawable.bracelet_1));
            productArrayList.add(new ItemProduct("1", "Watch-Silver", "The professional watch for man, silver color and lather belt", "360", "Fashion","1", R.drawable.watch_1));
            productArrayList.add(new ItemProduct("1", "Watch for Man", "The mechanism watch for boys and stylish manufacture", "299", "Fashion","1", R.drawable.watch_2));

            return productArrayList;
        }

        @Override
        protected void onPostExecute(ArrayList<ItemProduct> itemProducts) {

            if (itemProducts != null) {
                setUpRecycler();
            }

            super.onPostExecute(itemProducts);
        }
    }

    private void setUpRecycler() {

        homeAdapters = new HomeAdapters(requireActivity(), productArrayList, new HomeAdapters.OnClick() {
            @Override
            public void onItemClick(int position, ItemProduct product) {

                TAGS.product = product;
                startActivity(new Intent(requireActivity(), DetailActivity.class));

            }

            @Override
            public void onAddToCartClick(int position, ItemProduct product) {
                dataBaseHelper.addToCart(product);
            }
        }, false);
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 1, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(homeAdapters);
    }
}