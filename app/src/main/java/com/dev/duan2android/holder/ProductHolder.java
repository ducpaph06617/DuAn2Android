package com.dev.duan2android.holder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dev.duan2android.R;


public class ProductHolder extends RecyclerView.ViewHolder {
    public ImageView imgProduct;
    public TextView tvNameproduct;
    public TextView tvPrice;



    public ProductHolder(View itemView) {
        super(itemView);
        imgProduct =  itemView.findViewById(R.id.btn_product);
        tvNameproduct =  itemView.findViewById(R.id.tvNameproduct);
        tvPrice =  itemView.findViewById(R.id.tvPrice);
    }
}
