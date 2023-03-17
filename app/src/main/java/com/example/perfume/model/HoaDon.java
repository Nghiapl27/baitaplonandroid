package com.example.perfume.model;

public class HoaDon {
    public int maHD;
    public int maNV;
    public int maKH;
    public int maNuocHoa;
    public String ngay;
    public String giaHD;
    public int trangThai;
    public byte[] anhmaNuocHoa;

    public HoaDon() {
    }

    public HoaDon(int maHD, int maNV, int maKH, int maNuocHoa, String ngay, String giaHD, int trangThai) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maKH = maKH;
        this.maNuocHoa = maNuocHoa;
        this.ngay = ngay;
        this.giaHD = giaHD;
        this.trangThai = trangThai;
    }
}