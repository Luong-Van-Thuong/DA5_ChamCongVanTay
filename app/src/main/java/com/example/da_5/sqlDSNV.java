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

public class sqlDSNV  extends SQLiteOpenHelper {


    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String CUSTOMER_ID = "CUSTOMER_ID";
    public static final String CUSTOMER_NAME = "CUSTOMER_NAME";
    Context context;
    public sqlDSNV(Context context) {
        super(context, "ListDSNV.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + CUSTOMER_TABLE + " (" + CUSTOMER_ID + " INTEGER PRIMARY KEY, " + CUSTOMER_NAME + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
    // Them dư lieu vao ban DSNV
    public void addOne(objectDSNV obdsvn){
        SQLiteDatabase sqlDSNV = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(CUSTOMER_ID, obdsvn.getId());
        cv.put(CUSTOMER_NAME, obdsvn.getName());
        long insert = sqlDSNV.insert(CUSTOMER_TABLE, null, cv);
        if(insert == -1){
            Toast.makeText(context, "Lỗi tạo dữ liệu", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(context, "Tạo dữ liệu thành công", Toast.LENGTH_LONG).show();
        }
    }
    // Show du lieu
    public List<objectDSNV> getEveryone(){
        List<objectDSNV> returnList = new ArrayList<>();
        String qureryString = "SELECT * FROM " + CUSTOMER_TABLE;
        SQLiteDatabase dbListDSNV = this.getWritableDatabase();
        Cursor cursor = dbListDSNV.rawQuery(qureryString, null);
        if(cursor.moveToNext()){
            do{
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                objectDSNV ob = new objectDSNV(customerID, customerName);
                returnList.add(ob);
            } while(cursor.moveToNext());
        } else {
            return returnList;
        }
        cursor.close();
        dbListDSNV.close();
        return returnList;
    }

}





















