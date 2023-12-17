package com.example.da_5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity3 extends AppCompatActivity {

    EditText etIDLuong, etThangLuong, etNamLuong;
    Button btnTingLuong, btnTinhLuongGio, btnhome;
    ListView listView;
    TextView tongluong, textHienThiNgayGio;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        addControls();
        tinhLuong();

    }



    private void addControls() {
        etIDLuong = findViewById(R.id.etIdLuong);
        etThangLuong = findViewById(R.id.etThangLuong);
        etNamLuong = findViewById(R.id.etNamLuong);
        btnTingLuong = findViewById(R.id.btnTingLuong);
        listView = findViewById(R.id.lsngathang);
        tongluong = findViewById(R.id.textTongLuong);
        btnTinhLuongGio = findViewById(R.id.btnTinhLuongGio);
        btnhome = findViewById(R.id.btnHome1);
        textHienThiNgayGio = findViewById(R.id.textSoNgayLam);

    }

    private void tinhLuong(){
        btnTingLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float luongcoban = ((6400000 + 500000 + 800000 + 300000 + 500000)/26);
                float luongngay = songaylam() * luongcoban;
                String luongngayString = String.format("%.2f", luongngay);
                tongluong.setText("Lương thực lĩnh: " + luongngayString);
            }
        });
        btnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnTinhLuongGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double luong = sogiolam() * ((4400000 + 500000 + 800000 + 300000 + 500000)/26);
                String luongString = String.format("%.3f", luong);
                tongluong.setText("Lương thực lĩnh: " + luongString);
            }
        });
    }

    public double sogiolam() {
        sqlChamCong sqlcc = new sqlChamCong(MainActivity3.this);
        List<objectChamCong> lsqlcc = sqlcc.getEvery();

//        if (lsqlcc != null) {
//            HienThiGioNgayLuong htgiongaylam = new HienThiGioNgayLuong(this, lsqlcc);
//            listView.setAdapter(htgiongaylam);
//            //Toast.makeText(MainActivity3.this, "Chạy hàm hiển thị rồi" , Toast.LENGTH_LONG).show();
//        } else {
//            //Toast.makeText(MainActivity3.this, "Lỗi hàm hiển thị rồi" , Toast.LENGTH_LONG).show();
//        }

        // Lấy dữ liệu từ người dùng nhập vào

        String idluong1 = etIDLuong.getText().toString();
        String thangluong1 = etThangLuong.getText().toString();
        String namluong1 = etNamLuong.getText().toString();

        int idtinhluong = Integer.valueOf(idluong1);
        int thangtinhluong = Integer.valueOf(thangluong1);
        int namtinhluong = Integer.valueOf(namluong1);
        //Toast.makeText(MainActivity3.this, thangtinhluong + "_" + namtinhluong , Toast.LENGTH_LONG).show();
        // Tạo một bản đồ để lưu trữ thời gian theo từng ngày
// Tạo một bản đồ để lưu trữ thời gian theo từng ngày
        Map<String, List<Date>> timesByDate = new HashMap<>();

        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

// Lọc chỉ những ngày thuộc tháng và năm do người dùng nhập
        for (objectChamCong entry : lsqlcc) {
            int entryUserId = entry.getId();
            int entryMonth = Integer.parseInt(entry.getNgaythangnam().split("/")[1]);
            int entryYear = Integer.parseInt(entry.getNgaythangnam().split("/")[2]);

            // Kiểm tra xem id, tháng và năm có trùng khớp không
            if (entryUserId == idtinhluong && entryMonth == thangtinhluong && entryYear == namtinhluong) {
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

        double totalDifferenceHours = 0;
        // Tính hiệu thời gian (Max - Min) cho mỗi ngày
        long hours1 = 0;
        long remainingMinutes1 = 0;
        for (Map.Entry<String, List<Date>> entry : timesByDate.entrySet()) {
            String date = entry.getKey();
            List<Date> timesOnDate = entry.getValue();

            if (timesOnDate.size() >= 2) {
                // Sắp xếp thời gian để tìm giá trị lớn nhất và nhỏ nhất
                Collections.sort(timesOnDate);
                Date minTime = timesOnDate.get(0);
                Date maxTime = timesOnDate.get(timesOnDate.size() - 1);

                // Tính hiệu thời gian
//                long timeDifferenceMillis = maxTime.getTime() - minTime.getTime();
//                double timeDifferenceHours = timeDifferenceMillis / (60 * 60 * 1000);
                long timeDifferenceMillis = maxTime.getTime() - minTime.getTime();
                double timeDifferenceHours = timeDifferenceMillis / (60.0 * 60.0 * 1000.0);

                System.out.println("Số giờ đi làm trong ngày " + date + ": " + timeDifferenceHours + " giờ");
                totalDifferenceHours += timeDifferenceHours;
            } else {
                System.out.println("Không đủ dữ liệu để tính hiệu thời gian trong ngày " + date);
            }
        }
        String timeString = String.format("%.1f", totalDifferenceHours);
        textHienThiNgayGio.setText("Số giờ làm được: " + timeString);
//        tongluong.setText(String.valueOf(totalDifferenceHours));
        return totalDifferenceHours;
    }

    public int songaylam(){
        sqlChamCong sqlcc = new sqlChamCong(MainActivity3.this);
        List<objectChamCong> lsqlcc = sqlcc.getEvery();
//        if (lsqlcc != null) {
//            HienThiGioNgayLuong htgiongaylam = new HienThiGioNgayLuong(this, lsqlcc);
//            listView.setAdapter(htgiongaylam);
//            //Toast.makeText(MainActivity3.this, "Chạy hàm hiển thị rồi" , Toast.LENGTH_LONG).show();
//        } else {
//            //Toast.makeText(MainActivity3.this, "Lỗi hàm hiển thị rồi" , Toast.LENGTH_LONG).show();
//        }

        // Lấy dữ liệu từ người dùng nhập vào

        String idluong1 = etIDLuong.getText().toString();
        String thangluong1 = etThangLuong.getText().toString();
        String namluong1 = etNamLuong.getText().toString();

        int idtinhluong = Integer.valueOf(idluong1);
        int thangtinhluong = Integer.valueOf(thangluong1);
        int namtinhluong = Integer.valueOf(namluong1);
        Map<String, List<Date>> timesByDate = new HashMap<>();
        Set<String> uniqueDates = new HashSet<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

// Lọc chỉ những ngày thuộc tháng và năm do người dùng nhập
        for (objectChamCong entry : lsqlcc) {
            int entryUserId = entry.getId();
            int entryMonth = Integer.parseInt(entry.getNgaythangnam().split("/")[1]);
            int entryYear = Integer.parseInt(entry.getNgaythangnam().split("/")[2]);

            // Kiểm tra xem id, tháng và năm có trùng khớp không
            if (entryUserId == idtinhluong && entryMonth == thangtinhluong && entryYear == namtinhluong) {
                String dateStr = entry.getNgaythangnam();

                try {
                    Date date = dateFormat.parse(dateStr);
                    uniqueDates.add(dateFormat.format(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
        // In số ngày khác nhau
        int soNgayDiLam = uniqueDates.size();
        String textSoNgay = String.valueOf(soNgayDiLam);
//        System.out.println("Số ngày khác nhau trong tháng 12 năm 2023 với id 1: " + numUniqueDates);
        textHienThiNgayGio.setText("Số ngày làm được: " + textSoNgay);
//        List<String> dateList = new ArrayList<>(uniqueDates);
//        adapter = new ArrayAdapter<>(MainActivity3.this, android.R.layout.simple_list_item_1, dateList);
//        listView.setAdapter(adapter);
        return soNgayDiLam;
    }

}































