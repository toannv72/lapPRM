package com.example.lab7_exercise2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CALL_PHONE = 1;
    ImageView imgGoogle, imgCall, imgMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgGoogle = findViewById(R.id.imageViewGoogle);
        imgMessage = findViewById(R.id.imageViewMessage);
        imgCall = findViewById(R.id.imageViewCall);

        imgGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://"));
                startActivity(intent);
            }
        });
        imgMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body", "Hello sir/madam");
                intent.setData(Uri.parse("sms:0345821712"));
                startActivity(intent);
            }
        });
        imgCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.CALL_PHONE)
                        == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:0345821712"));
                    startActivity(intent);
                } else {
                    // Request the CALL_PHONE permission if it is not granted.
                    requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, REQUEST_CALL_PHONE);
                }
            }
        });
    }
}