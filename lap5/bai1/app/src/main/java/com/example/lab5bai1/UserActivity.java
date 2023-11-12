package com.example.lab5bai1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    ArrayList<User> userList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        RecyclerView rvUser = findViewById(R.id.rvUser);

        userList= new ArrayList<>();
        userList.add(new User("NguyenTT", "Tran Thanh Nguyen","ABC@gmail.com"));
        userList.add(new User("ToanNV72", "Nguyen Van Toan","ToanNV72@gmail.com"));
        userList.add(new User("HaoLHN", "Luong Huynh Ngoc Hao","hao@gmail.com"));
        userList.add(new User("AnTV", "Tran Van An","AnTV@gmail.com"));
        userList.add(new User("BaoNT", "Nguyen Thanh Bao","BaoNT@gmail.com"));
        userList.add(new User("BTT", "ran Thanh Bang","BTT@gmail.com"));
        userList.add(new User("XuanTTT", "Trab Thi Thanh Xuan","XuanTTT@gmail.com"));
        //setlist to adapter
        UserAdapter adapter = new UserAdapter(userList);
        // adapter to rv
        rvUser.setAdapter(adapter);
        //rv to view
        rvUser.setLayoutManager(new LinearLayoutManager(this));

    }
}