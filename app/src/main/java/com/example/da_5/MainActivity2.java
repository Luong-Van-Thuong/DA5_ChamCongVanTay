package com.example.da_5;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    EditText etName;
    TextView textId, textTest;
    Button btnAddNV, btnHome, btn;
    sqlDSNV sqldsnv;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addControls();
        eventFirebase();
        btnShow();
        btnHOME();
    }

    private void btnHOME() {
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void btnShow() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                display();
            }
        });
    }

    private void addControls() {
        etName = findViewById(R.id.etNhapTen);
        textId = findViewById(R.id.textId);
        btnHome = findViewById(R.id.btnHome);
        btnAddNV = findViewById(R.id.btnAdd);
        textTest = findViewById(R.id.textView7);
        listView = findViewById(R.id.listView);
        btn = findViewById(R.id.button);
    }
    private void eventBtnFirebase(int id) {
        btnAddNV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                objectDSNV ob = new objectDSNV(id, name);
                sqldsnv.addOne(ob);
//                textTest.setText(ob.getId() + " _ " + ob.getName());
            }
        });
    }
    private void eventFirebase() {
        // Read from the database
        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference();
        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int idThem = dataSnapshot.child("idThem").getValue(Integer.class);
                String stringIdThem = dataSnapshot.child("idThem").getValue().toString().trim();
                textId.setText(stringIdThem);
                sqldsnv = new sqlDSNV(MainActivity2.this);
//                Toast.makeText(MainActivity2.this, 1 , Toast.LENGTH_LONG).show();
                eventBtnFirebase(idThem);
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.");
            }
        });
    }
    public void display(){
        sqlDSNV sqdsnv = new sqlDSNV(MainActivity2.this);
        List<objectDSNV> oblist = sqdsnv.getEveryone();
        Log.d("DEBUG", "Size of oblist: " + oblist.size());
        if (oblist != null) {
            Adapter1Nguoi adapter1Nguoi = new Adapter1Nguoi(this, oblist);
            listView.setAdapter(adapter1Nguoi);

        } else {

        }
    }
}