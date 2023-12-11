package com.example.da_5;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity4 extends AppCompatActivity {
    Button btnHome, btnChamCong;
    EditText etGio, etPhut, etNgay, etThang, etNam;
    TextView tgio, tngaythang, textID1;
    sqlChamCong sqlCC;
    objectChamCong obCC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        addControls();
        btnH();
        eventFirebase();
    }

    private void eventAdd(int id) {
        btnChamCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gio1 = etGio.getText().toString();
                String phut1 = etPhut.getText().toString();
                String ngay1 = etNgay.getText().toString();
                String nam1 = etNam.getText().toString();
                String thang1 = etThang.getText().toString();

                int gio = Integer.parseInt(gio1);
                int phut = Integer.parseInt(phut1);
                int ngay = Integer.parseInt(ngay1);
                int thang = Integer.parseInt(thang1);
                int nam = Integer.parseInt(nam1);

                // Định dạng giờ và phút thành chuỗi có hai chữ số
                String gioString = (gio < 10) ? "0" + gio : String.valueOf(gio);
                String phutString = (phut < 10) ? "0" + phut : String.valueOf(phut);

                // Tạo chuỗi thời gian và ngày tháng năm
                String thoigian = gioString + ":" + phutString;
                String ngaythangnam = ngay + "/" + thang + "/" + nam;

                // In ra kết quả
                System.out.println("Thời gian: " + thoigian);
                System.out.println("Ngày tháng năm: " + ngaythangnam);
                tgio.setText(thoigian);
                tngaythang.setText(ngaythangnam);
                obCC = new objectChamCong(id, thoigian, ngaythangnam);
                sqlCC.addOne(obCC);

            }
        });
    }

    private void addControls() {
        textID1 = findViewById(R.id.idChamCong);
        btnHome = findViewById(R.id.button5);
        btnChamCong = findViewById(R.id.button4);
        etGio = findViewById(R.id.etNhapGio);
        etPhut = findViewById(R.id.etNhapPhut);
        etNgay = findViewById(R.id.etNhapNgay);
        etThang = findViewById(R.id.etNhapThang);
        etNam = findViewById(R.id.etNhapNam);
        tgio = findViewById(R.id.textView11);
        tngaythang = findViewById(R.id.textView12);
    }
    private void btnH() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity4.this, MainActivity.class);
                startActivity(intent);                
            }
        });

    }
    private void eventFirebase() {
        // Read from the database
        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference();
        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int idChamCong = dataSnapshot.child("idChamCong").getValue(Integer.class);
                String stringIdChamCong = dataSnapshot.child("idChamCong").getValue().toString().trim();
                sqlCC = new sqlChamCong(MainActivity4.this);
                textID1.setText(stringIdChamCong);
                eventAdd(idChamCong);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.");
            }
        });
    }
}