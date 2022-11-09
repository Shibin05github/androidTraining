package com.helloworld.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.helloworld.Model.Contact;
import com.helloworld.Params.Params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    public MyDbHandler(Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Params.TABLE_NAME + "(" + Params.KEY_ID + " INTEGER PRIMARY KEY," +
                Params.KEY_NAME + " TEXT," + Params.KEY_PHONE + " TEXT" + ")";
        Log.d("query is ", "query - " + query);
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Params.KEY_NAME, contact.getName());
        values.put(Params.KEY_PHONE, contact.getPhoneNumber());

        db.insert(Params.TABLE_NAME, null, values);
        Log.d("db", "Successfully inserted");
        db.close();
    }

    public List<Contact> getAllContacts(){
        List<Contact> contactList =new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM "+Params.TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);

        //Loop through query
        if(cursor.moveToFirst()){
            do {
                Contact contact = new Contact();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setPhoneNumber(cursor.getString(1));
                contactList.add(contact);
            }while (cursor.moveToNext());
        }
        return contactList;
    }
}
