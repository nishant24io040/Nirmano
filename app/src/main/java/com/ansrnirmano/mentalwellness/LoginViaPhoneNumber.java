package com.ansrnirmano.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
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

public class LoginViaPhoneNumber extends AppCompatActivity {
    ExtendedFloatingActionButton sendOtpBtn;
    TextView registerIntentBtn;
    EditText phoneNumberInput;
    String phoneNumberRetrieved;
    FirebaseAuth mAuth1;
    FirebaseFirestore db;
    List<String> phoneList;
    String phoneNumber;
    String verificationId;
    ProgressBar progressBar;
    public Boolean run = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_via_phone_number);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));


        phoneNumberRetrieved = getIntent().getStringExtra("phoneNumber");
        verificationId = getIntent().getStringExtra("verificationId");
        sendOtpBtn = findViewById(R.id.send_otp_button);
        registerIntentBtn = findViewById(R.id.phone_register_instead);
        progressBar = findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);
        phoneNumberInput = findViewById(R.id.phone_number_input);
        mAuth1 = FirebaseAuth.getInstance();

        InputFilter[] filters = new InputFilter[1];
        filters[0] = new InputFilter.LengthFilter(10); //Filter to 10 characters
        phoneNumberInput.setFilters(filters);
        if (phoneNumberRetrieved != null){
            phoneNumberInput.setText(phoneNumberRetrieved);
        }
        registerIntentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginViaPhoneNumber.this,Register.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }

        });
        sendOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phoneNumber = phoneNumberInput.getText().toString();
                checkIfPhoneLoginExist(phoneNumber);
            }
        });

    }

    public void checkIfPhoneLoginExist(String phoneNumber){
        this.db = FirebaseFirestore.getInstance();
        progressBar.setVisibility(View.VISIBLE);
        db.collection("phoneLoginDetails").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for (DocumentSnapshot document : task.getResult()) {
                    Map<String,Object> map = new HashMap<>();
                    map = document.getData();
                    assert map != null;
                    String phone = (String) map.get("phone");
                    if (phone == null){
                        continue;
                    }
                    else{
                        if (phone.equals("+91"+phoneNumber)) {
                            run = true;
                            sendVerificationCode(phoneNumber);
                        }


                    }


                }
                if (task.isComplete()){
                    if (!run){
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, "Phone Number Not Exists", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }).addOnFailureListener(e -> {
            Toast.makeText(LoginViaPhoneNumber.this, "Some error" + e.getMessage(), Toast.LENGTH_SHORT).show();

        });
    }
    private void sendVerificationCode(String number) {
        // Force reCAPTCHA flow
//        FirebaseAuth.getInstance().getFirebaseAuthSettings().forceRecaptchaFlowForTesting(true);
        mAuth1 = FirebaseAuth.getInstance();
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth1)
                        .setPhoneNumber("+91" + number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                                String code = credential.getSmsCode();
                                Intent i = new Intent(LoginViaPhoneNumber.this, OtpVerify.class);
                                OtpVerify ad = new OtpVerify();
                                ad.verifyCode1(code, verificationId);
                                i.putExtra("mobile", phoneNumber);
                                i.putExtra("vID", verificationId);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                                Toast.makeText(LoginViaPhoneNumber.this, "verification SUCCESS", Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(LoginViaPhoneNumber.this, "Verification Failed"+e.getMessage(), Toast.LENGTH_SHORT).show();
                                Log.e("Error",e.getMessage());
                            }

                            @Override
                            public void onCodeSent(@NonNull String s,
                                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                                super.onCodeSent(s, token);
                                verificationId = s;
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginViaPhoneNumber.this, "OTP SENT", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginViaPhoneNumber.this, OtpVerify.class);
                                i.putExtra("mobile", phoneNumber);
                                i.putExtra("vID", verificationId);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(i);
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(LoginViaPhoneNumber.this,WelcomePage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}