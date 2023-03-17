package com.example.perfume.model;

public class Nuochoa {
//    them thuoc tinh hinh anh vao day
    public int maNuocHoa;
    public String tenNuocHoa;
    public String giaMua;
    public String moTa;
    public String soLuong;
    public int maLoai;
    public byte[] hinh;
    public byte[] tenNuocHoaa;

    public Nuochoa() {
    }

    public Nuochoa(int maNuocHoa, String tenNuocHoa, String giaMua, String moTa, String soLuong, int maLoai, byte[] hinh) {
        this.maNuocHoa = maNuocHoa;
        this.tenNuocHoaa = tenNuocHoaa;
        this.giaMua = giaMua;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.maLoai = maLoai;
        this.hinh = hinh;
    }

    public int getMaNuocHoa() {
        return maNuocHoa;
    }

    public void setMaNuocHoa(int maNuocHoa) {
        this.maNuocHoa = maNuocHoa;
    }

    public String getTenNuocHoa() {
        return tenNuocHoa;
    }

    public void setTenNuocHoa(String tenNuocHoa) {
        this.tenNuocHoa = tenNuocHoa;
    }

    public String getGiaMua() {
        return giaMua;
    }

    public void setGiaMua(String giaMua) {
        this.giaMua = giaMua;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}