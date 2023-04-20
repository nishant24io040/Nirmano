package com.ansrnirmano.mentalwellness;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ansrnirmano.Globals;

import java.util.HashMap;
import java.util.Map;

public class jogg extends AppCompatActivity {
    ImageButton back;
    TextView title, bullet1,bullet2,bullet3,bullet4,bullet5,output,para;
    LinearLayout bullet1_Lp,bullet2_Lp,bullet3_Lp, bullet4_Lp , bullet5_Lp;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogg);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        findViewById(R.id.page_root_view).setPadding(0, 0, 0, Globals.getNavBarHeight(this));
        title = findViewById(R.id.title);
        bullet1 = findViewById(R.id.bullet1);
        bullet2 = findViewById(R.id.bullet2);
        bullet3 = findViewById(R.id.bullet3);
        bullet4 = findViewById(R.id.bullet4);
        bullet5 = findViewById(R.id.bullet5);
        para = findViewById(R.id.para);
        bullet1_Lp = findViewById(R.id.bullet1_LL);
        bullet2_Lp = findViewById(R.id.bullet2_LL);
        bullet3_Lp = findViewById(R.id.bullet3_LL);
        bullet4_Lp = findViewById(R.id.bullet4_LL);
        bullet5_Lp = findViewById(R.id.bullet5_LL);
        output = findViewById(R.id.outcomes);
        bullet4_Lp.setVisibility(View.VISIBLE);
        bullet5_Lp.setVisibility(View.VISIBLE);

        String name = getIntent().getStringExtra("name");
        callFirestore(name);
        back = findViewById(R.id.lemon_back_button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent i = new Intent(jogg.this,negativeDirectPage.class);
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
        db.collection("negative_breathe").document(emotion).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()) {
                        Map<String, Object> sample = new HashMap<>();
                        sample = documentSnapshot.getData();
                        String title1 = (String) sample.get("title");
                        String para1 = (String) sample.get("para");
                        String bullet11 = (String) sample.get("bullet1");
                        String bullet21 = (String) sample.get("bullet2");
                        String bullet31 = (String) sample.get("bullet3");
                        String bullet41 = (String) sample.get("bullet4");
                        String bullet51 = (String) sample.get("bullet5");
                        String output1 = (String) sample.get("output");
                        title.setText(title1);
                        if (!para.equals("")){
                            para.setText(para1);
                        }
                        else para.setVisibility(View.GONE);

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
                else Toast.makeText(jogg.this, "Problem Fetching it", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(jogg.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void emotionManagement(String emotion) {
        switch (emotion) {
            case "Stressed":
                title.setText("Take time out");
                para.setText("A time-out basically involves removing yourself from a stressful situation, so you have time to cool off and gain a clearer perspective,  have a glass of water or get a snack for yourself.");
                bullet1.setText("If you are stressed take a break from your activity");
                bullet2.setText("Just sit in one place Quitely, and have a glass of water");
                bullet3.setText("Just close your eyes and give yourself positive thoughts");
                output.setText("Taking time away from work and daily life stresses can improve our health, motivation, relationships, job performance, and perspective and give us the break we need to return to our lives and jobs refreshed and better able to handle whatever arises in our daily work life.");
                break;
            case "Sad":
                title.setText("Pick up a hobby");
                para.setText("Sadness can be so overwhelming sometimes that we lose interest in things we used to enjoy, for example, hobbies");
                bullet1.setText("Try to pick up your hobby back again");
                bullet2.setText("Every day picks up a suitable time and practices it for 30-45 mins");
                bullet3.setText("If you don't have a hobby, try picking a productive one like writing, reading, cooking, or art");
                output.setText("Having a hobby is very imp for a healthy life.\n" +
                        "Pursuing your hobby will release your stress, anxiety, and depression and slowly leads you toward a happy life.\n");
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
                title.setText("Seek Help (Intense/Extreme) ");
                para.setText("Are you showing the following symptoms?\n" +
                        "\n" +
                        "\n" +
                        "Sleep problems.\n" +
                        "Loss of appetite or other eating issues.\n" +
                        "Lack of energy.\n" +
                        "Loss of concentration.\n" +
                        "Problems with self-image or confidence.\n" +
                        "Ongoing thoughts of death or suicide.\n" +
                        "\n" +
                        "If you are showing at least four of the above symptoms, then please seek immediate help. You can call on this number for basic mental healthcare and seek further guidance: 919820466726\n");
                bullet1_Lp.setVisibility(View.GONE);
                bullet2_Lp.setVisibility(View.GONE);
                bullet3_Lp.setVisibility(View.GONE);
                output.setText("Ignoring symptoms of depression can prove fatal for you. We know it is difficult to talk about it. The number we have given you leads to Aasra. Their free, confidential helpline is answered by professionally trained volunteers. So, whatever your concerns are, you can rest assured that you will receive non-judgmental and non-critical listening.");
                break;
            case "Annoyance":
                title.setText("Deep-breathing exercises");
               // para.setText("A time-out basically involves removing yourself from a stressful situation, so you have time to cool off and gain a clearer perspective,  have a glass of water or get a snack for yourself.");
                para.setVisibility(View.GONE);
                bullet1.setText("Take a deep breath, until you feel your lungs are full of air");
                bullet2.setText("Then slowly release the air.");
                bullet3.setText("Repeat this exercise five times, or until you start feeling calm.");
                output.setText("In times of anger, your brain is giving signals to your body that it is in crisis. Slowing down breathing and focusing on one's breath helps tell one's brain that you are safe; therefore, breathing exercises are calming");
                break;
            case "Irritation":
                title.setText("Try to think and write a joke");
                para.setText("Lightening up can help diffuse tension.\n" +
                        " Use humor to help you face what's making you angry and, possibly, any unrealistic expectations you have for how things should go");
                bullet1_Lp.setVisibility(View.GONE);
                bullet2_Lp.setVisibility(View.GONE);
                bullet3_Lp.setVisibility(View.GONE);
                output.setText("Humour is a great icebreaker for any situation, including anger. Just a single smile and you will start feeling better..");
                break;
            case "Upset":
                title.setText("Mindfulness");
                para.setText("A time-out basically involves removing yourself from a stressful situation, so you have time to cool off and gain a clearer perspective,  have a glass of water or get a snack for yourself.");
                para.setVisibility(View.GONE);
                bullet1.setText("Mindfulness means accepting what you are feeling and staying in the present moment. ");
                bullet2.setText("You can do this by looking around and noticing the things around you.");
                bullet3.setText("Don't think of the future. Just focus on what is going on in the present.");
                output.setText("Mindfulness helps you stay in the present and stops you from dwelling on your past feelings as well as future worries ");
                break;
            case "Possessive":
                title.setText("Use it as inspiration");
                para.setText("A time-out basically involves removing yourself from a stressful situation, so you have time to cool off and gain a clearer perspective,  have a glass of water or get a snack for yourself.");
                para.setVisibility(View.GONE);
                bullet1.setText("Possessive behavior occurs when you want something the other has.");
                bullet2.setText("You can use this negative emotion to energize yourself to achieve what you want.");
                bullet3.setText(" Be productive with your jealousy instead of lashing out at others!");
                output.setText("Possessiveness can be a powerful motivator if harnessed in the right direction. \n" +
                        "You can use it as a motivation to better yourself instead of putting other people down.");
                break;
            case "Anxiety":
                title.setText("Talk to someone");
                para.setVisibility(View.GONE);
                bullet1.setText("Sometimes we need a third person's perspective to understand of silly some fears can be.");
                bullet2.setText("Talk to a trusted person with whom you can share all your fears.");
                bullet3.setText("You can also seek the professional help of a therapist if your fear is overtaking your life and is out of your control.");
                output.setText("Fear becomes even scarier when you are dealing with it all alone. By sharing your fears, you can get other people's perspectives and maybe come to see that your fear was irrational.");

                break;
            case "Uncertain":
                title.setText("Be objective in your thoughts");
                para.setVisibility(View.GONE);
                bullet3_Lp.setVisibility(View.GONE);
                bullet1.setText("Treat your thoughts as if they’re opinions, not facts.");
                bullet2.setText(" Even when your thoughts feel true, framing them as opinions helps you step back and look at them in new ways");
                bullet3.setText("Just close your eyes and give yourself positive thoughts");
                output.setText("When you put distance between yourself as an individual and your thoughts, it’s a lot easier to be objective about them.");

                break;
            case "Jealousy":
                title.setText("Reflect and acknowledge");
                para.setText("Jealousy is an all-consuming emotion and we are not able to see reason in midst of it. Before you take any action, sit down and ask yourself these questions:");
                bullet1.setText("Is my feelings reasonable?");
                bullet2.setText("How would I feel if they act like this toward me");
                bullet3.setText("Do I feel inferior?");
                bullet4.setText("Am I happy with what I have?");
                bullet4.setVisibility(View.VISIBLE);
                output.setText("Jealousy by nature is an irrational emotion. \n" +
                        "By reflecting on your jealous feelings, you can come to acknowledge and move on to a more rational line of thought.\n");
                break;
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        String name = getIntent().getStringExtra("name");
        Intent i = new Intent(jogg.this,negativeDirectPage.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.putExtra("name",name);
        startActivity(i);
    }
    }
