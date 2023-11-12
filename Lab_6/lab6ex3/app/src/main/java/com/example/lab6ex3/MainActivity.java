package com.example.lab6ex3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnChangeBackgroundColor;
    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layout = (ConstraintLayout) findViewById(R.id.constraintLayoutMain);
        btnChangeBackgroundColor = (Button) findViewById(R.id.btnColor);
        registerForContextMenu(btnChangeBackgroundColor);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Color");
        menu.setHeaderIcon(R.mipmap.ic_launcher);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuItemRed) {
            layout.setBackgroundColor(Color.RED);
        } else if (item.getItemId() == R.id.menuItemYellow) {
            layout.setBackgroundColor(Color.YELLOW);
        } else if (item.getItemId() == R.id.menuItemBlue) {
            layout.setBackgroundColor(Color.BLUE);
        }
        return super.onContextItemSelected(item);
    }
}