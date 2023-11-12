package com.example.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import api.TraineeRepository;
import api.TraineeService;
import model.Trainee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class XemActivity extends AppCompatActivity {

    ListView lvTrainee;
    TraineeService traineeService;
    ArrayList<Trainee> traineeList;
    TraineeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem);
        traineeService = TraineeRepository.getTraineeService();
        lvTrainee = (ListView) findViewById(R.id.listViewNhanVien);
        traineeList = new ArrayList<>();
        getAll();
        lvTrainee.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Trainee trainee = traineeList.get(position);
                DialogXoaNhanVien(trainee.getName(), trainee.getId());
                return false;
            }
        });
    }

    private void getAll() {
        Call<Trainee[]> call = traineeService.getAllTrainees();
        traineeList.clear();
        call.enqueue(new Callback<Trainee[]>() {
            @Override
            public void onResponse(Call<Trainee[]> call, Response<Trainee[]> response) {
                Trainee[] trainees = response.body();
                for (Trainee trainee: trainees) {
                    traineeList.add(trainee);
                }
                adapter = new TraineeAdapter(XemActivity.this, R.layout.chi_tiet, traineeList);
                lvTrainee.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Trainee[]> call, Throwable t) {

            }
        });
    }

    public void DialogSuaNhanVien(long id, String name, String email, String phone, String gender) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_sua);

        EditText edtName = (EditText) dialog.findViewById(R.id.editTextTen);
        EditText edtEmail = (EditText) dialog.findViewById(R.id.editTextMail);
        EditText edtPhone = (EditText) dialog.findViewById(R.id.editTextSo);
        EditText edtGender = (EditText) dialog.findViewById(R.id.editTextGioiTinh);
        Button btnXacNhan = (Button) dialog.findViewById(R.id.buttonXacNhan);
        Button btnHuy = (Button) dialog.findViewById(R.id.buttonHuy);

        edtName.setText(name);
        edtEmail.setText(email);
        edtPhone.setText(phone);
        edtGender.setText(gender);
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = edtName.getText().toString().trim();
                String newMail = edtEmail.getText().toString().trim();
                String newPhone = edtPhone.getText().toString().trim();
                String newGender = edtGender.getText().toString().trim();
                if (newName.equals("") || newMail.equals("") || newPhone.equals("") || newGender.equals("")) {
                    Toast.makeText(XemActivity.this, "All information must be field!", Toast.LENGTH_SHORT).show();
                } else {
                    update(id, newName, newMail, newPhone, newGender);
                    Toast.makeText(XemActivity.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }

    private void update(long id, String name, String email, String phone, String gender) {
        Trainee trainee = new Trainee(name, email, phone, gender);
        try {
            Call<Trainee> call = traineeService.updateTrainees(id, trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.body() != null) {
                        Toast.makeText(XemActivity.this, "Updated successfully", Toast.LENGTH_LONG).show();
                        getAll();
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(XemActivity.this, "Fail", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.d("Lỗi", e.getMessage());
        }
    }

    private void delete(long id) {
        Call<Trainee> call = traineeService.deleteTrainees(id);
        call.enqueue(new Callback<Trainee>() {
            @Override
            public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                if (response.body() != null) {
                    Toast.makeText(XemActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                    getAll();
                }
            }

            @Override
            public void onFailure(Call<Trainee> call, Throwable t) {

            }
        });
    }

    public void DialogXoaNhanVien(String name, long id) {
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa nhân viên " + name + " không?");
        dialogXoa.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                delete(id);
                Toast.makeText(XemActivity.this, "Đã xóa" + name, Toast.LENGTH_SHORT).show();

            }
        });
        dialogXoa.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialogXoa.show();
    }
}