
package com.ansrnirmano.easytutonotes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ansrnirmano.mentalwellness.AddNoteActivity;
import com.ansrnirmano.mentalwellness.Journal;
import com.ansrnirmano.mentalwellness.Note;
import com.ansrnirmano.mentalwellness.R;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

   Context context;
   RealmResults<Note> notesList;

   public MyAdapter(Context context, RealmResults<Note> notesList) {
      this.context = context;
      this.notesList = notesList;
   }



   @NonNull
   @Override
   public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.journal_item,parent,false));
   }

   @Override
   public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      Note note = notesList.get(position);
      assert note != null;
      holder.titleOutput.setText(note.getTitle());



      String formatedTime = note.getCreatedTime2();
      holder.timeOutput.setText(formatedTime);

      holder.setBackground(position);

      holder.menuBtn.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

            PopupMenu menu = new PopupMenu(context,view);
            menu.getMenu().add("Delete");
            menu.getMenu().add("Edit");
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
               @Override
               public boolean onMenuItemClick(MenuItem item) {
                  if(item.getTitle().equals("Delete")){
                     //delete the note
                     Realm realm = Realm.getInstance(Journal.RealmUtility.getDefaultConfig());
                     realm.beginTransaction();
                     note.deleteFromRealm();
                     realm.commitTransaction();
                     Toast.makeText(context,"Note deleted",Toast.LENGTH_SHORT).show();
                  }

                  if (item.getTitle().equals("Edit")){
                     Intent intent = new Intent(context, AddNoteActivity.class);
                     intent.putExtra("title",note.getTitle())
                             .putExtra("desc",note.getDescription())
                             .putExtra("date1",note.getCreatedTime2())
                             .putExtra("date",note.getCreatedTime())
                             .putExtra("id",note.getId())
                             .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
                     context.startActivity(intent);
                  }
                  return true;
               }
            });
            menu.show();


         }
      });

      holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
         @Override
         public boolean onLongClick(View v) {

            PopupMenu menu = new PopupMenu(context,v);
            menu.getMenu().add("Delete");
            menu.getMenu().add("Edit");
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
               @Override
               public boolean onMenuItemClick(MenuItem item) {
                  if(item.getTitle().equals("Delete")){
                     //delete the note
                     Realm realm = Realm.getInstance(Journal.RealmUtility.getDefaultConfig());
                     realm.beginTransaction();
                     note.deleteFromRealm();
                     realm.commitTransaction();
                     Toast.makeText(context,"Note deleted",Toast.LENGTH_SHORT).show();
                  }
                  if (item.getTitle().equals("Edit")){
                     Intent intent = new Intent(context,AddNoteActivity.class);
                     intent.putExtra("title",note.getTitle())
                             .putExtra("desc",note.getDescription())
                             .putExtra("date1",note.getCreatedTime2())
                             .putExtra("date",note.getCreatedTime())
                             .putExtra("id",note.getId())
                             .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                             .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);

//
                     context.startActivity(intent);
                  }
                  return true;
               }
            });
            menu.show();

            return true;
         }
      });
      holder.itemView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
            Intent intent = new Intent(context,AddNoteActivity.class);
            intent.putExtra("title",note.getTitle())
                    .putExtra("desc",note.getDescription())
                    .putExtra("date1",note.getCreatedTime2())
                    .putExtra("date",note.getCreatedTime())
                    .putExtra("id",note.getId())
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
            context.startActivity(intent);

         }
      });

   }

   @Override
   public int getItemCount() {
      return notesList.size();
   }

   public class MyViewHolder extends RecyclerView.ViewHolder{
      CardView journalCard;
      TextView titleOutput;

      TextView timeOutput;
      ImageButton menuBtn;

      public MyViewHolder(@NonNull View itemView) {
         super(itemView);
         titleOutput = itemView.findViewById(R.id.journal_title);
         menuBtn = itemView.findViewById(R.id.menu_button);
         timeOutput = itemView.findViewById(R.id.journal_date);
         journalCard = itemView.findViewById(R.id.journal_card);
      }

      public void setBackground(int position){
         final String[] colors = {"#FFBFBF", "#FFF6A4", "#DECFFF"};
         journalCard.setCardBackgroundColor(Color.parseColor(colors[position % colors.length]));
      }
   }
}
