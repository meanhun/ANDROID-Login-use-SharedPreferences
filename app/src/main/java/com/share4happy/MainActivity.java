package com.share4happy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText input_Username,input_Password;
    Button btn_exit, btn_Login;
    CheckBox check_Save;

    String thongtinluu = "tk_mk login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvent();
    }

    private void addEvent() {
        //Nuts thoat
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(1);
            }
        });
        // Nút đăng nhập
        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // lưu thông tin đăng nhập
                SharedPreferences sharedPreferences = getSharedPreferences(thongtinluu,MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                // Luu theo dạng phân rã
                editor.putString("UserName",input_Username.getText().toString());
                editor.putString("Password",input_Password.getText().toString());
                editor.putBoolean("Save",check_Save.isChecked());
                editor.commit();
                Toast.makeText(MainActivity.this,"Đã Đăng nhập!",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void addControls() {
        input_Password = findViewById(R.id.input_Password);
        input_Username = findViewById(R.id.input_user);
        btn_exit = findViewById(R.id.btnExit);
        btn_Login = findViewById(R.id.btnDangNhap);
        check_Save = findViewById(R.id.check_Save);

    }

    @Override
    protected void onResume() {
        super.onResume();
        // hiện thị thông tin đã được lưu
        SharedPreferences sharedPreferences = getSharedPreferences(thongtinluu,MODE_PRIVATE);
        String username = sharedPreferences.getString("UserName","");
        String password = sharedPreferences.getString("Password","");
        boolean save = sharedPreferences.getBoolean("Save",false);
        if (save==true){
            input_Password.setText(password);
            input_Username.setText(username);
        }
    }
}