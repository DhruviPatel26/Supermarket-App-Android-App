package com.all_product.commerce.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.all_product.commerce.Models.ItemProduct;
import com.all_product.commerce.R;
import com.all_product.commerce.Utils.Methods;

import java.util.ArrayList;

public class HomeAdapters extends RecyclerView.Adapter<HomeAdapters.ViewHolder> {

    private Activity activity;
    private ArrayList<ItemProduct> products;
    private Methods methods;
    private OnClick onClick;
    private boolean isFromOrder;

    public HomeAdapters(Activity activity, ArrayList<ItemProduct> products, OnClick onClick, boolean isFromOrder) {
        this.activity = activity;
        this.products = products;
        this.onClick = onClick;
        this.isFromOrder = isFromOrder;
        this.methods = new Methods(activity);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_home_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        if (isFromOrder) {
            String mText = "Price: $" +  products.get(position).getPrice() +
                    " || Qty: " + products.get(position).getQty() +
                    " || Total Pay: $"+methods.getMultiplyPrice(products.get(position).getPrice(), products.get(position).getQty());

            holder.priceProduct.setText("Price :"+mText);
        } else {
            holder.priceProduct.setText("Price :$" + products.get(position).getPrice());
        }

        holder.titleProduct.setText("Name : "+products.get(position).getName());
        holder.descriptionProduct.setText("Decription :"+products.get(position).getDescription());
        holder.imageProduct.setImageResource(products.get(position).getImageId());

       /* holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onAddToCartClick(position, products.get(position));
            }
        });*/

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onItemClick(position, products.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageProduct, addToCart;
        TextView titleProduct, descriptionProduct, priceProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageProduct = itemView.findViewById(R.id.imageProduct);
            addToCart = itemView.findViewById(R.id.addToCart);
            titleProduct = itemView.findViewById(R.id.titleProduct);
            descriptionProduct = itemView.findViewById(R.id.descProduct);
            priceProduct = itemView.findViewById(R.id.priceProduct);

        }
    }

    public interface OnClick {

        void onItemClick(int position, ItemProduct product);

        void onAddToCartClick(int position, ItemProduct product);

    }

}
