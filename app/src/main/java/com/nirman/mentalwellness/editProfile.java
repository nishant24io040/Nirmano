package com.nirman.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;

import com.bumptech.glide.Glide;

import com.nirman.Globals;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class editProfile extends AppCompatActivity {
    ImageView profileImage , backBtn , editProfile, calender;
    EditText userName;
    EditText name;
    TextView dob;
    ExtendedFloatingActionButton saveBtn;
    FirebaseAuth mAuth;
    FirebaseFirestore db;
    GoogleSignInClient gsc;
    GoogleSignInOptions gso;
    String signInName;
    String signInUserName;
    StorageReference storageReference;
    String emailID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));

        storageReference = FirebaseStorage.getInstance().getReference();
        backBtn = findViewById(R.id.edit_profile_back_btn);
        profileImage = findViewById(R.id.profile_photo_edit_photo);
        editProfile = findViewById(R.id.profile_photo_edit);
        userName = findViewById(R.id.user_name);
        name = findViewById(R.id.Name);
        dob = findViewById(R.id.dob);
        saveBtn = findViewById(R.id.save_button);
        calender = findViewById(R.id.calender_popUp);
        userName.clearFocus();
        name.clearFocus();
        dob.clearFocus();
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpWindow();
            }
        });
       dob.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               popUpWindow();
           }
       });
        this.mAuth = FirebaseAuth.getInstance();
        String phoneNumber = FirebaseAuth.getInstance().getCurrentUser().getPhoneNumber();
        getUserDetails(phoneNumber);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        StorageReference profileRef = storageReference.child("loginUserDetails/" + mAuth.getCurrentUser().getUid()+"/profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(editProfile.this)
                        .load(uri)
                        .circleCrop()
                        .into(profileImage);
//                Picasso.get().load(uri).rotate(90).resize(160, 160)
//                        .into(profileImage, new Callback() {
//                            @Override
//                            public void onSuccess() {
//                                Bitmap imageBitmap = ((BitmapDrawable) profileImage.getDrawable()).getBitmap();
//                                RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
//                                imageDrawable.setCircular(true);
//                                imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
//                                profileImage.setImageDrawable(imageDrawable);
//                            }
//
//                            @Override
//                            public void onError(Exception e) {
//                                e.printStackTrace();
//                            }
//                        });
            }
        });
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);

            }
        });
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent,1000);

            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userUID = user.getUid();
                try {


                    if (Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).isEmailVerified()) {
                        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
                        gsc = GoogleSignIn.getClient(editProfile.this, gso);
                        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(editProfile.this);
                        if (account != null) {
                            emailID = account.getEmail();
                        }
                    }
                    if (name.getText().toString().isEmpty() || userName.getText().toString().isEmpty() || dob.getText().toString().isEmpty()) {
                        Toast.makeText(editProfile.this, "One or Many Fields are empty", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (userName.getText().toString().equals("Nirmano")|| userName.getText().toString().equals("mental")){
                        userName.setError("Can't Use Nirmano as UserName");
                    }
                    else{
                    Map<String, Object> edited = new HashMap<>();
                    edited.put("usernameList", userName.getText().toString());
                    edited.put("name", name.getText().toString());
                    edited.put("dob", dob.getText().toString());
                    edited.put("email", emailID);
                    edited.put("phone", phoneNumber);

                    db = FirebaseFirestore.getInstance();
                    db.collection("phoneLoginDetails").document(userUID).set(edited).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Intent i = new Intent(editProfile.this, HomePage.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            i.putExtra("uid","a");
                            startActivity(i);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

                }}

            catch(Exception e){
                e.printStackTrace();
            }}
        });


    }
    public void getUserDetails(String phoneNumber){
        try {

            signInUserName = "";
            dob.setText("01/01/2000");
            if (Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).isEmailVerified()) {
                gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
                gsc = GoogleSignIn.getClient(this, gso);
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
                assert account != null;
                signInName = account.getDisplayName();
                name.setText(signInName);

            }
            this.db = FirebaseFirestore.getInstance();
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String userUID = user.getUid();
            db.collection("phoneLoginDetails").document(userUID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    Map<String, Object> map = new HashMap<>();
                    map = document.getData();
                    if (map != null) {
                        signInUserName = (String) map.get("usernameList");
                        name.setText((String) map.get("name"));
                        dob.setText((String) map.get("dob"));
                        userName.setText(signInUserName);
                    }}
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("fetch", e.getMessage());
                }
            });


        }catch (Exception e){
            e.printStackTrace();
        }

    }
    @Override
    protected void onActivityResult(int requestCode,int resultCode,@androidx.annotation.Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                uploadImageToFirebase(imageUri);
            }
        }

    }
    private void uploadImageToFirebase(Uri imageUri){
        try{  final StorageReference fileRef = storageReference.child("loginUserDetails/"+mAuth.getCurrentUser().getUid()+"/profile.jpg");
            fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            Glide.with(editProfile.this)
                                    .load(uri)
                                    .circleCrop()
                                    .into(profileImage);

                        }
                    });
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
//                Toast.makeText(editProfile.this, "Failed", Toast.LENGTH_SHORT).show();
                    Log.e("upload",e.getMessage());
                    Toast.makeText(editProfile.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });}
        catch (Exception e){e.printStackTrace();}

    }
    public void popUpWindow(){

        Calendar selectedDate = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,R.style.DialogTheme,
                (view, year, month, dayOfMonth) -> {
                    selectedDate.set(year, month, dayOfMonth);
                    dob.setText(DateFormat.getDateInstance(DateFormat.LONG).format(selectedDate.getTime()));
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());


        datePickerDialog.show();





    }
    public int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath){
        int rotate = 0;
        try {
            context.getContentResolver().notifyChange(imageUri, null);
            File imageFile = new File(imagePath);

            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_270:
                    rotate = 270;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    rotate = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_90:
                    rotate = 90;
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rotate;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(editProfile.this,HomePage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("uid","a");
        startActivity(i);
    }

}