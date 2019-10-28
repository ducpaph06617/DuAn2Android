package com.dev.duan2android.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dev.duan2android.R;
import com.dev.duan2android.adapter.GridAdapter;
import com.dev.duan2android.adapter.ImageAdapter;
import com.dev.duan2android.adapter.ProductAdapter;
import com.dev.duan2android.base.BaseFragment;
import com.dev.duan2android.user.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Fragment_Home extends BaseFragment {
    private RecyclerView recyclerviewProductBoy;
    private RecyclerView recyclerviewProductGirl;
    private RecyclerView recyclerviewProductPhone;
    private RecyclerView recyclerviewProductHouseware;
    private RecyclerView recyclerviewProductnew;
    private ProductAdapter productAdapter;
    private ProductAdapter productAdaptergirl;
    private ProductAdapter productAdapterphone;
    private ProductAdapter productAdapterhouse;
    private ProductAdapter productAdapternew;
    private ProductAdapter productAdapteroffer;
    private GridAdapter gridAdapter;

    private LinearLayoutManager linearLayoutManager,
            linearLayoutManager1, getLinearLayoutManager2,
            getLinearLayoutManager3,
            getGetLinearLayoutManager4
                    ,getGetGetLinearLayoutManager5;


    private ArrayList<User.Product> products = new ArrayList<>();
    private ArrayList<User.Product> productsgirl = new ArrayList<>();
    private ArrayList<User.Product> productsphone = new ArrayList<>();
    private ArrayList<User.Product> productshouse = new ArrayList<>();
    private ArrayList<User.Product> productnew = new ArrayList<>();
    private ArrayList<User.Product> productoffer = new ArrayList<>();
    private List<User.Product> list = new ArrayList<>();

    private ArrayList<String> offer=new ArrayList<>();




    private DatabaseReference mDatabase;
    private StorageReference storageRef;
    private FirebaseStorage storage;
    private ArrayList<String> path = new ArrayList<>();
    private ArrayList<String> uri = new ArrayList<>();
    private ArrayList<User.cartsp> giohangArray = new ArrayList<>();
    private CardView cvForMan;
    private TextView btnMoreman;

    private int i = 0;
    private boolean check = false;


    private TextView textView;
    private String id = "";


    private TextView txtProduct;
    private RecyclerView recyclerviewimg;
    private TextView txProduct;
    private TextView txtprice;
    private TextView txtnameshop;
    private TextView txtSanPham;
    private TextView txtdate;
    private TextView txtloai;
    private TextView txttinhtrang;
    private TextView txttrangthai;
    private TextView txtsoluong;
    private TextView txtmota;
    private ImageView left;
    private TextView intgio;
    private TextView themvaogio;
    private TextView muangay;
    private ImageView giohang;
    private LinearLayout layout;

    private TextView btnMoregirl;
    private TextView btnMorephone;
    private TextView btnMorehouse;
    private TextView btnMorenew;

    private TextView xemthem;
    private RecyclerView recyclerviewoffer;
    ProgressBar progressBar;
    ProgressBar progressBar1;
    ProgressBar progressBar2;
    ProgressBar progressBar3;
    ProgressBar progressBar4;
    ProgressBar progressBar5;






    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

         progressBar = view.findViewById(R.id.progress);
         progressBar1 = view.findViewById(R.id.progress1);
         progressBar2 = view.findViewById(R.id.progress2);
         progressBar3 = view.findViewById(R.id.progress3);
         progressBar4 = view.findViewById(R.id.progress4);
         progressBar5 = view.findViewById(R.id.progress5);
        mapped();
        getiduser();
        Intent intent = getActivity().getIntent();
        id = intent.getStringExtra("id");
        productAdapter = new ProductAdapter(products, Fragment_Home.this);
        productAdaptergirl = new ProductAdapter(productsgirl, Fragment_Home.this);
        productAdapterphone = new ProductAdapter(productsphone, Fragment_Home.this);
        productAdapterhouse = new ProductAdapter(productshouse, Fragment_Home.this);
        productAdapternew = new ProductAdapter(productnew, Fragment_Home.this);
        productAdapteroffer=new ProductAdapter(productoffer,Fragment_Home.this);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        linearLayoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        getLinearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        getLinearLayoutManager3 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        getGetLinearLayoutManager4 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        getGetGetLinearLayoutManager5 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        recyclerviewProductBoy.setLayoutManager(linearLayoutManager);
        recyclerviewProductGirl.setLayoutManager(linearLayoutManager1);
        recyclerviewProductPhone.setLayoutManager(getLinearLayoutManager2);
        recyclerviewProductHouseware.setLayoutManager(getLinearLayoutManager3);
        recyclerviewProductnew.setLayoutManager(getGetLinearLayoutManager4);
        recyclerviewoffer.setLayoutManager(getGetGetLinearLayoutManager5);
        recyclerviewProductBoy.setAdapter(productAdapter);
        recyclerviewProductGirl.setAdapter(productAdaptergirl);
        recyclerviewProductPhone.setAdapter(productAdapterphone);
        recyclerviewProductHouseware.setAdapter(productAdapterhouse);
        recyclerviewProductnew.setAdapter(productAdapternew);
        recyclerviewoffer.setAdapter(productAdapteroffer);


        btnMoreman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviews();
            }
        });
        btnMoregirl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewsgirl();
            }
        });
        btnMorephone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewsphone();
            }
        });
        btnMorehouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewshouse();
            }
        });
        btnMorenew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewsview();
            }
        });
        xemthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewDexuat();
            }
        });
        getoffer();

        return view;
    }

    private void mapped() {
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://onlinestore-41bf0.appspot.com");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        cvForMan = view.findViewById(R.id.cvForMan);
        btnMoreman = view.findViewById(R.id.btnMoreman);
        btnMoreman = view.findViewById(R.id.btnMoreman);
        recyclerviewProductGirl = view.findViewById(R.id.recyclerviewProductGirl);
        recyclerviewProductBoy = view.findViewById(R.id.recyclerviewProductBoy);
        recyclerviewProductPhone = view.findViewById(R.id.recyclerviewProductPhone);
        recyclerviewProductHouseware = view.findViewById(R.id.recyclerviewProductHouseware);
        recyclerviewProductnew = view.findViewById(R.id.recycylerviewnew);
        btnMoreman = view.findViewById(R.id.btnMoreman);
        btnMoregirl = view.findViewById(R.id.btnMoregirl);
        btnMorephone = view.findViewById(R.id.btnMorephone);
        btnMorehouse = view.findViewById(R.id.btnMorehouse);
        btnMorenew = view.findViewById(R.id.btnMorenew);
        xemthem =  view.findViewById(R.id.xemthem);
        recyclerviewoffer =  view.findViewById(R.id.recyclerviewoffer);
    }


    private void getiduser() {

        path.clear();
        final String nam = "Quần áo nam";
        final String nu = "Quần áo nữ";
        final String dienthoai = "Điện thoại & Laptop";
        final String dogiadung = "Đồ gia dụng";
        final String nguoiyeu = "Người Yêu";
        products.clear();
        productsgirl.clear();
        productnew.clear();
        mDatabase.child("id").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if (dataSnapshot.getKey() != null && dataSnapshot.getKey().startsWith("sp:")) {
                    mDatabase.child("id").child(dataSnapshot.getKey()).child("product").addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                            User.Product product = dataSnapshot.getValue(User.Product.class);
                            Log.e("TAG", product.toString());
                            products.add(0,product);
                            productsgirl.add(0, product);
                            productsphone.add(0, product);
                            productshouse.add(0, product);
                            list.add(0, product);
                            progressBar.setVisibility(View.GONE);
                            progressBar1.setVisibility(View.GONE);
                            progressBar2.setVisibility(View.GONE);
                            progressBar3.setVisibility(View.GONE);
                            progressBar4.setVisibility(View.GONE);
                            progressBar5.setVisibility(View.GONE);
                            if (productnew.size() < 50) {
                                productnew.add(0, product);
                                i++;
                            }
                            for (i = 0; i < products.size(); i++) {
                                if (products != null) {
                                    if (!nam.equalsIgnoreCase(products.get(i).getLoaisp())) {
                                        products.remove(i);
                                        for (int i=0;i<products.size();i++){
                                            Log.e("LOL",products.get(i).getNameproduct()+"");
                                        }


                                    }
                                }
                            }
                            for (i = 0; i < productsgirl.size(); i++) {
                                if (productsgirl != null) {
                                    if (!nu.equalsIgnoreCase(productsgirl.get(i).getLoaisp())) {
                                        productsgirl.remove(i);
                                    }

                                }
                            }

                            for (i = 0; i < productshouse.size(); i++) {
                                if (productshouse != null) {
                                    if (!dogiadung.equalsIgnoreCase(productshouse.get(i).getLoaisp())) {
                                        productshouse.remove(i);
                                    }

                                }
                            }

                            for (i = 0; i < productsphone.size(); i++) {
                                if (productsphone != null) {
                                    if (!dienthoai.equalsIgnoreCase(productsphone.get(i).getLoaisp())) {
                                        productsphone.remove(i);
                                    }
                                }
                            }
                            for (i = 0; i < productsphone.size(); i++) {
                                if (productsphone != null) {
                                    if (!nguoiyeu.equalsIgnoreCase(productsphone.get(i).getLoaisp())) {
                                        productsphone.remove(i);
                                    }
                                }
                            }

                            Log.e("TAG", products.toString());
                            Log.e("TAGGIRL", productsgirl.toString());
                            productAdaptergirl.notifyDataSetChanged();
                            productAdapter.notifyDataSetChanged();
                            productAdapterphone.notifyDataSetChanged();
                            productAdapterhouse.notifyDataSetChanged();
                            productAdapternew.notifyDataSetChanged();
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
                    Log.e("TAG", products.size() + "");


                }


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


    public void reviews() {

        final Dialog dialog = new Dialog(getActivity(), R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_for_boy);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;

        //dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialog;

        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        ImageView left;
        TextView textView;
        textView = dialog.findViewById(R.id.nameshop);
        textView.setText("Dành cho nam");
        left = dialog.findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        GridView gif = dialog.findViewById(R.id.gif);
        gridAdapter = new GridAdapter(Fragment_Home.this, products);
        gif = dialog.findViewById(R.id.gif);
        gif.setAdapter(gridAdapter);
        dialog.show();

    }

    public void reviewsgirl() {

        final Dialog dialog = new Dialog(getActivity(), R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_for_boy);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;

        //dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialog;

        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        ImageView left;
        TextView textView;
        textView = dialog.findViewById(R.id.nameshop);
        textView.setText("Dành cho nữ");
        left = dialog.findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        GridView gif = dialog.findViewById(R.id.gif);
        gridAdapter = new GridAdapter(Fragment_Home.this, productsgirl);
        gif = dialog.findViewById(R.id.gif);
        gif.setAdapter(gridAdapter);


        dialog.show();

    }

    public void reviewsphone() {

        final Dialog dialog = new Dialog(getActivity(), R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_for_boy);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;

        //dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialog;

        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        ImageView left;
        TextView textView;
        textView = dialog.findViewById(R.id.nameshop);
        textView.setText("Điện thoại");
        left = dialog.findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        GridView gif = dialog.findViewById(R.id.gif);
        gridAdapter = new GridAdapter(Fragment_Home.this, productsphone);
        gif = dialog.findViewById(R.id.gif);
        gif.setAdapter(gridAdapter);


        dialog.show();

    }

    public void reviewshouse() {

        final Dialog dialog = new Dialog(getActivity(), R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_for_boy);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;

        //dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialog;

        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        ImageView left;
        TextView textView;
        textView = dialog.findViewById(R.id.nameshop);
        textView.setText("Đồ gia dụng");
        left = dialog.findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        GridView gif = dialog.findViewById(R.id.gif);
        gridAdapter = new GridAdapter(Fragment_Home.this, productshouse);
        gif = dialog.findViewById(R.id.gif);
        gif.setAdapter(gridAdapter);


        dialog.show();

    }

    public void reviewsview() {

        final Dialog dialog = new Dialog(getActivity(), R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_for_boy);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;

        //dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialog;

        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        ImageView left;
        TextView textView;
        textView = dialog.findViewById(R.id.nameshop);
        textView.setText("Mới nhất");
        left = dialog.findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        GridView gif = dialog.findViewById(R.id.gif);
        gridAdapter = new GridAdapter(Fragment_Home.this, productnew);
        gif = dialog.findViewById(R.id.gif);
        gif.setAdapter(gridAdapter);


        dialog.show();

    }
    public void reviewDexuat(){
        final Dialog dialog = new Dialog(getActivity(), R.style.PauseDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_for_boy);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.BOTTOM;

        //dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialog;

        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        ImageView left;
        TextView textView;
        textView = dialog.findViewById(R.id.nameshop);
        textView.setText("Đề xuất cho bạn");
        left = dialog.findViewById(R.id.left);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        GridView gif = dialog.findViewById(R.id.gif);
        gridAdapter = new GridAdapter(Fragment_Home.this, productoffer);
        gif = dialog.findViewById(R.id.gif);
        gif.setAdapter(gridAdapter);

        dialog.show();
    }


    public void clickproduct(final User.Product product) {
        if (!offer.contains(product.getIdsp())){
            offer.add(product.getIdsp());
        }
       if (!offer.isEmpty()){
         if (id!=null){
             mDatabase.child("id").child("User").child(id).child("user").child("offer").setValue(offer)
                     .addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void aVoid) {

                         }
                     });
         }
       }
        final Dialog dialog = new Dialog(getActivity(), R.style.PauseDialog1);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.item_click);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);

        left = dialog.findViewById(R.id.left);
        intgio = dialog.findViewById(R.id.intgio);
        themvaogio = dialog.findViewById(R.id.themvaogio);
        muangay = dialog.findViewById(R.id.muangay);
        giohang = dialog.findViewById(R.id.giohang);
        layout = dialog.findViewById(R.id.layout);
        giohang.setVisibility(View.INVISIBLE);
        layout.setVisibility(View.INVISIBLE);

        getcart();
        final Calendar calendar1 = Calendar.getInstance();

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        giohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        themvaogio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id == null) {
                    Toast.makeText(getActivity(), "Vui lòng đăng nhập", Toast.LENGTH_SHORT).show();
                } else {

                    if (!giohangArray.isEmpty()) {
                        for (int i = 0; i < giohangArray.size(); i++) {
                            if (product.getIdsp().equalsIgnoreCase(giohangArray.get(i).getIdsp())) {
                                check = true;
                                Toast.makeText(getActivity(), "Sản phẩm đã tồn tại trong giỏ hàng", Toast.LENGTH_SHORT).show();
                                break;
                            } else {
                                check = false;
                            }
                        }
                    }
                    if (check == false) {
                        giohangArray.clear();
                        User.cartsp cartsp = new User.cartsp();
                        cartsp.setIdsp(product.getIdsp());
                        cartsp.setSoluong("1");
                        mDatabase.child("id").child("User").child(id).child("cart").child(calendar1.getTimeInMillis() + "").setValue(cartsp).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getActivity(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                getcart();
                            }
                        });
                    }


                }
            }
        });


        txtloai = dialog.findViewById(R.id.txtloai);
        txtsoluong = dialog.findViewById(R.id.txtsoluong);
        txtmota = dialog.findViewById(R.id.txtmota);

        LinearLayoutManager imglayout;
        txtProduct = dialog.findViewById(R.id.txtProduct);
        recyclerviewimg = dialog.findViewById(R.id.recyclerviewimg);
        txProduct = dialog.findViewById(R.id.txProduct);
        txtprice = dialog.findViewById(R.id.txtprice);
        txtnameshop = dialog.findViewById(R.id.txtnameshop);
        txtSanPham = dialog.findViewById(R.id.txtSanPham);
        txtdate = dialog.findViewById(R.id.txtdate);
        textView = dialog.findViewById(R.id.posion);
        imglayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

        uri.clear();
        ImageAdapter imageAdapter = new ImageAdapter(Fragment_Home.this, uri, R.layout.item_image);
        recyclerviewimg.setLayoutManager(imglayout);
        recyclerviewimg.setAdapter(imageAdapter);
        String idsp = product.getIdsp();
        mDatabase.child("id").child("User").child("sp").child(idsp).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Object o = dataSnapshot.getValue();
                uri.add(o.toString());
                Log.e("TAG", uri.size() + "");
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
        imageAdapter.notifyDataSetChanged();
        txProduct.setText(product.getNameproduct());
        txtProduct.setText(product.getNameproduct());
        txtnameshop.setText("Tên shop:" + product.getNameshop());
        txtprice.setText("đ" + product.getPriceproduct());
        txtloai.setText(product.getLoaisp());
        txtmota.setText(product.getDescribe());
        txtsoluong.setText(product.getSoluong());


        String thoigia = product.getThoigian();
        Calendar calendar = Calendar.getInstance();
        int date = (int) ((calendar.getTimeInMillis() - Long.valueOf(thoigia)) / (1000 * 60 * 60 * 24));
        if (date == 0) {
            txtdate.setText("Hôm nay");
        } else if (date <= 30) {
            txtdate.setText(date + "ngày");
        } else {
            ///..........//

        }


        dialog.show();
    }

    public void imgzoom() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_img_zoom);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.TOP;

        //dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialog;
        wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        wlp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(wlp);
        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerviewimg);
        ImageAdapter imageAdapter = new ImageAdapter(Fragment_Home.this, uri, R.layout.item_image_b);
        LinearLayoutManager imglayout = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(imglayout);
        recyclerView.setAdapter(imageAdapter);
        dialog.show();
    }


    private void getcart() {
        if (id != null) {
            giohangArray.clear();
            mDatabase.child("id").child("User").child(id).child("cart").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    User.cartsp cartsp = dataSnapshot.getValue(User.cartsp.class);
                    giohangArray.add(cartsp);
                    giohang.setVisibility(View.VISIBLE);
                    layout.setVisibility(View.VISIBLE);
                    intgio.setText(giohangArray.size() + "");
                    Log.e("KEY", giohangArray.size() + "");
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log.e("KEY", "a");
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    Log.e("KEY", "b");
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Log.e("KEY", "c");
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("KEY", "d");
                }
            });
        }
    }

    private void getoffer(){
        if (id!=null){
            productoffer.clear();
            mDatabase.child("id").child("User").child(id).child("user").child("offer").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    if (dataSnapshot.getValue()!=null){
                        mDatabase.child("id").child(dataSnapshot.getValue().toString()).child("product").addChildEventListener(new ChildEventListener() {
                            @Override
                            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                User.Product product=dataSnapshot.getValue(User.Product.class);
                                productoffer.add(0,product);

                                productAdapteroffer.notifyDataSetChanged();
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
    }
}
