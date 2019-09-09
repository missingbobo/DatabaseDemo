package com.example.datebasedemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private static DBHelper helper;
    private final static String DBNAME = "Reminds";
    private final static int DBVERSION = 1;

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (helper == null) {
            helper = new DBHelper(context);
        }
        return helper;
    }

    public DBHelper(Context context) {
        this(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table births (id Integer primary key,name String,avatar String,gender int,birth long)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void add(Person item) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("name", item.getName());
        value.put("avatar", item.getAvatar());
        value.put("gender", item.getGender());
        value.put("birth", item.getBirth());
        db.insert("births", "", value);
    }

    public void update(Person item) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("name", item.getName());
        value.put("avatar", item.getAvatar());
        value.put("gender", item.getGender());
        value.put("birth", item.getBirth());
        db.update("births", value, "id=" + item.getId(), null);
    }


    public void del(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("id", id);
        db.delete("births", "id=" + id, null);
    }

    public ArrayList<Person> getItems() {
        ArrayList<Person> items = new ArrayList<Person>();
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("births", new String[]{"id", "name", "avatar", "gender", "birth"}, null, null, null, null, null);
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    Person person = new Person();
                    person.setId(cursor.getInt(cursor.getColumnIndex("id")));
                    person.setAvatar(cursor.getString(cursor.getColumnIndex("avatar")));
                    person.setName(cursor.getString(cursor.getColumnIndex("name")));
                    person.setBirth(cursor.getColumnIndex("birth"));
                    person.setGender(cursor.getColumnIndex("gender"));
                    items.add(person);
                }
            } catch (Exception e) {

            } finally {
                db.close();
            }
        }
        return items;
    }
}
