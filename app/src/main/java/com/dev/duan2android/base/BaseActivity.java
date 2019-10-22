package com.dev.duan2android.base;

import android.content.SharedPreferences;

import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;

import com.google.firebase.auth.FirebaseAuth;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;

public class BaseActivity extends AppCompatActivity {

    //Biến màn hình HomeActivity
    public Animation animationIMG;

    public Animation animationGR;

    public EditText txtPass;

    public ImageView imgPreview;

    public boolean xxx=false;

    public ImageView imgDeletetext;

    public BottomBar bottomBar;

    public BottomBarTab nearby;

    public BottomBarTab nearby1;

    public BottomBarTab nearby2;

    public BottomBarTab nearby3;

    public TextView edtSearch;

    public NestedScrollView nestedScrollView;


    //Biến của màn hình login


    public FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener authStateListener;
    public SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    public EditText edtphone;
    public Button btnlogin;
    public String idcode;


    //Biến của màn hình AddProduct









}
