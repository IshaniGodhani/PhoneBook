package com.example.phonebook;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {super(context, "PhoneBook", null, 1);}



    @Override
    public void onCreate(SQLiteDatabase db) {

        String query="create table PhoneBook (id integer primary key autoincrement, name text, contact text)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    public void insertData(String name, String contact)
    {
        //System.out.println("name="+name+"contact="+contact);
        String query="insert into PhoneBook (name,contact) values('"+name+"','"+contact+"')";
        SQLiteDatabase database;
        database=getWritableDatabase();
        database.execSQL(query);
    }
    public Cursor viewData()
    {
        String query="select * from PhoneBook";
        SQLiteDatabase sqLiteDatabase;
        sqLiteDatabase=getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        return cursor;
    }

    public void deleteData(int Id)
    {

        String query="delete from PhoneBook where id='"+Id+"'";
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL(query);

    }
    public void updateData(int id, String name, String contact)
    {

        System.out.println("id="+id+"name="+name+"contact="+contact);
        String query="Update PhoneBook set name='"+name+"',contact='"+contact+"' where id='"+id+"'";
        SQLiteDatabase sqLiteDatabase=getWritableDatabase();
        sqLiteDatabase.execSQL(query);
    }
}
