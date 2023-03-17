package com.example.perfume.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.perfume.database.DbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private SQLiteDatabase db;
    private Context context;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDAO(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int getDanhThu(String tuNgay, String denNgay){
        String sqlDoanhThu = "SELECT SUM(giaHD) as doanhThu FROM HoaDon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{tuNgay,denNgay});

        while (c.moveToNext()){
            try{
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        c.close();
        return list.get(0);
    }
}
