package com.dev.duan2android.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.duan2android.R;
import com.dev.duan2android.fragment.Fragment_Home;
import com.dev.duan2android.holder.ProductHolder;
import com.dev.duan2android.user.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductHolder> {

    private ArrayList<User.Product> products;
    private Fragment_Home context;

    public ProductAdapter(ArrayList<User.Product> products, Fragment_Home context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_productt,parent,false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, final int position) {
       final User.Product product=products.get(position);
       holder.tvNameproduct.setText(product.getNameproduct());
       holder.tvPrice.setText("đ" +product.getPriceproduct());
       Picasso.get().load(product.getUri()).resize(1080,1920).centerCrop(Gravity.TOP).into(holder.imgProduct);
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               context.clickproduct(product);
           }
       });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
