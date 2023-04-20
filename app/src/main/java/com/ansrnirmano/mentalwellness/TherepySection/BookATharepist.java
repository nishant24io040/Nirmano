package com.ansrnirmano.mentalwellness.TherepySection;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.ansrnirmano.mentalwellness.R;

import java.util.HashMap;


public class BookATharepist extends AppCompatActivity {

    private EditText name, phone, age, gender, marital, domilcile, education, occupation, concern;
    TextView tv;
    int b=0;
    String token;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = findViewById(R.id.header_title);
        name = findViewById(R.id.name);
        phone = findViewById(R.id.mobile);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        marital = findViewById(R.id.marital_status);
        domilcile = findViewById(R.id.domilcile);
        education = findViewById(R.id.Education);
        occupation = findViewById(R.id.Occupation);
        concern = findViewById(R.id.Concerns);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("token", task.getResult().toString(), task.getException());
                        }
                        // Get new FCM registration token
                        token = task.getResult();
                    }
                });
        findViewById(R.id.submitButton).setOnClickListener(v -> {
            if (b==0){
                tv.setText("Confirm you session");
                b++;
            }
            else {
                String namee = name.getText().toString();
                String phonee = phone.getText().toString();
                String agee = age.getText().toString();
                String genderr = gender.getText().toString();
                String maritall = marital.getText().toString();
                String domilcilee = domilcile.getText().toString();
                String educationn = education.getText().toString();
                String occupationn = occupation.getText().toString();
                String concernn = concern.getText().toString();
                store(namee, phonee, agee, genderr, maritall, domilcilee, educationn, occupationn, concernn);
                Intent intent = new Intent(this,landing_page.class);
                startActivity(intent);
            }

        });

    }

    private void store(String namee, String phonee, String agee, String genderr, String maritall, String domilcilee, String educationn, String occupationn, String concernn) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        HashMap<Object, String> hashMap = new HashMap<>();
        hashMap.put("Name", namee);
        hashMap.put("Mobile Number", phonee);
        hashMap.put("Gender", genderr);
        hashMap.put("Marital Status", maritall);
        hashMap.put("Domilcile", domilcilee);
        hashMap.put("Education", educationn);
        hashMap.put("Occupation", occupationn);
        hashMap.put("Concern", concernn);
        hashMap.put("token", token);
        database.getReference().child("sessions").child(mAuth.getCurrentUser().getUid())
                .child(namee+mAuth.getCurrentUser().getUid()).setValue(hashMap);
        Toast.makeText(this, "Booked", Toast.LENGTH_SHORT).show();
        finish();
    }
}
