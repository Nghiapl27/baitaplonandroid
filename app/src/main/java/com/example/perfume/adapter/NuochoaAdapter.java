package com.example.perfume.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.perfume.R;
import com.example.perfume.dao.LoaiNuochoaDAO;
import com.example.perfume.fragment.SanphamFragment;
import com.example.perfume.model.LoaiNuochoa;
import com.example.perfume.model.Nuochoa;

import java.util.ArrayList;

public class NuochoaAdapter extends ArrayAdapter<Nuochoa> {

    private Context context;
    SanphamFragment fragment;
    private ArrayList<Nuochoa> lists;
    TextView tvMaNuocHoa, tvTenNuocHoa, tvGiaMua, tvMaLoaiNuocHoa, tvMoTa, tvSoLuong;
    ImageView imgDeleteGiay, imgHinh;

    public NuochoaAdapter(@NonNull Context context, SanphamFragment fragment, ArrayList<Nuochoa> lists){
        super(context,0,lists);
        this.context = context;
        this.lists = lists;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.nuochoa_item,null);
        }
        final Nuochoa item = lists.get(position);
        if(item != null){
            LoaiNuochoaDAO loaiGiayDAO = new LoaiNuochoaDAO(context);
            LoaiNuochoa loaiGiay = loaiGiayDAO.getID(String.valueOf(item.maLoai));

            tvMaNuocHoa = v.findViewById(R.id.tv_maGiay_item);
            tvMaNuocHoa.setText("Mã: "+item.maNuocHoa);

            tvTenNuocHoa = v.findViewById(R.id.tv_tenGiay_item);
            tvTenNuocHoa.setText("Tên: "+item.tenNuocHoa);

            tvSoLuong = v.findViewById(R.id.tv_soLuongG_item);
            tvSoLuong.setText("Số Lượng: "+item.soLuong);

            tvGiaMua = v.findViewById(R.id.tv_giaMua_item);
            tvGiaMua.setText("Giá bán: "+item.giaMua+" VNĐ");

            tvMaLoaiNuocHoa = v.findViewById(R.id.tv_maLoaiGiay_item);
            tvMaLoaiNuocHoa.setText("Thương hiệu: "+loaiGiay.tenLoai);

            tvMoTa = v.findViewById(R.id.tv_moTa_item);
            tvMoTa.setText("Lưu hương: "+item.moTa);

            imgHinh = v.findViewById(R.id.img_giay_item);
            byte[] hinhGiay = item.hinh;
            Bitmap bitmap;
            bitmap = BitmapFactory.decodeByteArray(hinhGiay, 0, hinhGiay.length);
            imgHinh.setImageBitmap(bitmap);
            imgDeleteGiay = v.findViewById(R.id.img_delete_giay_item);
        }
        imgDeleteGiay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment.xoaGiay(String.valueOf(item.maNuocHoa));
            }
        });

//        if (position % 2 == 0){
//
//        }

        return v;

    }

}
