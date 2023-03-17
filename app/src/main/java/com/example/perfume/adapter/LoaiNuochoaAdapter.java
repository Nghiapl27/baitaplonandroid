package com.example.perfume.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.perfume.R;
import com.example.perfume.fragment.LoaiFragment;
import com.example.perfume.model.LoaiNuochoa;

import java.util.ArrayList;

public class LoaiNuochoaAdapter extends ArrayAdapter<LoaiNuochoa> {
    private Context context;
    LoaiFragment fragment;
    private ArrayList<LoaiNuochoa> lists;
    TextView tv_maLoai, tv_tenLoai;
    ImageView img_delete;
    public LoaiNuochoaAdapter(@NonNull Context context, LoaiFragment fragment, ArrayList<LoaiNuochoa> lists){
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
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_nuochoa_item,null);
        }

        final LoaiNuochoa item = lists.get(position);
        if(item != null){
            tv_maLoai = v.findViewById(R.id.tv_maLoai_item);
            tv_maLoai.setText("Mã Thương hiệu: "+item.maLoai);
            tv_tenLoai = v.findViewById(R.id.tv_tenLoai_item);
            tv_tenLoai.setText("Tên Thương hiệu: "+item.tenLoai);
            img_delete = v.findViewById(R.id.img_delete_LG_item);
        }
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.xoaLoaiGiay(String.valueOf(item.maLoai));
            }
        });
        return v;

    }
}
