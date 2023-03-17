package com.example.perfume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perfume.fragment.DoanhthuFragment;
import com.example.perfume.fragment.DoimkFragment;
import com.example.perfume.fragment.HoadonFragment;
import com.example.perfume.fragment.LoaiFragment;
import com.example.perfume.fragment.NhanvienFragment;
import com.example.perfume.fragment.SanphamFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class Main extends AppCompatActivity {
    DrawerLayout drawer;
    MaterialToolbar toolbar;
    View mHeaderView;
    TextView edUser;
    private NavigationView nv;
    private BottomNavigationView bm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //        FULL MÀN HÌNH
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
//=================

        setContentView(R.layout.activity_main);
        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);
        nv = findViewById(R.id.nvView);
        bm = findViewById(R.id.btt_nav);
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu_tab_bar);
        ab.setDisplayHomeAsUpEnabled(true);
        FragmentManager manager = getSupportFragmentManager();
        HoadonFragment hoaDonFragment = new HoadonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,hoaDonFragment)
                .commit();

        nv.setCheckedItem(R.id.nav_sp);
        bm.getMenu().findItem(R.id.nav_sp).setChecked(true);
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.tvUser);
        Intent i = getIntent();
        String user = i.getStringExtra("user");
        edUser.setText("Welcome "+user+"!");

        if(user.equalsIgnoreCase("admin")){
            nv.getMenu().findItem(R.id.nav_sub_NhanVien).setVisible(true);
        }

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.nav_hoadon:
                        HoaDonGE();
                        nv.setCheckedItem(R.id.nav_hoadon);
                        bm.getMenu().findItem(R.id.home).setChecked(true);
                        break;
                    case R.id.nav_loai:
                        LoaiGiayGE();
                        nv.setCheckedItem(R.id.nav_loai);
                        bm.getMenu().findItem(R.id.loai).setChecked(true);
                        break;
                    case R.id.nav_sp:
                        GiayGE();
                        nv.setCheckedItem(R.id.nav_sp);
                        bm.getMenu().findItem(R.id.nav_sp).setChecked(true);
                        break;
                    case R.id.nav_doanhthu:
                        DoanhThuGE();
                        nv.setCheckedItem(R.id.nav_doanhthu);
                        break;
                    case R.id.nav_sub_NhanVien:
                        NhanVienGE();
                        nv.setCheckedItem(R.id.nav_sub_NhanVien);
                        break;
                    case R.id.nav_doimk:
                        ChangePassGE();
                        nv.setCheckedItem(R.id.nav_doimk);
                        break;
                    case R.id.nav_dangxuat:
                        AlertDialog.Builder builder = new AlertDialog.Builder(Main.this);
                        builder.setIcon(android.R.drawable.ic_menu_set_as);
                        builder.setTitle( Html.fromHtml("<font color='#d73a31'>Bạn muốn đăng xuất khỏi ứng dụng ?</font>"));
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                startActivity(new Intent(getApplicationContext(), Login.class));
                                finish();
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.show();
                        break;
                }
                drawer.closeDrawers();
                return false;
            }
        });
        bm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager = getSupportFragmentManager();
                switch (item.getItemId()){
                    case R.id.nav_sp:
                        GiayGE();
                        nv.setCheckedItem(R.id.nav_sp);
                        break;
                    case R.id.loai:
                        LoaiGiayGE();
                        nv.setCheckedItem(R.id.nav_loai);
                        break;
                    case R.id.home:
                        HoaDonGE();
                        nv.setCheckedItem(R.id.nav_hoadon);
                        break;
                }
                return true;
            }
        });

    }
    private void HoaDonGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Quản Lý Hóa Đơn");
        HoadonFragment hoaDonFragment = new HoadonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,hoaDonFragment)
                .commit();
    }
    private void GiayGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Quản Lý Sản Phẩm");
        SanphamFragment giayFragment = new SanphamFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,giayFragment)
                .commit();
    }
    private void LoaiGiayGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Quản Lý Loại ");
        LoaiFragment loaiGiayFragment = new LoaiFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,loaiGiayFragment)
                .commit();
    }
    private void DoanhThuGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Thông Kê Doanh Thu");
        DoanhthuFragment doanhThuFragment = new DoanhthuFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,doanhThuFragment)
                .commit();
    }
    private void NhanVienGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Quản Lý Nhân Viên");
        NhanvienFragment nhanVienFragment = new NhanvienFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,nhanVienFragment)
                .commit();
    }
    private void ChangePassGE(){
        FragmentManager manager = getSupportFragmentManager();
        setTitle("Thay Đổi Mật Khẩu");
        DoimkFragment changePassFragment = new DoimkFragment();
        manager.beginTransaction()
                .replace(R.id.flContent,changePassFragment)
                .commit();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id==android.R.id.home)
            drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }
    //Out Ứng Dụng
    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Click 2 Lần Để Thoát", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
//        navigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.nav_sp:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SanphamFragment()).commit();
//                        break;
//                    case R.id.loai:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoaiFragment()).commit();
//                        break;
//                    case R.id.home:
//                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HoadonFragment()).commit();
//                        break;
//
//                }
//            }
//        });
//    }

}