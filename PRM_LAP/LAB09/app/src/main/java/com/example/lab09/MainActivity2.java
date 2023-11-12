package com.example.lab09;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.lab09.PersonDao.ModuleAdapter;
import com.example.lab09.model.Module;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    Database database;

    EditText txtTitle, txtDescription, txtCategory;

    Button btnThem, btnHuy;
    ListView lvModule;
    ModuleAdapter adapter;

    ArrayList<Module> arrayModule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setTitle("ModulesApplication");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#9806E6")));

        lvModule = findViewById(R.id.listviewModule);
        arrayModule = new ArrayList<>();
        adapter = new ModuleAdapter(this, R.layout.activity_module, arrayModule);
        lvModule.setAdapter(adapter);

        database = new Database(this, "Module.sqlite", null, 1);
        database.QueryData("Create table if not exists Module(id Integer Primary Key Autoincrement ," + "Title nvarchar(200)," + " Description nvarchar(200)," + "Category nvarchar(200))");
        GetDataCongViec();
    }

    private void GetDataCongViec() {
        Cursor dataCongViec = database.GetData("Select * from Module");
        arrayModule.clear();

        while (dataCongViec.moveToNext()) {
            String category = dataCongViec.getString(3);
            String description = dataCongViec.getString(2);
            String title = dataCongViec.getString(1);
            int id = dataCongViec.getInt(0);
            arrayModule.add(new Module(id, title, description, category));
        }
        adapter.notifyDataSetChanged();
    }

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
        dialog.setContentView(R.layout.dialog_them_module);
        dialog.show();

        txtTitle = dialog.findViewById(R.id.editTitle);
        txtDescription = dialog.findViewById(R.id.editDescription);
        txtCategory = dialog.findViewById(R.id.editCategory);
        btnThem = dialog.findViewById(R.id.btnThem1);
        btnHuy = dialog.findViewById(R.id.btnHuy);

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = txtTitle.getText().toString();
                String description = txtDescription.getText().toString();
                String category = txtCategory.getText().toString();
                if (title.equals("")) {
                    Toast.makeText(MainActivity2.this, "Vui lòng nhập title Module !", Toast.LENGTH_SHORT).show();
                }
                if (description.equals("")) {
                    Toast.makeText(MainActivity2.this, "Vui lòng nhập description Module !", Toast.LENGTH_SHORT).show();
                }
                if (category.equals("")) {
                    Toast.makeText(MainActivity2.this, "Vui lòng nhập category Module !", Toast.LENGTH_SHORT).show();
                } else {
                    String query = "INSERT INTO Module (id, Title, Description, Category) VALUES (null, '" + title + "', '" + description + "', '" + category + "')";
                    database.QueryData(query);
                    Toast.makeText(MainActivity2.this, "Đã thêm", Toast.LENGTH_SHORT).show();
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

    public void DialogSuaModule(int id, String title, String description, String category) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua_module);

        EditText edtTitle = dialog.findViewById(R.id.edtTitle);
        EditText edtDescription = dialog.findViewById(R.id.edtDescription);
        EditText editCategory = dialog.findViewById(R.id.edtCategory);
        Button btnXacNhan = dialog.findViewById(R.id.btnUpdate);
        Button btnHuy = dialog.findViewById(R.id.btnHuy);

        edtTitle.setText(title);
        edtDescription.setText(description);
        editCategory.setText(category);

        btnXacNhan.setOnClickListener(view -> {
            String newTitle = edtTitle.getText().toString().trim();
            String newDescription = edtDescription.getText().toString().trim();
            String newCategory = editCategory.getText().toString().trim();

            String query = "UPDATE Module SET Title = '" + newTitle + "', Description = '" + newDescription + "', Category = '" + newCategory + "' WHERE id = " + id;
            database.QueryData(query);

            Toast.makeText(MainActivity2.this, "Đã cập nhật", Toast.LENGTH_SHORT).show();
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

    public void DialogXoaModule(int id, String title) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xoá công việc " + title + " không ? ");
        dialogXoa.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                database.QueryData("DELETE FROM Module WHERE id = '" + id + "' ");
                Toast.makeText(MainActivity2.this, "Đã xoá", Toast.LENGTH_SHORT).show();
                GetDataCongViec();
            }
        });
        dialogXoa.show();
    }
}