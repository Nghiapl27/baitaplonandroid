package com.example.perfume.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.perfume.database.DbHelper;
import com.example.perfume.model.LoaiNuochoa;

import java.util.ArrayList;
import java.util.List;

public class LoaiNuochoaDAO {
    private SQLiteDatabase db;

    public LoaiNuochoaDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiNuochoa ob){
        ContentValues values = new ContentValues();
        values.put("tenLoai", ob.tenLoai);
        return db.insert("LoaiNuocHoa",null,values);
    }

    public int update(LoaiNuochoa ob){
        ContentValues values = new ContentValues();
        values.put("tenLoai",ob.tenLoai);
        return db.update("LoaiNuocHoa",values,"maLoai=?", new String[]{String.valueOf(ob.maLoai)});
    }

    public int delete(String id){
        return db.delete("LoaiNuocHoa","maLoai=?", new String[]{id});
    }

    public List<LoaiNuochoa> getAll(){
        String sql = "SELECT * FROM LoaiNuocHoa";
        return getData(sql);
    }

    public LoaiNuochoa getID(String id){
        LoaiNuochoa objLoaiNuocHoa = new LoaiNuochoa();
        String[] args = new String[]{id + ""};
        Cursor c = db.rawQuery("SELECT maLoai, tenLoai FROM LoaiNuocHoa WHERE maLoai=?", args);
        if(c.moveToFirst()){
            objLoaiNuocHoa.maLoai = c.getInt(0);
            objLoaiNuocHoa.tenLoai = c.getString(1);
        }
        return objLoaiNuocHoa;
    }

   private List<LoaiNuochoa> getData(String sql, String...selectionArgs){
        List<LoaiNuochoa> list = new ArrayList<LoaiNuochoa>();
       Cursor c = db.rawQuery(sql, selectionArgs);
       while (c.moveToNext()){
           LoaiNuochoa ob = new LoaiNuochoa();
           ob.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
           ob.tenLoai = c.getString(c.getColumnIndex("tenLoai"));
           list.add(ob);
       }
       return list;
   }

   public List<LoaiNuochoa> getSearch_loaiGiay(String tenLoai){
        String sql = "SELECT * FROM LoaiNuocHoa WHERE tenLoai LIKE '%"+tenLoai+"%' ";
        return getData(sql);
   }

}
