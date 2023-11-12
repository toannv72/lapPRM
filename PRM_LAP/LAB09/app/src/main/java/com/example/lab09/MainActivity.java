package com.example.lab09;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab09.PersonDao.CongViecAdapter;
import com.example.lab09.model.CongViec;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Database database;
    ListView lvCongViec;
    ArrayList<CongViec> arrayCongViec;
    CongViecAdapter adapter;

    EditText edtTen;
    Button btnThem, btnHuy, btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("sqlitedatabase");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9806E6")));

        lvCongViec = findViewById(R.id.listviewCongViec);
        btnNext = findViewById(R.id.btnNext);
        arrayCongViec = new ArrayList<>();
        adapter = new CongViecAdapter(this, R.layout.cong_viec, arrayCongViec);
        lvCongViec.setAdapter(adapter);

        // Create database
        database = new Database(this, "GhiChu.sqlite", null, 1);

        //Create table CV
        database.QueryData("Create table if not exists CongViec(id Integer Primary Key Autoincrement," + "TenCV nvarchar(200))");
//        database.QueryData("Insert into CongViec values(null, 'Project Android')");
//        database.QueryData("Insert into CongViec values(null, 'Design app')");

//        Cursor dataCongViec = database.GetData("Select * from CongViec");
//        while (dataCongViec.moveToNext()) {
//            String ten = dataCongViec.getString(1);
//            Toast.makeText(this, ten, Toast.LENGTH_SHORT).show();
//            int id = dataCongViec.getInt(0);
//            arrayCongViec.add(new CongViec(id, ten));
//        }
//        adapter.notifyDataSetChanged();
        GetDataCongViec();
        btnNext.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PersonActivity.class);
            startActivity(intent);
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_congviec, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuAdd) {
            DialogThem();
        }
        return super.onOptionsItemSelected(item);
    }

    private void DialogThem() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_them_cong_viec);
        dialog.show();

        edtTen = dialog.findViewById(R.id.editTextTenCV);
        btnThem = dialog.findViewById(R.id.buttonThem);
        btnHuy = dialog.findViewById(R.id.buttonHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tencv = edtTen.getText().toString();
                if (tencv.equals("")) {
                    Toast.makeText(MainActivity.this, "Vui lòng nhập tên công việc !", Toast.LENGTH_SHORT).show();
                } else {
                    database.QueryData("Insert into CongViec values(null,'" + tencv + "')");
                    Toast.makeText(MainActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                    GetDataCongViec();
                }
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    private void GetDataCongViec() {
        Cursor dataCongViec = database.GetData("Select * from CongViec");
        arrayCongViec.clear();

        while (dataCongViec.moveToNext()) {
            String ten = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            arrayCongViec.add(new CongViec(id, ten));
        }
        adapter.notifyDataSetChanged();
    }

    public void DialogSuaCongViec(String ten, int id) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialogsua);
        EditText edtTenCV = dialog.findViewById(R.id.editTextTenCV);
        Button btnXacNhan = dialog.findViewById(R.id.buttonXacNhan);
        Button btnHuy = dialog.findViewById(R.id.buttonHuyEdit);
        edtTenCV.setText(ten);
        btnXacNhan.setOnClickListener(view -> {
            String tenMoi = edtTenCV.getText().toString().trim();
            database.QueryData("UPDATE CongViec SET TenCV ='" + tenMoi + "' WHERE id = '" + id + "'");
            Toast.makeText(MainActivity.this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
            GetDataCongViec();
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void DialogxoaCongViec(String tencv, int Id) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xoá công việc " + tencv + " không ? ");
        dialogXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                database.QueryData("DELETE FROM CongViec WHERE Id = '" + Id + "' ");
                Toast.makeText(MainActivity.this, "Đã xoá", Toast.LENGTH_SHORT).show();
                GetDataCongViec();
            }
        });
        dialogXoa.show();
    }
}