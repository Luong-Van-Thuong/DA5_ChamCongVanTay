package com.example.da_5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class sqlChamCong extends SQLiteOpenHelper {


    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String CUSTOMER_ID = "CUSTOMER_ID";
    public static final String CUSTOMER_THOIGIAN = "CUSTOMER_THOIGIAN";
    public static final String CUSTOMER_NGAYTHANGNAM = "CUSTOMER_NGAYTHANGNAM";
    Context context;
    public sqlChamCong(Context context) {
        super(context, "ChamCong.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + CUSTOMER_TABLE + " (" + CUSTOMER_ID + " INTEGER, " + CUSTOMER_THOIGIAN + " TEXT, " + CUSTOMER_NGAYTHANGNAM + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void addOne(objectChamCong obCC){
        SQLiteDatabase sqlCC = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CUSTOMER_ID, obCC.getId());
        cv.put(CUSTOMER_THOIGIAN, obCC.getThoigian());
        cv.put(CUSTOMER_NGAYTHANGNAM, obCC.getNgaythangnam());
        long insert = sqlCC.insert(CUSTOMER_TABLE, null, cv);
        if(insert == -1){
            Toast.makeText(context, "Lỗi tạo dữ liệu", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(context, "Tạo dữ liệu thành công", Toast.LENGTH_LONG).show();
        }
    }

    public List<objectChamCong> getEvery(){
        List<objectChamCong> returnList = new ArrayList<>();
        String qureryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase dbListCC = this.getWritableDatabase();
        Cursor cursor = dbListCC.rawQuery(qureryString, null);
        if(cursor.moveToNext()){
            do{
                int idluong = cursor.getInt(0);
                String thoigianluong = cursor.getString(1);
                String ngaythangnamluong = cursor.getString(2);
                objectChamCong obcc = new objectChamCong(idluong, thoigianluong, ngaythangnamluong);
                returnList.add(obcc);
            } while(cursor.moveToNext());
        } else {
            return returnList;
        }
        cursor.close();
        dbListCC.close();
        return returnList;
    }



}
