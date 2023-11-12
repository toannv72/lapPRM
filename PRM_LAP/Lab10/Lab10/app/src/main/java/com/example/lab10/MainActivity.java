package com.example.lab10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import api.TraineeRepository;
import api.TraineeService;
import model.Trainee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TraineeService traineeService;
    EditText edtName, edtEmail, edtPhone, edtGioiTinh;
    Button btnSave, btnXem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = (EditText) findViewById(R.id.editTextName);
        edtEmail = (EditText) findViewById(R.id.editTextEmail);
        edtPhone = (EditText) findViewById(R.id.editTextPhone);
        edtGioiTinh = (EditText) findViewById(R.id.editTextGender);
        btnSave = (Button) findViewById(R.id.buttonSave);
        btnXem = (Button) findViewById(R.id.buttonXem);
        traineeService = TraineeRepository.getTraineeService();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, XemActivity.class));
            }
        });



    }

    private void save() {
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String phone = edtPhone.getText().toString();
        String gender = edtGioiTinh.getText().toString();

        //Tao du lieu dang Trainee (doi tuong Trainee)
        Trainee trainee = new Trainee(name, email, phone, gender);
        try {
            Call<Trainee> call = traineeService.createTrainees(trainee);
            call.enqueue(new Callback<Trainee>() {
                @Override
                public void onResponse(Call<Trainee> call, Response<Trainee> response) {
                    if (response.body() != null) {
                        Toast.makeText(MainActivity.this, "Save successfully", Toast.LENGTH_LONG).show();
                        edtName.setText("");
                        edtEmail.setText("");
                        edtPhone.setText("");
                        edtGioiTinh.setText("");
                    }
                }

                @Override
                public void onFailure(Call<Trainee> call, Throwable t) {
                    Toast.makeText(MainActivity.this, "Save Fail", Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Log.d("Lỗi", e.getMessage());
        }
    }
}