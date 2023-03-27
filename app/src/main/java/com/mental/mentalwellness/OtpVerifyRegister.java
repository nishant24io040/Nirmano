package com.mental.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;

import com.mental.Globals;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OtpVerifyRegister extends AppCompatActivity {
    EditText otp1 , otp2 ,otp3 ,otp4 , otp5 ,otp6;
    String otp2Text , otp1Text , otp3Text , otp4Text , otp5Text,otp6Text;
    FirebaseAuth mAuth;
    ExtendedFloatingActionButton verifyOtpBtn;
    String verificationId;
    String phoneNumber;
    String userName;
    FirebaseFirestore db;
    String otpInput;
    TextView timer, resendOtp;
    ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify_register);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        timer = findViewById(R.id.resend_otp_tag);
        resendOtp = findViewById(R.id.resend_otp_button);
        resendOtp.setVisibility(View.GONE);
        otp1 = findViewById(R.id.otp1);

        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                resendOtp.setVisibility(View.VISIBLE);
                timer.setText("Didn't You Recieve the OTP");


            }
        }.start();

        phoneNumber = getIntent().getStringExtra("mobile").toString();
        userName = getIntent().getStringExtra("usernameList").toString();

        verifyOtpBtn = findViewById(R.id.done_otp_button);
        backBtn = findViewById(R.id.register_otp_back_button);
        mAuth = FirebaseAuth.getInstance();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtpVerifyRegister.this,Register.class));
                finish();
            }
        });

        verifyOtpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                otpInput = otp1.getText().toString();
                checkDataEmpty(otpInput);

            }
        });
        resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resendOTP_method();
            }
        });
    }
    public void verifyCode(String code , String verificationId){
        FirebaseAuth.getInstance();
        PhoneAuthCredential credentials = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credentials);
    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            storingPhoneNumberAndUsernameLogin();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(OtpVerifyRegister.this,HomePage.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra("new",true);
                            startActivity(i);

                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
//                                otpInput.setError("Enter valid OTP");
                                Toast.makeText(OtpVerifyRegister.this, "invalid code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }
    public void checkDataEmpty(String otpInput){
        verificationId = getIntent().getStringExtra("vID").toString();
        if (otpInput==null && otpInput.length()!=6) {
//            otpInput.setError("Enter valid OTP");
            Toast.makeText(this, "Please Enter 6 digit otp sent to your device"+otpInput.length(), Toast.LENGTH_SHORT).show();
        }
        else verifyCode(otpInput, verificationId);

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText();
        return TextUtils.isEmpty(str);
    }

    public void storingPhoneNumberAndUsernameLogin(){
        phoneNumber = getIntent().getStringExtra("mobile");
        userName = getIntent().getStringExtra("usernameList");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        this.db = FirebaseFirestore.getInstance();
        Map<String,Object> phoneNumberAndUsername = new HashMap<>();
        phoneNumberAndUsername.put("usernameList", userName);
        phoneNumberAndUsername.put("phone","+91"+phoneNumber);
        phoneNumberAndUsername.put("name",null);
        phoneNumberAndUsername.put("dob",null);
        db.collection("phoneLoginDetails").document(user.getUid()).set(phoneNumberAndUsername).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(OtpVerifyRegister.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void resendOTP_method(){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + getIntent().getStringExtra("mobile"),
                60,
                TimeUnit.SECONDS,
                OtpVerifyRegister.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(OtpVerifyRegister.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onCodeSent(@NonNull String newVerificationID,
                                           @NonNull PhoneAuthProvider.ForceResendingToken token) {
                        verificationId = newVerificationID;
                        Toast.makeText(OtpVerifyRegister.this, "OTP SENT", Toast.LENGTH_SHORT).show();
                        new CountDownTimer(60000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                timer.setText(String.valueOf(millisUntilFinished / 1000));
                                resendOtp.setVisibility(View.GONE);
                            }

                            public void onFinish() {
                                resendOtp.setVisibility(View.VISIBLE);
                                timer.setText("Didn't You Recieve the OTP");


                            }
                        }.start();
                        
                }}
        );
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000));
                resendOtp.setVisibility(View.GONE);
            }

            public void onFinish() {
                resendOtp.setVisibility(View.VISIBLE);
                timer.setText("Didn't You Recieve the OTP");


            }
        }.start();



    }
//    public class GenericTextWatcher implements TextWatcher {
//        private final EditText[] editText;
//        private View view;
//        public GenericTextWatcher(View view, EditText editText[])
//        {
//            this.editText = editText;
//            this.view = view;
//        }
//
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//            String text = editable.toString();
//            switch (view.getId()) {
//
//                case R.id.otp1:
//                    if (text.length() == 1)
//                        editText[1].requestFocus();
//                    break;
//                case R.id.otp2:
//
//                    if (text.length() == 1)
//                        editText[2].requestFocus();
//                    else if (text.length() == 0)
//                        editText[0].requestFocus();
//                    break;
//                case R.id.otp3:
//                    if (text.length() == 1)
//                        editText[3].requestFocus();
//                    else if (text.length() == 0)
//                        editText[1].requestFocus();
//                    break;
//                case R.id.otp4:
//                    if (text.length() == 1)
//                        editText[4].requestFocus();
//                    else if (text.length() == 0)
//                        editText[2].requestFocus();
//                    break;
//                case R.id.otp5:
//                    if (text.length() == 1)
//                        editText[5].requestFocus();
//                    else if (text.length() == 0)
//                        editText[3].requestFocus();
//                    break;
//                case R.id.otp6:
//
//                    if (text.length() == 0)
//                        editText[4].requestFocus();
//                    break;
//            }
//        }}
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(OtpVerifyRegister.this,WelcomePage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}