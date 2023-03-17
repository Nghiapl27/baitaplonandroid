package com.example.perfume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.perfume.R;
import com.example.perfume.model.LoaiNuochoa;

import java.util.ArrayList;

public class LoaiSpinnerAdapter extends ArrayAdapter<LoaiNuochoa> {
    private Context context;
    private ArrayList<LoaiNuochoa> lists;
    TextView tvMaLoaiGiaySP, tvTenLoaiGiaySP;

    public LoaiSpinnerAdapter(@NonNull Context context , ArrayList<LoaiNuochoa> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_nuochoa_item_spinner,null);
        }
        final LoaiNuochoa item = lists.get(position);
        if(item != null){
            tvMaLoaiGiaySP = v.findViewById(R.id.tvMaLoaiGiaySp);
            tvMaLoaiGiaySP.setText(item.maLoai+". ");
            tvTenLoaiGiaySP = v.findViewById(R.id.tvTenLoaiGiaySp);
            tvTenLoaiGiaySP.setText(item.tenLoai);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull  ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_nuochoa_item_spinner,null);
        }
        final LoaiNuochoa item = lists.get(position);
        if(item != null){
            tvMaLoaiGiaySP = v.findViewById(R.id.tvMaLoaiGiaySp);
            tvMaLoaiGiaySP.setText(item.maLoai+". ");
            tvTenLoaiGiaySP = v.findViewById(R.id.tvTenLoaiGiaySp);
            tvTenLoaiGiaySP.setText(item.tenLoai);
        }
        return v;
    }
}
