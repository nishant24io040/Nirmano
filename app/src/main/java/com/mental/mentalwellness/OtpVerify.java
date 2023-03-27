package com.mental.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;

import com.mental.Globals;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpVerify extends AppCompatActivity {

    EditText otp1, otp2, otp3, otp4, otp5, otp6;
    String otp2Text, otp1Text, otp3Text, otp4Text, otp5Text, otp6Text;
    String otpInput;
    FirebaseAuth mAuth;
    ExtendedFloatingActionButton verifyOtpBtn;
    String verificationId;
    String phoneNumber;
    TextView timer, resendOtp;
    ImageView backBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp_verify);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        phoneNumber = getIntent().getStringExtra("mobile").toString();
        verificationId = getIntent().getStringExtra("vID").toString();
        otp1 = findViewById(R.id.otp1_r);
//        otp2 = findViewById(R.id.otp2_r);
//        otp3 = findViewById(R.id.otp3_r);
//        otp4 = findViewById(R.id.otp4_r);
//        otp5 = findViewById(R.id.otp5_r);
//        otp6 = findViewById(R.id.otp6_r);
        timer = findViewById(R.id.resend_otp_tag);
        resendOtp = findViewById(R.id.resend_otp_button);
        resendOtp.setVisibility(View.GONE);
        mAuth = FirebaseAuth.getInstance();

        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                timer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                resendOtp.setVisibility(View.VISIBLE);
                timer.setText("Didn't You Recieve the OTP");

            }
        }.start();



        otp1.requestFocus();

//        EditText [] edit = {otp1,otp2,otp3,otp4,otp5,otp6};
//
//        otp1.addTextChangedListener(new GenericTextWatcher(otp1, edit));
//        otp2.addTextChangedListener(new GenericTextWatcher(otp2, edit));
//        otp3.addTextChangedListener(new GenericTextWatcher(otp3, edit));
//        otp4.addTextChangedListener(new GenericTextWatcher(otp4, edit));
//        otp5.addTextChangedListener(new GenericTextWatcher(otp5, edit));
//        otp6.addTextChangedListener(new GenericTextWatcher(otp6, edit));





        verifyOtpBtn = findViewById(R.id.done_otp_button);
        backBtn = findViewById(R.id.login_otp_back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OtpVerify.this,LoginViaPhoneNumber.class));
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
                resendOTP_Method();
            }
        });
    }

    public void verifyCode1(String code, String verificationId) {
        FirebaseAuth.getInstance();
        PhoneAuthCredential credentials1 = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credentials1);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //existLoginCheck To Be Created After Confirmation
                            Toast.makeText(OtpVerify.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent i = new Intent(OtpVerify .this,HomePage.class);
                            i.putExtra("uid",user.getUid());
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            //Enter THe Intent Activity You want to visit After this.


                        } else {
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                                Toast.makeText(OtpVerify.this, "invalid code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    public void checkDataEmpty(String otpInput) {
        if (otpInput.length() != 6) {
//
            Toast.makeText(this, "Please Enter 6 digit otp sent to your device", Toast.LENGTH_SHORT).show();
        } else verifyCode1(otpInput, verificationId);

    }
    public void resendOTP_Method(){
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + getIntent().getStringExtra("mobile"),
                60,
                TimeUnit.SECONDS,
                OtpVerify.this,
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {
                        Toast.makeText(OtpVerify.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onCodeSent(@NonNull String newVerificationID1,
                                           @NonNull PhoneAuthProvider.ForceResendingToken token) {
                        verificationId = newVerificationID1;
                        Toast.makeText(OtpVerify.this, "OTP SENT", Toast.LENGTH_SHORT).show();
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
//                case R.id.otp1_r:
//                    if (text.length() == 1)
//                        editText[1].requestFocus();
//                    break;
//                case R.id.otp2_r:
//
//                    if (text.length() == 1)
//                        editText[2].requestFocus();
//                    else if (text.length() == 0)
//                        editText[0].requestFocus();
//                    break;
//                case R.id.otp3_r:
//                    if (text.length() == 1)
//                        editText[3].requestFocus();
//                    else if (text.length() == 0)
//                        editText[1].requestFocus();
//                    break;
//                case R.id.otp4_r:
//                    if (text.length() == 1)
//                        editText[4].requestFocus();
//                    else if (text.length() == 0)
//                        editText[2].requestFocus();
//                    break;
//                case R.id.otp5_r:
//                    if (text.length() == 1)
//                        editText[5].requestFocus();
//                    else if (text.length() == 0)
//                        editText[3].requestFocus();
//                    break;
//                case R.id.otp6_r:
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
        Intent i = new Intent(OtpVerify.this,WelcomePage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }
}

