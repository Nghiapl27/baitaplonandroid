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
import com.example.perfume.model.Nuochoa;

import java.util.ArrayList;

public class NuochoaSpinnerAdapter extends ArrayAdapter<Nuochoa> {
    private Context context;
    private ArrayList<Nuochoa> lists;
    TextView tvMaGiaysp, tvTenGiaysp;

    public NuochoaSpinnerAdapter(@NonNull Context context , ArrayList<Nuochoa> lists) {
        super(context, 0,lists);
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.nuochoa_item_spinner,null);
        }
        final Nuochoa item = lists.get(position);
        if(item != null){
            tvMaGiaysp = v.findViewById(R.id.tvMaGiaySp);
            tvMaGiaysp.setText(item.maNuocHoa+". ");
            tvTenGiaysp = v.findViewById(R.id.tvTenGiaySp);
            tvTenGiaysp.setText(item.tenNuocHoa);
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable  View convertView, @NonNull  ViewGroup parent) {
        View v = convertView;
        if(v == null){
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.nuochoa_item_spinner,null);
        }
        final Nuochoa item = lists.get(position);
        if(item != null){
            tvMaGiaysp = v.findViewById(R.id.tvMaGiaySp);
            tvMaGiaysp.setText(item.maNuocHoa+". ");
            tvTenGiaysp = v.findViewById(R.id.tvTenGiaySp);
            tvTenGiaysp.setText(item.tenNuocHoa);
        }
        return v;
    }
}
