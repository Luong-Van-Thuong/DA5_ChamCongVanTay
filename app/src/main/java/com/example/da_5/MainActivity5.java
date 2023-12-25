package com.example.da_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity5 extends AppCompatActivity {

    EditText etID, etNam, etThang;
    Button btnDiMuon, btnDiLam, btnhome;
    sqlChamCong sqlcc;
    ListView lv;
    ArrayAdapter<String> adapter;
    TextView hienthi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        addControls();
        ngayDiLam();
        ngaydimuon();
        btnHome2();
    }

    private void btnHome2() {
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity5.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private void addControls() {
        etID = findViewById(R.id.etIDKiemTraNgayGio);
        etNam = findViewById(R.id.etNhapNamKiemTra);
        etThang = findViewById(R.id.etNhapThangKiemTra);
        btnDiLam = findViewById(R.id.btnNgayDiLam);
        btnDiMuon = findViewById(R.id.btnNgayDiMuon);
        sqlcc = new sqlChamCong(this);
        lv = findViewById(R.id.lsHienThi);
        btnhome = findViewById(R.id.button3);
        hienthi = findViewById(R.id.textView14);
    }
    public void ngayDiLam(){
        btnDiLam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sid = etID.getText().toString();
                String sthang = etThang.getText().toString();
                String snam = etNam.getText().toString();
                int id = Integer.valueOf(sid);
                int thang = Integer.valueOf(sthang);
                int nam = Integer.valueOf(snam);
                List<objectChamCong> lobcc = sqlcc.getEvery();
                SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
                Set<String> uniqueDates = new HashSet<>();
                for(objectChamCong obcc : lobcc){
                    int obccID = obcc.getId();
                    int obccThang = Integer.parseInt(obcc.getNgaythangnam().split("/")[1]);
                    int obccNam = Integer.parseInt(obcc.getNgaythangnam().split("/")[2]);
                    if(obccID == id && obccThang == thang && obccNam == nam){
                        String dateStr = obcc.getNgaythangnam();
                        try{
                            Date date = dateFormat.parse(dateStr);
                            uniqueDates.add(dateFormat.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                List<String> dateList = new ArrayList<>(uniqueDates);
                adapter = new ArrayAdapter<>(MainActivity5.this, android.R.layout.simple_list_item_1, dateList);
                lv.setAdapter(adapter);
                int songay = dateList.size();
                hienthi.setText("Số ngày đi làm: " + String.valueOf(songay));
            }
        });
    }

    public void ngaydimuon(){
        btnDiMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sid = etID.getText().toString();
                String sthang = etThang.getText().toString();
                String snam = etNam.getText().toString();
                int id = Integer.valueOf(sid);
                int thang = Integer.valueOf(sthang);
                int nam = Integer.valueOf(snam);
                List<objectChamCong> lsqlcc = sqlcc.getEvery();

                Map<String, List<Date>> timesByDate = new HashMap<>();
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
                for (objectChamCong entry : lsqlcc) {
                    int entryUserId = entry.getId();
                    int entryMonth = Integer.parseInt(entry.getNgaythangnam().split("/")[1]);
                    int entryYear = Integer.parseInt(entry.getNgaythangnam().split("/")[2]);
                    if (entryUserId == id && entryMonth == thang && entryYear == nam) {
                        String dateStr = entry.getNgaythangnam();
                        String timeStr = entry.getThoigian();

                        try {
                            Date datetimeObj = timeFormat.parse(timeStr);

                            if (!timesByDate.containsKey(dateStr)) {
                                timesByDate.put(dateStr, new ArrayList<>());
                            }

                            timesByDate.get(dateStr).add(datetimeObj);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
                List<String> mindates = new ArrayList<>();
                for (Map.Entry<String, List<Date>> entry : timesByDate.entrySet()) {
                    String date = entry.getKey();
                    List<Date> timesOnDate = entry.getValue();
                    if (timesOnDate.size() >= 2) {
                        Collections.sort(timesOnDate);
                        Date minTime = timesOnDate.get(0);
                        if (minTime.getHours() > 7 || (minTime.getHours() == 7 && minTime.getMinutes() > 0)) {
                            mindates.add(date + " - " + minTime.getHours() + ":" + minTime.getMinutes());
                        }
                    }
                }
                List<String> dateList = new ArrayList<>(mindates);
                adapter = new ArrayAdapter<>(MainActivity5.this, android.R.layout.simple_list_item_1, dateList);
                lv.setAdapter(adapter);
                int songay = dateList.size();
                hienthi.setText("Số ngày đi muộn: " + String.valueOf(songay));
            }
        });
    }
}