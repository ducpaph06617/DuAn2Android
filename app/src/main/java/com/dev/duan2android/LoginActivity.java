package com.dev.duan2android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dev.duan2android.base.BaseActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import pl.droidsonroids.gif.GifImageView;

public class LoginActivity extends BaseActivity {
    public EditText code;
    public GifImageView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mapped();
        method();
        onclick();
    }

    private void mapped() {

        mAuth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);

        edtphone = findViewById(R.id.edtphone);
        btnlogin = findViewById(R.id.btnlogin);
        mAuth = FirebaseAuth.getInstance();

    }

    private void onclick() {
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String phone = edtphone.getText().toString().trim();
                if (phone.equals("")) {
                    edtphone.setError("Nhập số điện thoại!!!");
                    edtphone.requestFocus();
                    return;
                }
                if (!phone.startsWith("+84") && !phone.startsWith("0")) {
                    edtphone.setError("Số chưa đúng!!!");
                    edtphone.requestFocus();
                    return;
                }
                if (phone.length() != 10 && phone.length() != 12) {
                    edtphone.setError("Số chưa đúng!!!");
                    edtphone.requestFocus();
                    return;
                }

                sendCode(phone);
                Dialog dialog = dialog(R.layout.codephone, WindowManager.LayoutParams.WRAP_CONTENT);
                Button xacnhan;
                TextView textView = dialog.findViewById(R.id.txtsend);

                code = dialog.findViewById(R.id.code);
                xacnhan = dialog.findViewById(R.id.xacnhan);
                loading = dialog.findViewById(R.id.loading);
                xacnhan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String codeid = code.getText().toString();
                        if (codeid.equals("")) {
                            code.setError("Error134");
                            code.requestFocus();
                            return;
                        }
                        if (codeid.length() != 6) {
                            code.setError("Error1345");
                            code.requestFocus();
                            return;
                        }
                        loading.setVisibility(View.VISIBLE);
                        verityCode(codeid);


                    }
                });
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendCode(phone);
                    }
                });
                dialog.show();
            }

        });
    }

    private void method() {
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
//                    Log.e("TAG", user.getProviders().get(0));

                        Intent intent = new Intent(LoginActivity.this, AddProductActivity.class);
//                        intent.putExtra("provider", user.getProviders().get(0));
                        intent.putExtra("id", user.getUid());
                        intent.putExtra("phone", user.getPhoneNumber());
                        startActivity(intent);
                        Log.e("ANBC", "B");
                        finish();
                    }
            }
        };


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(authStateListener);


    }
    public Dialog dialog(int layoutid, int height) {
        android.app.Dialog dialog = new android.app.Dialog(this, R.style.CustomDialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layoutid);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.windowAnimations = R.anim.anim1;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        wlp.height = height;
        window.setAttributes(wlp);
        return dialog;

    }

    private void sendCode(String phoneNumber) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+84" + phoneNumber,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private void verityCode(String code) {
        if (idcode != null) {
            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(idcode, code);
            signInWithPhoneAuthCredential(phoneAuthCredential);
        } else {
            loading.setVisibility(View.INVISIBLE);
            Toast.makeText(this, "Mã xác nhận chưa hợp lệ", Toast.LENGTH_SHORT).show();
        }

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        mAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Log.d("TAG", "signInWithCredential:success");
                            Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = task.getResult().getUser();
                            finish();

                            // ...
                        } else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.INVISIBLE);
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            }
                        }
                    }
                });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            idcode = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String codeid = phoneAuthCredential.getSmsCode();
            if (codeid != null) {
                code.setText(codeid);
                loading.setVisibility(View.VISIBLE);
                verityCode(codeid);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

        }

    };
}
