package com.dev.duan2android.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dev.duan2android.AddProductActivity;
import com.dev.duan2android.R;


import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private AddProductActivity context;
    private LayoutInflater mLayoutInflater;
    private List<String> result;
    private final static String TAG = "Adapter";

    public Adapter(AddProductActivity context, List<String> result) {
        mLayoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.result = result;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_image_a, null));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context)
                .load(result.get(position))
                .centerCrop()
                .into(holder.image);

                if (position==result.size()-1){

                }

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image =itemView.findViewById(R.id.my_image_view);
        }

    }


}
