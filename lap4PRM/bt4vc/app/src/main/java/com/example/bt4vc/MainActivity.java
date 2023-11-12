package com.example.bt4vc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn,btnNumber,btnArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.truyendulieu);
        btnNumber = (Button) findViewById(R.id.btnNumber);
        btnArray = (Button) findViewById(R.id.btnArray);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,  Screen2Activity.class);
                intent.putExtra("dulieu", "chao cau");
                startActivity(intent);
            }
        });
        btnNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Screen3Activity.class);
                intent.putExtra("dulieu2", 1235478);
                startActivity(intent);
            }
        });
        btnArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Screen4Activity.class);
                String [] arrayCourse ={"Androi","IOS","Python","PHP"};
                intent.putExtra("dulieu3", arrayCourse);
                startActivity(intent);
            }
        });
    }
}