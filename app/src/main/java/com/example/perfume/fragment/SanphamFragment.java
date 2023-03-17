package com.example.perfume.fragment;

import static android.app.Activity.RESULT_OK;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.perfume.R;
import com.example.perfume.adapter.LoaiSpinnerAdapter;
import com.example.perfume.adapter.NuochoaAdapter;
import com.example.perfume.dao.NuocHoaDAO;
import com.example.perfume.dao.LoaiNuochoaDAO;
import com.example.perfume.model.LoaiNuochoa;
import com.example.perfume.model.Nuochoa;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class SanphamFragment extends Fragment {


    ListView lv;
    ArrayList<Nuochoa> list;
    FloatingActionButton fab;
    Dialog dialog;
    EditText edMaNuocHoa, edTenNuocHoa, edGiaMua, edMoTa, edSoLuong, ed_search;
    Spinner spinner;
    ImageView img_anh, img_search;
    Button btnSave, btnCanl;

    static NuocHoaDAO dao;
    NuochoaAdapter adapter;
    Nuochoa item;
    LoaiSpinnerAdapter spinnerAdapter;
    ArrayList<LoaiNuochoa> listLoaiGiay;
    LoaiNuochoaDAO loaiNuochoaDAODAO;
    int maLoai, positionG;
    final int REQUEST_CODE_FOLDER = 456;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_sanpham, container, false);
        lv = v.findViewById(R.id.lv_NuocHoa);
        fab = v.findViewById(R.id.fab_NuocHoa);
        ed_search = v.findViewById(R.id.ed_search_NuocHoa);
        img_search = v.findViewById(R.id.img_search_Giay);
        dao = new NuocHoaDAO(getActivity());
        capNhatLv();

//        tim kiem Giay theo ten Giay
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ed_search.getText().length() == 0){
                    Toast.makeText(getContext(), "Vui lòng nhập thông tin trước khi Search", Toast.LENGTH_SHORT).show();
                }
                capNhatTen_giay();
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
                openDialog(getActivity(),1);
                return false;
            }
        });

        return v;
    }

    protected void openDialog(final Context context, final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.nuochoa_dialog);
        edMaNuocHoa = dialog.findViewById(R.id.edmaNuocHoa_dialog);
        edTenNuocHoa = dialog.findViewById(R.id.edtenNuocHoa_dialog);
        edGiaMua = dialog.findViewById(R.id.edgiaMua_dialog);
        edSoLuong = dialog.findViewById(R.id.edSoLuong_dialog);
        edMoTa = dialog.findViewById(R.id.edmoTa_dialog);
        img_anh = dialog.findViewById(R.id.img_anh_dialog);
        spinner = dialog.findViewById(R.id.spLoaiNuocHoa);
        btnCanl = dialog.findViewById(R.id.btnCancelGiay);
        btnSave = dialog.findViewById(R.id.btnSaveGiay);

        listLoaiGiay = new ArrayList<LoaiNuochoa>();
        loaiNuochoaDAODAO = new LoaiNuochoaDAO(context);
        listLoaiGiay = (ArrayList<LoaiNuochoa>) loaiNuochoaDAODAO.getAll();
        spinnerAdapter = new LoaiSpinnerAdapter(context,listLoaiGiay);
        spinner.setAdapter(spinnerAdapter);

//        lay ma Loai Nuoc Hoa
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoai = listLoaiGiay.get(position).maLoai;
                Toast.makeText(context,"Chọn"+listLoaiGiay.get(position).tenLoai,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edMaNuocHoa.setEnabled(false);
        if(type != 0) {
            edMaNuocHoa.setText(String.valueOf(item.maNuocHoa));
            edTenNuocHoa.setText(item.tenNuocHoa);
            edGiaMua.setText(String.valueOf(item.giaMua));
            edMoTa.setText(item.moTa);
            edSoLuong.setText(String.valueOf(item.soLuong));
            for (int i = 0; i < listLoaiGiay.size(); i++) {
                if (item.maLoai == (listLoaiGiay.get(i).maLoai)) {
                    positionG = i;
                }
                spinner.setSelection(positionG);
            }
        }

        btnCanl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) img_anh.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArray);
                byte[] hinhAnh = byteArray.toByteArray();
//===========
                item = new Nuochoa();
                item.maLoai = maLoai;
                item.setMaLoai(maLoai);
                item.tenNuocHoa = edTenNuocHoa.getText().toString();
                item.giaMua = edGiaMua.getText().toString();
                item.moTa = edMoTa.getText().toString();
                item.soLuong = edSoLuong.getText().toString();
                item.hinh = hinhAnh;
                if(valible()>0){
                    if(type == 0){
                        if(dao.insert(item)>0){
                            Toast.makeText(context,"Thêm Thành Công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Thêm Thất Bại",Toast.LENGTH_SHORT).show();
                        }

                    }else{

                        item.maNuocHoa = Integer.parseInt(edMaNuocHoa.getText().toString());
                        if(dao.update(item)>0){
                            Toast.makeText(context,"Cập Nhật Thành Công",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"Cập Nhật Thất Bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });

        img_anh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        dialog.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                img_anh.setImageBitmap (bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void xoaGiay(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
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
        list = (ArrayList<Nuochoa>) dao.getAll();
        adapter = new NuochoaAdapter(getActivity(),this,list);
        lv.setAdapter(adapter);
    }

    void capNhatTen_giay(){
        list = (ArrayList<Nuochoa>)dao.getSearch_NuocHoa(ed_search.getText().toString());
        adapter = new NuochoaAdapter(getActivity(), this,list);
        lv.setAdapter(adapter);
        return;
    }

    public int valible(){
        int check = 1;
        if (edTenNuocHoa.getText().length() == 0 || edGiaMua.getText().length() == 0 || edMoTa.getText().length() == 0 || edSoLuong.getText().length() == 0 ){
            Toast.makeText(getContext(),"Bạn phải nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}