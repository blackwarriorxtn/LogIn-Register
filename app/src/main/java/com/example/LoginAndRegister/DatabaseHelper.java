package com.example.LoginAndRegister;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(@Nullable Context context ) {
        super(context, "LoginDb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key,password text)");

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");

    }
    //inserting in database
    public  boolean InsetData(String email, String pass){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put("email",email);
        contentValues.put("pass",pass);
        long ins=db.insert("user",null,contentValues);
        return ins != -1;

    }
    // Checking if email exists
    public  boolean CheckEmail(String email) {
        SQLiteDatabase db=this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor= db.rawQuery("Select * from user where email=?",new String[]{email});
        return cursor.getCount() <= 0;
    }
    //checking email and password
    public  Boolean CheckMailAndPassword(String email , String password){
        SQLiteDatabase db=this.getReadableDatabase();
        @SuppressLint("Recycle") Cursor cursor= db.rawQuery("Select * from user where email=? and passord=?",new String[]{email,password});
        return cursor.getCount() > 0;
    }
}
