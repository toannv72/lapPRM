package com.example.baicycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnMain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnMain= (Button) findViewById(R.id.btnMain);
        btnMain.setOnClickListener((view -> {
            Intent intent= new Intent(MainActivity.this, Screen2Activity.class);
            startActivity(intent);
        }));
        Log.d("AAA", "onCreate: Main");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("AAA", "onStart: Second");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("AAA", "onStop: Second");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("AAA", "onDestroy: Second");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("AAA", "onPause: Second");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AAA", "onResume: Second");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("AAA", "onRestart: Second");
    }
}