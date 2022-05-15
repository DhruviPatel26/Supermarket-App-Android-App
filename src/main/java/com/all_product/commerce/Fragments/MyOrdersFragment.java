package com.all_product.commerce.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.all_product.commerce.Adapters.HomeAdapters;
import com.all_product.commerce.Databases.DataBaseHelper;
import com.all_product.commerce.Models.ItemProduct;
import com.all_product.commerce.R;
import com.all_product.commerce.Utils.Methods;

import java.util.ArrayList;

public class MyOrdersFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private Methods methods;
    private HomeAdapters homeAdapters;
    private DataBaseHelper dataBaseHelper;
    private ArrayList<ItemProduct> productArrayList = new ArrayList<>();
    private TextView textNoData;


    private void bindView() {
        recyclerView = rootView.findViewById(R.id.recyclerView);
        textNoData = rootView.findViewById(R.id.textNoData);
    }

    public MyOrdersFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_orders, container, false);
        methods = new Methods(requireActivity());
        dataBaseHelper = new DataBaseHelper(requireActivity());
        bindView();
        new InitArray().execute();
        return rootView;
    }

    private class InitArray extends AsyncTask<Void, Void, ArrayList<ItemProduct>> {
        @Override
        protected ArrayList<ItemProduct> doInBackground(Void... voids) {
            productArrayList = dataBaseHelper.retrieveData();
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

        if (0 < productArrayList.size()) {
            textNoData.setVisibility(View.GONE);
        }

        homeAdapters = new HomeAdapters(requireActivity(), productArrayList, new HomeAdapters.OnClick() {
            @Override
            public void onItemClick(int position, ItemProduct product) {

                //TAGS.product = product;
                //startActivity(new Intent(requireActivity(), OrderDetailActivity.class));

            }

            @Override
            public void onAddToCartClick(int position, ItemProduct product) {
                dataBaseHelper.addToCart(product);
            }
        }, true);
        recyclerView.setLayoutManager(new GridLayoutManager(requireActivity(), 1, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(homeAdapters);
    }

    @Override
    public void onResume() {
        //new InitArray().execute();
        super.onResume();
    }
}