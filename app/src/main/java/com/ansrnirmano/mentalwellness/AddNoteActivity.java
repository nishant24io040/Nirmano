package com.ansrnirmano.mentalwellness;

import android.app.DatePickerDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.ansrnirmano.Globals;
import io.realm.Realm;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.UUID;

public class AddNoteActivity extends AppCompatActivity {
    TextView date;
    Button text, colorChange, emoji, deleteText;
    CalendarView cv1;
    Note note;
    String id1;
    ImageButton back ,deleteTextBtn;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memory);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));
        back = findViewById(R.id.memory_back_button);
        date = findViewById(R.id.date);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(AddNoteActivity.this,Journal.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        String title1 = getIntent().getStringExtra("title");
        String desc = getIntent().getStringExtra("desc");
        long date1 = getIntent().getLongExtra("date", 01 / 01 / 2000);
        String date2 = getIntent().getStringExtra("date1");

        String id = getIntent().getStringExtra("id");

        UUID uuid = UUID.randomUUID();
        id1 = String.valueOf(uuid);
        EditText titleInput = findViewById(R.id.memory_title);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        Button saveBtn = findViewById(R.id.save_button);
        deleteTextBtn = findViewById(R.id.memory_delete_button);
        deleteTextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titleInput.setText("");
                descriptionInput.setText("");
            }
        });
        LinearLayout dateSelect = findViewById(R.id.toggle_calendar);
        String formatedBasic = DateFormat.getDateInstance(DateFormat.LONG).format(System.currentTimeMillis());
        date.setText(formatedBasic);
        if (title1 != null || desc != null || date1 != 0) {
            titleInput.setText(title1);
            descriptionInput.setText(desc);
        }


        Realm.init(getApplicationContext());
        Realm realm = Realm.getInstance(Journal.RealmUtility.getDefaultConfig());

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!titleInput.getText().toString().equals("") || !descriptionInput.getText().toString().equals("")){
                String title = titleInput.getText().toString();
                if(title.trim().length()<=0){
                    title = "No Title";
                }
                String description = descriptionInput.getText().toString();
                long createdTime = System.currentTimeMillis();
                String createdTime2 = date.getText().toString();
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String userID = user.getUid()+UUID.randomUUID();
//                db = FirebaseFirestore.getInstance();
//                Map<String, Object> journal = new HashMap<>();
//                journal.put("title",title);
//                journal.put("description",description);
//                journal.put("createdTime",createdTime);
//                journal.put("createdTime2",createdTime2);
//                journal.put("userID",userID);
////                journal.put("id",note.getId());
//
//                db.collection("journalDataCollection").document(userID).set(journal).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void unused) {
//
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        e.printStackTrace();
//                        Toast.makeText(AddNoteActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//                });


                realm.beginTransaction();
                if (title1 != null || desc != null || date2 != null) {
                    note = realm.where(Note.class).equalTo("id", id).findFirst();
                    note.setUser(userID);
                    note.setTitle(title);
                    note.setDescription(description);
                    note.setCreatedTime2(createdTime2);
                    realm.insertOrUpdate(note);
                    realm.commitTransaction();
                    Toast.makeText(AddNoteActivity.this, "Note Is Updated", Toast.LENGTH_SHORT).show();

                } else {
                    note = realm.createObject(Note.class);
                    note.setUser(userID);
                    note.setTitle(title);
                    note.setDescription(description);
                    note.setCreatedTime(createdTime);
                    note.setCreatedTime2(createdTime2);
                    note.setId(id1);
                    realm.commitTransaction();
                    Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
                }
                Intent i = new Intent(AddNoteActivity.this,Journal.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);



            }
                else Toast.makeText(AddNoteActivity.this, "Please Write Something To Save", Toast.LENGTH_SHORT).show();
        }}
        );

        dateSelect.setOnClickListener(view -> showDatePicker());
    }
    public void showDatePicker(){
        Calendar selectedDate = Calendar.getInstance();

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,R.style.DialogTheme,
                (view, year, month, dayOfMonth) -> {
                    selectedDate.set(year, month, dayOfMonth);
                    date.setText(DateFormat.getDateInstance(DateFormat.LONG).format(selectedDate.getTime()));
                },
                selectedDate.get(Calendar.YEAR),
                selectedDate.get(Calendar.MONTH),
                selectedDate.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());


        datePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        Intent i = new Intent(AddNoteActivity.this,Journal.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);

    }
}