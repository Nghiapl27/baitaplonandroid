package com.example.perfume.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.perfume.database.DbHelper;
import com.example.perfume.model.Nuochoa;

import java.util.ArrayList;
import java.util.List;

public class NuocHoaDAO {
    private SQLiteDatabase db;

    public NuocHoaDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public long insert(Nuochoa ob){
        ContentValues values = new ContentValues();
        values.put("tenNuocHoa",ob.tenNuocHoa);
        values.put("giaMua",ob.giaMua);
        values.put("moTa",ob.moTa);
        values.put("soLuong",ob.soLuong);
        values.put("maLoai", ob.maLoai);
        values.put("hinh", ob.hinh);
        return db.insert("NuocHoa",null,values);
    }
    public int update(Nuochoa ob){
        ContentValues values = new ContentValues();
        values.put("tenNuocHoa",ob.tenNuocHoa);
        values.put("giaMua",ob.giaMua);
        values.put("moTa",ob.moTa);
        values.put("soLuong",ob.soLuong);
        values.put("maLoai", ob.maLoai);
        values.put("hinh", ob.hinh);
        return db.update("NuocHoa",values,"maNuocHoa=?", new String[]{String.valueOf(ob.maNuocHoa)});
    }
    public int delete(String id){
        return db.delete("NuocHoa","maNuocHoa=?", new String[]{id});
    }

    public List<Nuochoa> getAll(){
        String sql = "SELECT * FROM NuocHoa";
        return getData(sql);
    }

    public Nuochoa getID(String id){
        Nuochoa objNuocHoa = new Nuochoa();
        String[] argss = new String[]{ id + ""};
    Cursor c = db.rawQuery("SELECT maNuocHoa, tenNuocHoa, giaMua, moTa, soLuong, maLoai, hinh FROM NuocHoa WHERE maNuocHoa=?", argss);
    if(c.moveToFirst()){
        objNuocHoa.maNuocHoa = c.getInt(0);
        objNuocHoa.tenNuocHoa = c.getString(1);
        objNuocHoa.giaMua = c.getString(2);
        objNuocHoa.moTa = c.getString(3);
        objNuocHoa.soLuong = c.getString(4);
        objNuocHoa.maLoai = c.getInt(5);
        objNuocHoa.hinh = c.getBlob(6);
    }
    return objNuocHoa;
}

//Get data nhieu tham so
   private List<Nuochoa> getData(String sql, String...selectionArgs){
        List<Nuochoa> list = new ArrayList<Nuochoa>();
       Cursor c = db.rawQuery(sql, selectionArgs);
       while (c.moveToNext()){
          Nuochoa ob = new Nuochoa();
           ob.maNuocHoa = Integer.parseInt(c.getString(c.getColumnIndex("maNuocHoa")));
           ob.tenNuocHoa = c.getString(c.getColumnIndex("tenNuocHoa"));
           ob.giaMua = c.getString(c.getColumnIndex("giaMua"));
           ob.moTa = c.getString(c.getColumnIndex("moTa"));
           ob.soLuong = c.getString(c.getColumnIndex("soLuong"));
           ob.maLoai = Integer.parseInt(c.getString(c.getColumnIndex("maLoai")));
           ob.hinh = c.getBlob(c.getColumnIndex("hinh"));
           list.add(ob);
       }
       return list;
   }

   public List<Nuochoa> getSearch_NuocHoa(String tenNuocHoa){
        String sql = "SELECT * FROM NuocHoa WHERE tenNuocHoa LIKE '%"+tenNuocHoa+"%' ";
        return getData(sql);
    }
}
