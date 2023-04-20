package com.ansrnirmano.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;

import com.ansrnirmano.Globals;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Register extends AppCompatActivity {

    EditText phoneNumberInput;
    EditText usernamePhoneLoginInput;
    String phoneNumber;
    ExtendedFloatingActionButton sendOtpBtn;
    FirebaseAuth mAuth1;
    String verificationId;
    FirebaseFirestore db;
    String usernamePhoneLogin;
    TextView phoneLogin;
    String phoneNumberData;
    List<String> phoneList;
    List<String> userNameList;
    CheckBox checkBox;
    public static String ret , ret1;
    Boolean y;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_via_phone);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        phoneNumberInput = findViewById(R.id.phone_number_input);
        sendOtpBtn = findViewById(R.id.send_otp_button);
        usernamePhoneLoginInput = findViewById(R.id.username_input);
        phoneLogin = findViewById(R.id.login_intent);
        checkBox = findViewById(R.id.tc_checkbox);
        mAuth1 = FirebaseAuth.getInstance();
        usernamePhoneLogin = usernamePhoneLoginInput.getText().toString();
        phoneNumber = phoneNumberInput.getText().toString();
        progressBar = findViewById(R.id.progressBar3);
        progressBar.setVisibility(View.GONE);
        y = false;

        String restart = getIntent().getStringExtra("restart");
        if(restart!=null){
        if (restart.equals("Yes")){
            Toast.makeText(Register.this, "This Number Already Exists Login With This", Toast.LENGTH_SHORT).show();
            phoneNumberInput.setError("Try With Another Number");
        }}
        sendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = phoneNumberInput.getText().toString();
                usernamePhoneLogin = usernamePhoneLoginInput.getText().toString();
                if (!checkBox.isChecked())  checkBox.setError("Agree First");
                if (usernamePhoneLogin.equals("Nirmano")||usernamePhoneLogin.equals("mental")){
                    usernamePhoneLoginInput.setError("Can't Use Nirmano as UserName");
                }
                else {
                    checkUserAndExecute(phoneNumber,y);

                }
            }
        });
        phoneLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, LoginViaPhoneNumber.class);
                intent.putExtra("phoneNumber",phoneNumber);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    private void sendVerificationCode(String number) {
        // Force reCAPTCHA flow
        //FirebaseAuth.getInstance().getFirebaseAuthSettings().forceRecaptchaFlowForTesting(true);
        PhoneAuthOptions options1 =
                PhoneAuthOptions.newBuilder(mAuth1)
                        .setPhoneNumber("+91"+number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                                String code = credential.getSmsCode();
                                Intent intent = new Intent(Register.this, OtpVerifyRegister.class);
                                OtpVerifyRegister md = new OtpVerifyRegister();
                                md.verifyCode(code, verificationId);
                                intent.putExtra("mobile", phoneNumber);
                                intent.putExtra("usernameList",usernamePhoneLogin);
                                intent.putExtra("vID", verificationId);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                Toast.makeText(Register.this, "verification SUCCESS", Toast.LENGTH_SHORT).show();
//

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(Register.this, "hii", Toast.LENGTH_SHORT).show();
                                Log.w("firebase" , e.getMessage());


                            }

                            @Override
                            public void onCodeSent(@NonNull String s,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                                super.onCodeSent(s, token);
                                verificationId = s;
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(Register.this, "OTP SENT", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Register.this, OtpVerifyRegister.class);
                                intent.putExtra("mobile", phoneNumber);
                                intent.putExtra("usernameList",usernamePhoneLogin);
                                intent.putExtra("vID", verificationId);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options1);
    }

    private void checkUserAndExecute(String documentName,Boolean b){
        this.db = FirebaseFirestore.getInstance();
        progressBar.setVisibility(View.VISIBLE);


        db.collection("phoneLoginDetails").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    Map<String,Object> map = new HashMap<>();
                    map = document.getData();
                    assert map != null;
                    String phone = (String) map.get("phone");
                    if (phone != null){
                        if (phone.equals("+91"+documentName)) {
                            y = true;
                            restartActivity(Register.this);
                            break;
                    }

                    else {
                        if (isEmpty(phoneNumberInput) && phoneNumber.length() != 10) {
                            phoneNumberInput.setError("Enter valid Mobile Number");
                            Toast.makeText(this, "Please Enter Your 10 digit mobile Number Without Country Code", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            break;
                        }
                        }



                    }
                }
                if (!y){
                    sendVerificationCode(documentName);
                }


            }
        }).addOnFailureListener(e -> {
            Toast.makeText(Register.this, "Error" +e.getMessage(), Toast.LENGTH_SHORT).show();

        });

    }
    public void restartActivity(Activity act){

        Intent intent=new Intent();
        intent.setClass(act, act.getClass());
        intent.putExtra("restart","Yes");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        act.startActivity(intent);
        act.finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(Register.this,WelcomePage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);


    }



}