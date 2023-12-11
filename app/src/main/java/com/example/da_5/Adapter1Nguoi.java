package com.example.da_5;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter1Nguoi extends ArrayAdapter<objectDSNV> {

    Context context;
    List<objectDSNV> object;
    public Adapter1Nguoi( Context context, List<objectDSNV> objects) {
        super(context, 0, objects);
        this.context = context;
        this.object = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get data item for this position
        objectDSNV ob = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.hienthi1nguoi, parent, false);
            //convertView = LayoutInflater.from(context).inflate(R.layout.list_item_layout, parent, false);
        }
        //convertView = LayoutInflater.from(getContext()).inflate(R.layout.hienthi1nguoi, parent, false);
        TextView tvId = convertView.findViewById(R.id.id1Nguoi);
        TextView tvName = convertView.findViewById(R.id.name1Nguoi);
        // Chu ý chuyển đổi dữ liệu thành String rồi ms dc setText
        tvId.setText(String.valueOf(ob.getId()));
        tvName.setText(ob.getName());
        return convertView;
    }
}
