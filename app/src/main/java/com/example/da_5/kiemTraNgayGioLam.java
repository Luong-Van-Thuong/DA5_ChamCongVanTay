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

public class kiemTraNgayGioLam extends ArrayAdapter<objectChamCong> {
    Context context;
    List<objectChamCong> objexts;
    public kiemTraNgayGioLam( Context context,  List<objectChamCong> objects) {
        super(context, 0, objects);
        this.context = context;
        this.objexts = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        objectChamCong obCC = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.mot_ngaydilam, parent, false);
        }
        TextView ngay = convertView.findViewById(R.id.hienThiNgay);
        TextView thoigian = convertView.findViewById(R.id.hienthiThoiGian);
        ngay.setText(obCC.getNgaythangnam());
        thoigian.setText(obCC.getThoigian());
        return convertView;
    }
}
