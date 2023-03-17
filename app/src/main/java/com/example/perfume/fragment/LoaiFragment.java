package com.example.perfume.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.perfume.R;
import com.example.perfume.adapter.LoaiNuochoaAdapter;
import com.example.perfume.dao.LoaiNuochoaDAO;
import com.example.perfume.model.LoaiNuochoa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;


public class LoaiFragment extends Fragment {

    ListView lv;
    ArrayList<LoaiNuochoa> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaLoaiGiay, edTenLoaiGiay, ed_search;
    Button btnSaveLG, btnCancelLG;
    ImageView img_search;
    static LoaiNuochoaDAO dao;
    LoaiNuochoaAdapter adapter;
    LoaiNuochoa item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_loai_nuochoa, container, false);
        lv = v.findViewById(R.id.lvLoaiGiay);
        fab = v.findViewById(R.id.fab_LG);
        ed_search = v.findViewById(R.id.ed_search_loaiGiay);
        img_search = v.findViewById(R.id.img_search_loaiGiay);
        dao = new LoaiNuochoaDAO(getActivity());
        capNhatLv();

//        tim kiem
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_search.getText().length() == 0){
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin trước khi Search", Toast.LENGTH_SHORT).show();
                }
                capNhatTen_loaiGiay();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(),1);  // 1 update
                return false;
            }
        });

        return v;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.loai_nuochoa_dialog);
        edMaLoaiGiay = dialog.findViewById(R.id.ed_maLoai_dialog);
        edTenLoaiGiay = dialog.findViewById(R.id.edtenLoai_dialog);
        btnCancelLG = dialog.findViewById(R.id.btnCancelLG);
        btnSaveLG = dialog.findViewById(R.id.btnSaveLG);
        edMaLoaiGiay.setEnabled(false);
        if(type != 0){
            edMaLoaiGiay.setText(String.valueOf(item.maLoai));
            edTenLoaiGiay.setText(item.tenLoai);
        }

        btnCancelLG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSaveLG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                item = new LoaiNuochoa();
                item.tenLoai = edTenLoaiGiay.getText().toString();
                if(validate()>0){
                    if(type == 0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm Thương hiệu  Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        item.maLoai = Integer.parseInt(edMaLoaiGiay.getText().toString());
                        if(dao.update(item)>0){
                            Toast.makeText(context,"Cập nhật Thành Công", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Cập nhật Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }

    public void xoaLoaiGiay(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xoá mục này không?");
        builder.setCancelable(true);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dao.delete(Id);
                capNhatLv();
                Snackbar.make(getView(),"Bạn đã xóa thành công",Snackbar.LENGTH_LONG).show();
                dialog.cancel();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }

    void capNhatLv(){
        list = (ArrayList<LoaiNuochoa>)dao.getAll();
        adapter = new LoaiNuochoaAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
        return;
    }

    void capNhatTen_loaiGiay(){
        list = (ArrayList<LoaiNuochoa>)dao.getSearch_loaiGiay(ed_search.getText().toString());
        adapter = new LoaiNuochoaAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
        return;
    }

    public int validate(){
        int check = 1;
        if(edTenLoaiGiay.getText().length()==0){
            Toast.makeText(getContext(),"Bạn chưa nhập Tên ", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}