package com.dev.duan2android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.duan2android.adapter.ProductuserAdapter;
import com.dev.duan2android.user.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

public class ProductManagementActivity extends AppCompatActivity {
    private RecyclerView recyclerviewproduct;
    private String id = "";
    private DatabaseReference mDatabase;
    private ArrayList<User.Product> products = new ArrayList<>();
    private ProductuserAdapter productuserAdapter;
    private LinearLayoutManager linearLayoutManager;

    private TextView txtthongbao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management);
        getSupportActionBar().setTitle(R.string.manage_product);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerviewproduct = findViewById(R.id.recyclerviewproduct);
        txtthongbao = findViewById(R.id.txtthongbao);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        productuserAdapter = new ProductuserAdapter(this, products);
        recyclerviewproduct.setLayoutManager(linearLayoutManager);
        recyclerviewproduct.setHasFixedSize(true);
        recyclerviewproduct.setAdapter(productuserAdapter);
        Intent intent = getIntent();
        id = intent.getStringExtra("id1");
        getproductuser();
        Log.e("idU", "buuu3"+id);
    }

    private void getproductuser() {
        products.clear();
        mDatabase.child("id").child("User").child(id).child("user").child("idsp").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Object o = dataSnapshot.getValue();
                Log.e("O", o.toString());
                Log.e("idU", "buuu"+id);

                mDatabase.child("id").child(o.toString()).child("product").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        User.Product product = dataSnapshot.getValue(User.Product.class);
                        products.add(0, product);
                        txtthongbao.setVisibility(View.GONE);
                        productuserAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    public void deletesp(final User.Product product, final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn xóa sản phẩm\t" + product.getNameproduct() + "\tkhông?");
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                mDatabase.child("id").child(product.getIdsp()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });

                mDatabase.child("id").child("User").child(id).child("user").child("idsp").child(product.getIdsp()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                    }
                });
                mDatabase.child("id").child("User").child("sp").child(product.getIdsp()).removeValue();


                getproductuser();
                productuserAdapter.notifyDataSetChanged();
                if (i == 0) {
                    txtthongbao.setVisibility(View.VISIBLE);
                }

            }
        });


        builder.show();

    }


    public void clickprodut() {
        Toast.makeText(this, "Đang phát triển", Toast.LENGTH_SHORT).show();

    }
}
