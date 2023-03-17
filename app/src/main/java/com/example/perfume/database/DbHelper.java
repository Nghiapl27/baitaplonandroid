package com.example.perfume.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "NuocHoa.db";
    public static final int VERSION = 1;
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//      Tao bang NHAN VIEN
        String tableNhanVien=
                "CREATE TABLE NhanVien (" +
                        "maNV INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "hoTenNV TEXT NOT NULL, " +
                        "tenDN TEXT NOT NULL, " +
                        "matKhau TEXT NOT NULL, " +
                        "sdtNV TEXT NOT NULL, " +
                        "hinhNV BLOB NOT NULL)";
        db.execSQL(tableNhanVien);

//      Tao bang LOAI
        String tableLoaiNuocHoa=
                "CREATE TABLE LoaiNuocHoa (" +
                        "maLoai INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenLoai TEXT NOT NULL)" ;
        db.execSQL(tableLoaiNuocHoa);

        //bảng sp
        String tableNuocHoa=
                "CREATE TABLE NuocHoa(" +
                        "maNuocHoa INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "tenNuocHoa TEXT NOT NULL," +
                        "giaMua TEXT NOT NULL, "+
                        "moTa TEXT NOT NULL," +
                        "soLuong TEXT NOT NULL," +
                        "maLoai INTEGER REFERENCES LoaiNuocHoa(maLoai), "+
                        "hinh BLOB NOT NULL )";
        db.execSQL(tableNuocHoa);

//      Tao bang HOADON
        String tableHoaDon=
                "CREATE TABLE HoaDon (" +
                        "maHD INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "maNV INTEGER REFERENCES NhanVien(maNV), " +
                        "maNuocHoa INTEGER REFERENCES NuocHoa(maNuocHoa), "+
                        "ngay TEXT NOT NULL, "+
                        "giaHD TEXT NOT NULL, "+
                        "trangThai INTEGER NOT NULL)";
        db.execSQL(tableHoaDon);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableNhanVien = "DROP TABLE IF EXISTS NhanVien";
        db.execSQL(dropTableNhanVien);

        String dropTableLoaiNuocHoa = "DROP TABLE IF EXISTS LoaiNuocHoa";
        db.execSQL(dropTableLoaiNuocHoa);

        String dropTableNuocHoa = "DROP TABLE IF EXISTS NuocHoa";
        db.execSQL(dropTableNuocHoa);

        String dropTableHoaDon = "DROP TABLE IF EXISTS HoaDon";
        db.execSQL(dropTableHoaDon);

        onCreate(db);
    }
}
