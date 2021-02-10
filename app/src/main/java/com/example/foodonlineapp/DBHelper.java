package com.example.foodonlineapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodonlineapp.Models.OrdersModel;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    final static String DBname = "mydatabase.db";
    final static int DBVersion = 2;
    public DBHelper(@Nullable Context context) {
        super(context, DBname, null, DBVersion);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table orders"+
                        "(id integer primary key autoincrement,"+
                        "name text,"+
                        "phone text,"+
                        "price integer,"+
                        "image integer,"+
                        "quantity integer,"+
                        "description text,"+
                        "foodname text)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP table if exists orders");
        onCreate(db);

    }
    public boolean insertorder(String name, String phone, int image, int price, String desc, String foodname,int quantity){
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("phone",phone);
        values.put("price",price);
        values.put("image",image);
        values.put("description",desc);
        values.put("foodname",foodname);
        values.put("quantity",quantity);
        long id =database.insert("orders",null,values);
        if(id <= 0){
            return false;
        }else{
            return true;
        }
    }
    public ArrayList<OrdersModel> getorder(){
        ArrayList<OrdersModel> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id, foodname,image,price from orders",null);
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){
                OrdersModel models = new OrdersModel();
                models.setOrdernumber(cursor.getInt(0)+"");
                models.setSolditemname(cursor.getString(1));
                models.setOrderimage(cursor.getInt(2));
                models.setPrice(cursor.getInt(3)+"");
                orders.add(models);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }
}
