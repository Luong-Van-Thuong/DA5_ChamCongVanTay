package com.example.da_5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class HienThiGioNgayLuong extends ArrayAdapter<objectChamCong> {

    List<objectChamCong> lObChamCong;
    Context context;
    public HienThiGioNgayLuong(Context context, List<objectChamCong> lObChamCong) {
        super(context, 0, lObChamCong);
        this.context = context;
        this.lObChamCong = lObChamCong;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        objectChamCong obCC = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.songaycong, parent, false);
        }

        TextView idLuong = convertView.findViewById(R.id.idLuong);
        TextView soGioCong = convertView.findViewById(R.id.tongGioLuong);
        TextView soNgayCong = convertView.findViewById(R.id.tongNgayLuong);
        idLuong.setText(String.valueOf(obCC.getId()));
        soGioCong.setText(obCC.getThoigian());
        soNgayCong.setText(obCC.getNgaythangnam());

        return convertView;
    }
}
