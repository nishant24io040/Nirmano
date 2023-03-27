package com.mental.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.WindowCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mental.Globals;

import java.util.HashMap;
import java.util.Map;

public class lemonAndCinammonJuice extends AppCompatActivity {
    ImageButton back;
    TextView title, bullet1,bullet2,bullet3,bullet4,bullet5,output;
    LinearLayout bullet1_Lp,bullet2_Lp,bullet3_Lp, bullet4_Lp , bullet5_Lp;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lemon_and_cinammon_juice);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));
        title = findViewById(R.id.title);
        bullet1 = findViewById(R.id.bullet1);
        bullet2 = findViewById(R.id.bullet2);
        bullet3 = findViewById(R.id.bullet3);
        bullet4 = findViewById(R.id.bullet4);
        bullet5 = findViewById(R.id.bullet5);
        bullet1_Lp = findViewById(R.id.bullet1_LL);
        bullet2_Lp = findViewById(R.id.bullet2_LL);
        bullet3_Lp = findViewById(R.id.bullet3_LL);
        bullet4_Lp = findViewById(R.id.bullet4_LL);
        bullet5_Lp = findViewById(R.id.bullet5_LL);
        output = findViewById(R.id.outcomes);
        back = findViewById(R.id.lemon_back_button);
        bullet4_Lp.setVisibility(View.VISIBLE);
        bullet5_Lp.setVisibility(View.VISIBLE);
        String name = getIntent().getStringExtra("name");
        callFirestore(name);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(lemonAndCinammonJuice.this,negativeDirectPage.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("name",name);
                startActivity(i);
            }
        });
    }
    public void callFirestore(String emotion){
        this.db = FirebaseFirestore.getInstance();
        db.collection("negative_lemon").document(emotion).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        Map<String, Object> sample = new HashMap<>();
                        sample = documentSnapshot.getData();
                        String title1 = (String) sample.get("title");
                        String bullet11 = (String) sample.get("bullet1");
                        String bullet21 = (String) sample.get("bullet2");
                        String bullet31 = (String) sample.get("bullet3");
                        String bullet41 = (String) sample.get("bullet4");
                        String bullet51 = (String) sample.get("bullet5");
                        String output1 = (String) sample.get("output");
                        title.setText(title1);

                        if (!bullet11.equals("")){
                            bullet1.setText(bullet11);
                        }
                        else bullet1_Lp.setVisibility(View.GONE);

                        if (!bullet21.equals("")){
                            bullet2.setText(bullet21);
                        }
                        else bullet2_Lp.setVisibility(View.GONE);

                        if (!bullet31.equals("")){
                            bullet3.setText(bullet31);
                        }
                        else bullet3_Lp.setVisibility(View.GONE);

                        if (!bullet41.equals("")){
                            bullet4.setText(bullet41);
                        }
                        else bullet4_Lp.setVisibility(View.GONE);

                        if (!bullet51.equals("")){
                            bullet5.setText(bullet51);
                        }
                        else bullet5_Lp.setVisibility(View.GONE);

                        if (!output1.equals("")){
                            output.setText(output1);
                        }
                        else output.setVisibility(View.GONE);
                    }
                }
                else Toast.makeText(lemonAndCinammonJuice.this, "Problem Fetching it", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(lemonAndCinammonJuice.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void emotionManagement(String emotion){
        switch (emotion) {
            case "Anger":
            case "Irritation":
            case "Annoyance":
                title.setText("Rainbow Fruit Salad");
                bullet1.setText("Pick your fav fruits");
                bullet2.setText("Wash them");
                bullet3.setText("Chop them and put them in a bowl.");
                bullet4_Lp.setVisibility(View.VISIBLE);
                bullet4.setText("With a pinch of salt/favorite seasoning eat them");
                output.setText("Eating fruits may promote emotional well-being as well as predict improvements in mood. It helps to bring you to a more positive state!");
                break;
            case "Stressed":
                title.setText("Dry fruits");
                bullet1.setText("Eat a handful of Almond/ Pistachios");
                bullet2_Lp.setVisibility(View.GONE);
                bullet3_Lp.setVisibility(View.GONE);
                output.setText("It may help lower your cholesterol, ease inflammation in your heart's arteries, make diabetes less likely, and protect you against the effects of stress. Don't overdo it, though: Nuts are rich in calories.");
                break;
            case "Sad":
                title.setText("Wheet Bread/Yogurt");
                bullet1.setText("Eat 1 or 2 slice of bread");
                bullet2.setText("Or");
                bullet3.setText("Small cup of yogurt");
                output.setText("The calcium and vitamin D found in these food contain peptides which promote feelings of well-being and relaxation");
                break;
            case "Rage":
            case "Grief":
            case "Gloomy":
            case "Trauma":
            case "Shock":
            case "Fear":
            case "Panic":
            case "Paranoid":
            case "Regret":
            case "Terror":
            case "Guilt":
                title.setText("Wheat Bread");
                bullet1.setText("Eat 1 or 2 slices of bread");
                bullet2_Lp.setVisibility(View.GONE);
                bullet3_Lp.setVisibility(View.GONE);
                output.setText("The calcium and vitamin D found in Bread contain peptides that promote feelings of well-being and relaxation");
                break;
            case "Upset":
                title.setText("Garden Salad");
                bullet1.setText("Pick your favorite vegetables.");
                bullet2.setText("Wash them");
                bullet3.setText("Cut them into slices");
                bullet4_Lp.setVisibility(View.VISIBLE);
                bullet4.setText("Eat them with a pinch of salt or your Favourite dressing");
                output.setText("vegetables provide us with fibre to support a healthy gut environment. Fibre is a favourite food of the beneficial bacteria in our gut that play a range of roles in supporting our overall health. Vegetables also give us a wide range of vitamins, minerals, and antioxidants to support brain health.");
                break;
            case "Possessive":
            case "Uncertain":
                title.setText("Green Tea");
                bullet1.setText("Boil the drinking water and allow to cool slightly for 2-3 mins,  this slightly cooler water will get the best out of your delicate tea leaves.");
                bullet2.setText("Pour the water onto the teabag to release its delicious aroma.");
                bullet3.setText("Leave the teabag to infuse for up to 2 minutes");
                output.setText("It may help lower your cholesterol, ease inflammation in your heart's arteries, make diabetes less likely, and protect you against the effects of stress. Don't overdo it, though: Nuts are rich in calories.");
                break;

            case "Anxiety":
            case "Jealousy":
                title.setText("Dark Chocolate");
                bullet1.setText("Based on your health type choose ( 55%, 75% or 99%) dark chocolate.");
                bullet2.setText("Eat 1 or 2 cube Max.");
                bullet3.setText("dark chocolates should be eaten on an empty stomach or 30 minutes after a solid-food meal. They can also be eaten as a snack between lunch and dinner.");
                output.setText(" It may improve negative emotional state.\n" +
                        "These effects may allow you to adjust better to stressful situations that can lead to anxiety and other mood disorders.");

                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        String name = getIntent().getStringExtra("name");
        Intent i = new Intent(lemonAndCinammonJuice.this,negativeDirectPage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("name",name);
        startActivity(i);
    }
    }
