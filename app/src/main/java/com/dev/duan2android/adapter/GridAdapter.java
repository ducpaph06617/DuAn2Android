package com.dev.duan2android.adapter;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev.duan2android.R;
import com.dev.duan2android.fragment.Fragment_Home;
import com.dev.duan2android.user.User;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {

    private Fragment_Home context;
    private ArrayList<User.Product> products;

    public GridAdapter(Fragment_Home context, ArrayList<User.Product> products) {
        this.context = context;
        this.products = products;
    }


    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView==null){
            LayoutInflater inflater= LayoutInflater.from(parent.getContext());
            v=inflater.inflate(R.layout.item_product,parent,false);

        }else {
            v=convertView;
        }

         ImageView imgProduct;
         TextView tvNameproduct;
         TextView tvPrice;
        imgProduct =  v.findViewById(R.id.btn_product);
        tvNameproduct =  v.findViewById(R.id.tvNameproduct);
        tvPrice =  v.findViewById(R.id.tvPrice);
        final User.Product product = products.get(position);
        tvNameproduct.setText(product.getNameproduct());
        tvPrice.setText("Giá:"+product.getPriceproduct()+"\tVND");
        Picasso.get().load(product.getUri()).resize(300,300).centerCrop(Gravity.TOP).into(imgProduct);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.clickproduct(product);
            }
        });
        return v;
    }
}
