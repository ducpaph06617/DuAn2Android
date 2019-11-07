package com.dev.duan2android.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


import com.dev.duan2android.AddProductActivity;
import com.dev.duan2android.HomeActivity;
import com.dev.duan2android.LoginActivity;
import com.dev.duan2android.ProductManagementActivity;
import com.dev.duan2android.R;

import com.dev.duan2android.user.User;
import com.facebook.login.LoginManager;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;

import pl.droidsonroids.gif.GifImageView;

import static android.app.Activity.RESULT_OK;

public class Fragment_Menu extends BaseFragment {
    String name = "";
    String uri = "";
    String provice ="";
    String phone = "";
    String id = "";
    String email="";
    String address="";
    String gender="";
    private Dialog dialog;
    private DatabaseReference mDatabase;


    private LinearLayout linearLayout;
    private GifImageView loading;

    ImageView imginfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu,container, false);
        return view;
    }


    //ánh xạ

    private void mapped() {
        imgUser = view.findViewById(R.id.imgUser);
        txtUsername = view.findViewById(R.id.txtUsername);
        btnLogin = view.findViewById(R.id.btnLogin);
        cvUserinfor = view.findViewById(R.id.cvUserinfor);
        cvAddProduct = view.findViewById(R.id.cvAddProduct);
        cvManageProduct = view.findViewById(R.id.cvManage);
        cvHelp =  view.findViewById(R.id.cvHelp);
        cvQuit = view.findViewById(R.id.cvQuit);
        cvManage=view.findViewById(R.id.cvManage);
    }

    public void dialog() {
    }



    public void Exit(){
        cvQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                builder.setTitle(R.string.logout);
                builder.setMessage(R.string.exit);
                builder.setCancelable(false);
                builder.setPositiveButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton(R.string.except, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    public void addProduct(){
        cvAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AddProductActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }

    public void uploadAnh(ImageView imageView) {
        Calendar calendar = Calendar.getInstance();
        final StorageReference mountainsRef = storageRef.child("image" + calendar.getTimeInMillis() + "");
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();

        final UploadTask uploadTask = mountainsRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Toast.makeText(getActivity(), "Hình chưa được lưu", Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.INVISIBLE);
                linearLayout.setAlpha(1);
                imginfo.setImageResource(R.drawable.ic_user);
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!urlTask.isSuccessful()) ;
                Uri downloadUrl = urlTask.getResult();
                uri = String.valueOf(downloadUrl);
                loading.setVisibility(View.INVISIBLE);
                linearLayout.setAlpha(1);
                Toast.makeText(getActivity(), "Hình đã được lưu", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri = data.getData();
            imginfo.setImageURI(uri);
            uploadAnh(imginfo);
            loading.setVisibility(View.VISIBLE);
            linearLayout.setAlpha(0.3f);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public class RunAble1 implements Runnable {
        int seconds;
        Context context;

        public RunAble1(int seconds, Context context) {
            this.seconds = seconds;
            this.context = context;
        }

        @Override
        public void run() {
            for (int i = 0; i <=2; i++) {
                Handler handler = new Handler(Looper.getMainLooper());
                final int intI = i;
                handler.post(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void run() {
                        if (intI==2){
                            if (name==null&&name==null){
                                   dialog();
                                   cvAddProduct.setAlpha(0.5f);
                                   cvAddProduct.setClickable(false);
                                   cvUserinfor.setAlpha(0.5f);
                                   cvUserinfor.setClickable(false);


                            }else {
                                cvAddProduct.setAlpha(1);
                                cvAddProduct.setClickable(true);
                                cvUserinfor.setAlpha(1);
                                cvUserinfor.setClickable(true);

                            }

                        }

                    }

                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }


        }
    }
}
