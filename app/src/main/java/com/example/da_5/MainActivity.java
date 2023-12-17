package com.example.da_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imgDanhSachNV, imgAddNV, imgTinhLuong, imgXemNgayGio1;
    Button btnCC1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEventImg();
    }

    private void addEventImg() {
        imgAddNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        imgDanhSachNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, MainActivity4.class);
                startActivity(intent1);
            }
        });
        imgTinhLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, MainActivity3.class);
                startActivity(intent2);
            }
        });
        imgXemNgayGio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, MainActivity5.class);
                startActivity(intent3);
            }
        });
    }

    private void addControls() {
        imgDanhSachNV = findViewById(R.id.imgXemNhanVien);
        imgTinhLuong = findViewById(R.id.imgTinhLuong);
        imgXemNgayGio1 = findViewById(R.id.imgXemNgayGio);
        imgAddNV = findViewById(R.id.imgThemNhanVien);

    }
}