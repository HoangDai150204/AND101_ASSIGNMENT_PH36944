package com.hoangdai.and101_assignment_ph36944;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import adapter.SpinnerPBAdapter;
import model.NhanVien;
import model.Room;

public class SuaNVActivity extends AppCompatActivity {

    private String tenPB="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_nvactivity);

        //ánh xạ
        EditText edtMaNV = findViewById(R.id.edtMaNV);
        EditText edtTenNV = findViewById(R.id.edtTenNV);
        Spinner spnTenPB = findViewById(R.id.spnTenPB);
        Button btnSuaNV = findViewById(R.id.btnSuaNV);
        Button btnTroVe = findViewById(R.id.btnTroVe);


        //set adapter spinner
        ArrayList<Room> listPB = new RoomActivity().getDSPB();
        SpinnerPBAdapter adapter = new SpinnerPBAdapter(SuaNVActivity.this, listPB);
        spnTenPB.setAdapter(adapter);

        spnTenPB.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(AddNVActivity.this, listPB.get(position).getNameRoom(), Toast.LENGTH_SHORT).show();
                tenPB = listPB.get(position).getNameRoom();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //nhận dữ liệu
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        NhanVien nhanVien = (NhanVien) bundle.getSerializable("nhanVienSua");
        int viTriSua = bundle.getInt("viTriSua");
        edtMaNV.setText(nhanVien.getMaNV());
        edtTenNV.setText(nhanVien.getTenNV());

        int viTri=-1;
        for (int i = 0; i<listPB.size(); i++){
            if(listPB.get(i).getNameRoom().equals(nhanVien.getTenPB())){
                viTri=i;
                break;
            }
        }
        spnTenPB.setSelection(viTri);

        //chỉnh sửa thông tin
        btnSuaNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String manv = edtMaNV.getText().toString();
                String tennv = edtTenNV.getText().toString();

                NhanVien nhanVienSua = new NhanVien(manv, tennv, tenPB);

                Intent intentSua = new Intent();
                Bundle bundleSua = new Bundle();
                bundleSua.putSerializable("nhanVienSua", nhanVienSua);
                bundleSua.putInt("viTriSua", viTriSua);
                intentSua.putExtras(bundleSua);
                setResult(2, intentSua);
                finish();
            }
        });

        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}