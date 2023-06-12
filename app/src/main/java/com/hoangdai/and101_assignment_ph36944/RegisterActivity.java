package com.hoangdai.and101_assignment_ph36944;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileOutputStream;
import java.io.IOException;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //ánh xạ
        TextInputEditText edtUser = findViewById(R.id.edtUser);
        TextInputLayout txtUser = findViewById(R.id.txtUser);
        TextInputEditText edtPass = findViewById(R.id.edtPass);
        TextInputLayout txtPass = findViewById(R.id.txtPass);
        TextInputEditText edtRePass = findViewById(R.id.edtRePass);
        TextInputLayout txtRePass = findViewById(R.id.txtRePass);
        Button btnRegister = findViewById(R.id.btnRegister);
        Button btnBack = findViewById(R.id.btnBack);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String pass = edtPass.getText().toString();
                String rePass = edtRePass.getText().toString();

                //validate
                if (user.equals("") || pass.equals("") || rePass.equals("")){
          //          Toast.makeText(RegisterActivity.this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    if(user.equals("")){
                        edtUser.setError("Vui lòng nhập Username");
                    } else{
                        edtUser.setError(null);
                    }
                    if(pass.equals("")){
                        edtPass.setError("Vui lòng nhập Password");
                    } else{
                        edtPass.setError(null);
                    }
                    if(rePass.equals("")){
                        edtRePass.setError("Vui lòng nhập lại Password");
                    } else{
                        edtRePass.setError(null);
                    }
                } else if (!pass.equals(rePass)){
                    Toast.makeText(RegisterActivity.this, "Nhập mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("user", user);
                    intent.putExtra("pass", pass);
                    setResult(1, intent);
                    finish();
                }

                try {
                    FileOutputStream fileOutputStream = openFileOutput("account.txt", Context.MODE_PRIVATE);
                    fileOutputStream.write(user.getBytes());
                    fileOutputStream.write(pass.getBytes());
                    fileOutputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}