package com.ansrnirmano.mentalwellness;

import io.realm.RealmObject;

public class Note extends RealmObject {
   String title;
   String description;
   String id;
   String createdTime2;
   String user;

   long createdTime;

   public String getUser() {
      return user;
   }

   public void setUser(String user) {
      this.user = user;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public long getCreatedTime() {
      return createdTime;
   }

   public void setCreatedTime(long createdTime) {
      this.createdTime = createdTime;
   }

   public void setId(String id){
      this.id = id;
   }

   public String getId(){
      return id;
   }

   public String getCreatedTime2(){ return createdTime2;}
   public void setCreatedTime2(String createdTime2){ this.createdTime2 = createdTime2;}
}
