package com.hoangdai.and101_assignment_ph36944;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import adapter.NhanVienAdapter;
import model.NhanVien;
import model.Room;

public class NhanVienActivity extends AppCompatActivity {
    private ArrayList<NhanVien> listNV;
    private ListView lvNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhan_vien);

        //ánh xạ
        lvNhanVien = findViewById(R.id.lvNhanVien);
        Button btnAddNV = findViewById(R.id.btnAddNV);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Nhân viên");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back2);

        //data
        listNV = new ArrayList<>();
        listNV.add(new NhanVien("NV01", "Nguyễn Văn A", "Nhân sự"));
        listNV.add(new NhanVien("NV02", "Nguyễn Văn B", "Hành chính"));
        listNV.add(new NhanVien("NV03", "Nguyễn Văn C", "Nhân sự"));
        listNV.add(new NhanVien("NV04", "Nguyễn Văn D", "Đào tạo"));

        //adapter
        loadData(listNV);

        btnAddNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NhanVienActivity.this, AddNVActivity.class);
                myLauncher.launch(intent);
            }
        });
    }

    private void loadData(ArrayList<NhanVien> list){
        NhanVienAdapter adapter = new NhanVienAdapter(NhanVienActivity.this, list, myLauncher);
        lvNhanVien.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private ActivityResultLauncher<Intent> myLauncher=
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            //xử lý dữ liệu trả về

                            //Thêm trả về
                            if (result.getResultCode() == 1){
                                Intent intent = result.getData();
                                Bundle bundle = intent.getExtras();
                                NhanVien nhanVienMoi = (NhanVien) bundle.getSerializable("nhanVienMoi");
                                listNV.add(nhanVienMoi);
                                loadData(listNV);
                            }

                            //sửa trả về
                            if (result.getResultCode() == 2){
                                Intent intent = result.getData();
                                Bundle bundle = intent.getExtras();
                                NhanVien nhanVienSua = (NhanVien) bundle.getSerializable("nhanVienSua");
                                int viTriSua = bundle.getInt("viTriSua");
                                listNV.set(viTriSua, nhanVienSua);
                                loadData(listNV);
                            }

                        }
                    });

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menutoolbar, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<NhanVien> listFilter = new ArrayList<>();
                for (NhanVien nv: listNV){
                    if (nv.getTenNV().toLowerCase().contains(newText.toLowerCase())){
                        listFilter.add(nv);

                    }
                }
                loadData(listFilter);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}