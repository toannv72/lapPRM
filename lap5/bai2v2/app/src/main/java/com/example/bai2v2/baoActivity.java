package com.example.bai2v2;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class baoActivity extends AppCompatActivity {
    private ArrayList<Bao> subjectList;
    private TextView add, delete, edit;
    private EditText titleEdit, desEdit, demucEdit, linkAnh;
    private ImageView imageViewedit;
    private RecyclerView rvUser;
    private UserAdapter adapter;
    private int selectedPosition = RecyclerView.NO_POSITION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        rvUser = findViewById(R.id.rvUser);
        add = findViewById(R.id.addbtn);
        delete = findViewById(R.id.deletebtn);
        edit = findViewById(R.id.editbtn);
        titleEdit = findViewById(R.id.editTitle);
        desEdit = findViewById(R.id.editDesTile);
        demucEdit = findViewById(R.id.editDemuc);
        linkAnh = findViewById(R.id.addLinkImg);
        imageViewedit = findViewById(R.id.imageViewedit);

        subjectList = new ArrayList<>();
        subjectList.add(new Bao("title1", "des1", "demuc1", "https://go.yolo.vn/wp-content/uploads/2019/08/hinh-anh-cho-pomsky-dep-45.jpg"));
        subjectList.add(new Bao("title2", "des2", "demuc2", "https://go.yolo.vn/wp-content/uploads/2019/08/hinh-anh-cho-pomsky-dep-45.jpg"));
        subjectList.add(new Bao("title3", "des3", "demuc3", "https://go.yolo.vn/wp-content/uploads/2019/08/hinh-anh-cho-pomsky-dep-45.jpg"));

        adapter = new UserAdapter(subjectList, this);
        rvUser.setAdapter(adapter);
        rvUser.setLayoutManager(new LinearLayoutManager(this));

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEdit.getText().toString();
                String des = desEdit.getText().toString();
                String demuc = demucEdit.getText().toString();
                String imageUrl = linkAnh.getText().toString();

                Bao newBao = new Bao(title, des, demuc, imageUrl);
                addNewBao(newBao);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != RecyclerView.NO_POSITION) {
                    String title = titleEdit.getText().toString();
                    String des = desEdit.getText().toString();
                    String demuc = demucEdit.getText().toString();
                    String imageUrl;
                    String currentImageUrl = subjectList.get(selectedPosition).getImg();

                    if (linkAnh.getText().toString().isEmpty()) {
                        imageUrl = currentImageUrl;
                    } else {
                        imageUrl = linkAnh.getText().toString();
                    }
                    Bao editedBao = new Bao(title, des, demuc, imageUrl);
                    editBao(editedBao, selectedPosition);
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition != RecyclerView.NO_POSITION) {
                    deleteBao(selectedPosition);
                }
            }
        });


    }

    void addNewBao(Bao bao) {
        subjectList.add(bao);
        adapter.notifyDataSetChanged();
        clearEditTextFields();
        Toast.makeText(this, "Đã thêm mục mới", Toast.LENGTH_SHORT).show();
    }

    void editBao(Bao bao, int position) {
        if (position >= 0 && position < subjectList.size()) {
            subjectList.set(position, bao);
            adapter.notifyDataSetChanged();
            clearEditTextFields();
            Toast.makeText(this, "Đã cập nhật mục", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteBao(int position) {
        if (position >= 0 && position < subjectList.size()) {
            subjectList.remove(position);
            adapter.notifyDataSetChanged();
            clearEditTextFields();
            Toast.makeText(this, "Đã xóa mục", Toast.LENGTH_SHORT).show();
        }
    }

    void clearEditTextFields() {
        titleEdit.setText("");
        desEdit.setText("");
        demucEdit.setText("");
        linkAnh.setText("");
        selectedPosition = RecyclerView.NO_POSITION;
    }



    void displayUserDetails(Bao user, int position) {
        if (user != null) {
            titleEdit.setText(user.getTitle());
            desEdit.setText(user.getDestile());
            demucEdit.setText(user.getDemuc());
            linkAnh.setText(user.getImg());
            selectedPosition = position;
            Glide.with(this)
                    .load(user.getImg())
                    .into(imageViewedit);
        }
    }
}
