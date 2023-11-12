package com.example.bt4vc;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Screen4Activity extends AppCompatActivity {
    //    TextView data, numberData;
    ListView arrayData;
    String[] arraycourse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_thu4);
//        data = (TextView) findViewById(R.id.data);
//        numberData= (TextView) findViewById(R.id.number);
        arrayData =(ListView) findViewById(R.id.listdata);
        String[] list = getIntent().getStringArrayExtra("dulieu3");
        arraycourse = list;
        if (list != null) {
            arraycourse = list;
            ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    Screen4Activity.this,
                    android.R.layout.simple_list_item_1,
                    arraycourse
            );
            arrayData.setAdapter(adapter);
        }
        arrayData.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Screen4Activity.this, MainActivity.class); // Thay thế NewActivity.class bằng Activity bạn muốn chuyển đến
                startActivity(intent);
            }
        });
//        String dulieubtn1 = getIntent().getStringExtra("dulieu");
//        data.setText(dulieubtn1);

//        Integer dulieuBtn2 = getIntent().getIntExtra("dulieu2", 404);
//        numberData.setText(""+dulieuBtn2);


    }
}

