package com.mental.mentalwellness;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class EditMemory extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private TextView dateButton,back;
    ImageView imgselect;
    FirebaseAuth mAuth =FirebaseAuth.getInstance();;
    FirebaseDatabase database =FirebaseDatabase.getInstance();
    ConstraintLayout btnimg;
    Uri url;
    Bundle bundle;
    EditText title,body;
    String image,feeling,s;
    ModalforMemoryBox temp;
    Button save;
    int o,tem=0;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_memory);
        imgselect = findViewById(R.id.imgselect);
        initDatePicker();
        bundle= getIntent().getExtras();
        dateButton = findViewById(R.id.datepickup);
        btnimg = findViewById(R.id.btnimg);
        save = findViewById(R.id.save);
        back = findViewById(R.id.back);
        title = findViewById(R.id.title);
        body = findViewById(R.id.discr);
        dateButton.setText(getTodaysDate());
        dateButton.setOnClickListener(this::openDatePicker);
        temp = new ModalforMemoryBox(bundle.getString("title"),bundle.getString("body")
                ,bundle.getString("date"),bundle.getString("photo"),
                mAuth.getUid(),bundle.getString("feeling"),bundle.getString("key"));
        image = bundle.getString("photo");
        feeling = bundle.getString("emotion");
        if (image.equals("joy")){
            s ="joy";
        }
        else if(image.equals("loved")){
            Glide.with(this).load(R.drawable.lovedbg2).into(imgselect);
            s ="loved";

        } else if (image.equals("surprised")){
            Glide.with(this).load(R.drawable.surprisedbg1).into(imgselect);
            s ="surprised";
        }
        else {
            Glide.with(this).load(image).into(imgselect);
        }

        body.setText(temp.getBody());
        title.setText(temp.getTitle());
        dateButton.setText(temp.getDate());


        back.setOnClickListener(view -> {
            onBackPressed();
        });
        body.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                o=1;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btnimg.setOnClickListener(view1 -> {
            Dexter.withActivity(this)
                    .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                            Intent intent=new Intent(Intent.ACTION_PICK);
                            intent.setType("image/*");
                            startActivityForResult(Intent.createChooser(intent,"Upload DP"),2);
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(com.karumi.dexter.listener.PermissionRequest permissionRequest, PermissionToken permissionToken) {
                            permissionToken.continuePermissionRequest();
                        }
                    }).check();
        });

        save.setOnClickListener(view -> {
            SaveChanges();
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==2 && resultCode==RESULT_OK){
            assert data != null;
            url = data.getData();
            tem++;
            Glide.with(EditMemory.this).load(url).into(imgselect);
            btnimg.setVisibility(View.INVISIBLE);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void SaveChanges() {
        if (tem!=0){
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("File Uploading");
            progressDialog.show();
            Date d= Calendar.getInstance().getTime();
            final StorageReference uploader = storage.getReference("Memories").child(mAuth.getCurrentUser()
                    .getUid()).child(title.getText().toString());
            uploader.putFile(url).addOnSuccessListener(taskSnapshot -> {
                progressDialog.dismiss();
                uploader.getDownloadUrl().addOnSuccessListener(uri -> {
                    String url = uri.toString();
                    String title1 =title.getText().toString() ;
                    String body1 =body.getText().toString() ;
                    String date = dateButton.getText().toString();
                    String feeling1 = temp.getFeeling();
                    String Uid = Objects.requireNonNull(mAuth.getCurrentUser().getUid());
                    ModalforMemoryBox memory = new ModalforMemoryBox(title1,body1,date,url,Uid,feeling1,temp.getKey());
                    database.getReference().child("Memories").child(Uid).child(feeling).child(temp.getKey()).setValue(memory)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                }
                            });
                });

            }).addOnProgressListener(snapshot -> {
                long precent = (100*snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                progressDialog.setMessage("Uploaded"+(int)precent+"%");
            });
        }
        else{
            Date d= Calendar.getInstance().getTime();
            String url = temp.getImage();
            String title1 =title.getText().toString() ;
            String body1 =body.getText().toString() ;
            String date = dateButton.getText().toString();
            String feeling2 = temp.getFeeling() ;
            String Uid = Objects.requireNonNull(mAuth.getCurrentUser().getUid());
            ModalforMemoryBox memory = new ModalforMemoryBox(title1,body1,date,url,Uid,feeling2,temp.getKey());
            database.getReference().child("Memories").child(Uid).child("loved").child(temp.getKey()).setValue(memory)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                        }
                    });
        }
        Intent intent = new Intent(EditMemory.this,Journal.class);
        startActivity(intent);
    }
    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }
    private String makeDateString(int day, int month, int year) {
        return  day + "/" + getMonthFormat(month)+ "/" + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view) {
        datePickerDialog.show();
    }
}