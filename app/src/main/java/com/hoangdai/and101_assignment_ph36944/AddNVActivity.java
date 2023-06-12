package com.hoangdai.and101_assignment_ph36944;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import adapter.SpinnerPBAdapter;
import model.NhanVien;
import model.Room;

public class AddNVActivity extends AppCompatActivity {

    private String tenPB ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nvactivity);

        //ánh xạ
        EditText edtMaNV = findViewById(R.id.edtMaNV);
        EditText edtTenNV = findViewById(R.id.edtTenNV);
        Spinner spnTenPB = findViewById(R.id.spnTenPB);
        Button btnThemNV = findViewById(R.id.btnThemNV);
        Button btnTroVe = findViewById(R.id.btnTroVe);

        //set adapter spinner
        ArrayList<Room> listPB = new RoomActivity().getDSPB();
        SpinnerPBAdapter adapter = new SpinnerPBAdapter(AddNVActivity.this, listPB);
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

        //Thêm nhân viên
        btnThemNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String manv = edtMaNV.getText().toString();
                String tennv = edtTenNV.getText().toString();

                NhanVien nhanVienMoi = new NhanVien(manv, tennv, tenPB);
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("nhanVienMoi", nhanVienMoi);
                intent.putExtras(bundle);
                setResult(1, intent);
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