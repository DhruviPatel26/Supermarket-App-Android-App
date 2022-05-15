package com.all_product.commerce.Utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.all_product.commerce.R;

public class ChooseImage extends Dialog {

    private Activity activity;
    private OnDialogClick onDialogClick;
    private RecyclerView recyclerView;
    private int[] resIds = new int[]{R.drawable.watch_1, R.drawable.watch_2, R.drawable.bracelet_1, R.drawable.chair_1, R.drawable.chair_2};


    public ChooseImage(Activity activity, OnDialogClick onDialogClick) {
        super(activity);
        this.activity = activity;
        this.onDialogClick = onDialogClick;
    }

    private void bindData() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("TAG", "onCreate: " + "");
        setContentView(R.layout.choose_image);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        bindData();

        GridLayoutManager layoutManager = new GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new MyProduct(resIds));

    }

    public interface OnDialogClick {
        void onClick(int position, int resourceId);

    }


    private class MyProduct extends RecyclerView.Adapter<MyProduct.ViewHolder> {

        int[] resourceIds;

        public MyProduct(int[] resourceIds) {
            this.resourceIds = resourceIds;
            Log.e("TAG", "ProductAdapter: " + resourceIds.length);
        }

        @NonNull
        @Override
        public MyProduct.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.square_image, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyProduct.ViewHolder holder, int position) {
            Log.e("TAG", "onBindViewHolder: ");

            holder.mImage.setImageResource(resourceIds[position]);

            Log.e("TAG", "onBindViewHolder: ");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDialogClick.onClick(position, resourceIds[position]);
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return resourceIds.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView mImage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mImage = itemView.findViewById(R.id.mImage);
            }
        }
    }


    /*private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

        int[] resourceIds;

        public ProductAdapter(int[] resourceIds) {
            this.resourceIds = resourceIds;
            Log.e("TAG", "ProductAdapter: " + resourceIds.length);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.square_image, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ProductAdapter.ViewHolder holder, int position) {

            Log.e("TAG", "onBindViewHolder: ");

            holder.mImage.setImageResource(resourceIds[position]);

            Log.e("TAG", "onBindViewHolder: ");

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDialogClick.onClick(position, resourceIds[position]);
                    dismiss();
                }
            });

        }

        @Override
        public int getItemCount() {
            Log.e("TAG", "getItemCount: " + resourceIds.length );
            return resourceIds.length;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView mImage;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                mImage = itemView.findViewById(R.id.mImage);
            }
        }
    }*/


}

