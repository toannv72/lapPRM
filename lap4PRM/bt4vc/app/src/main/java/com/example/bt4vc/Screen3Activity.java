package com.example.bt4vc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Screen3Activity extends AppCompatActivity {
    TextView data, numberData;
    //    ListView arrayData;
//    String[] arraycourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_thu3);
//        data = (TextView) findViewById(R.id.data);
        numberData= (TextView) findViewById(R.id.number);
//        arrayData =(ListView) findViewById(R.id.array);
//        String[] list = getIntent().getStringArrayExtra("dulieu3");
//        arraycourse = list;
//        ArrayAdapter adapter = new ArrayAdapter<>(
//                SecondActivity.this,
//                android.R.layout.simple_list_item_1,
//                arraycourse
//        );
//
//        arrayData.setAdapter(adapter);

//        String dulieubtn1 = getIntent().getStringExtra("dulieu");
//        data.setText(dulieubtn1);

        Integer dulieuBtn2 = getIntent().getIntExtra("dulieu2", 404);
        numberData.setText(""+dulieuBtn2);

        numberData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Xử lý khi TextView được click
                // Ở đây, bạn có thể chuyển sang một trang mới bằng Intent
                Intent intent = new Intent(Screen3Activity.this, MainActivity.class); // Thay thế NewActivity.class bằng Activity bạn muốn chuyển đến
                startActivity(intent);
            }
        });
    }
}
