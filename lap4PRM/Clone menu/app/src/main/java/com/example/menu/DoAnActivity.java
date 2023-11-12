package com.example.menu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DoAnActivity extends AppCompatActivity {
    private ImageView[] imageViews;
    private EditText[] editTexts;
    private Button btnOrder, btnReturnMenu;
    private ArrayList<MenuItem> menuItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_do_an);

        imageViews = new ImageView[]{
                findViewById(R.id.bunbo),
                findViewById(R.id.banhxeo),
                findViewById(R.id.bundau),
                findViewById(R.id.cavien)
        };

        editTexts = new EditText[]{
                findViewById(R.id.slbunbo),
                findViewById(R.id.slbanhxeo),
                findViewById(R.id.slbundau),
                findViewById(R.id.slcavien)
        };

        btnOrder = findViewById(R.id.order);
        btnReturnMenu = findViewById(R.id.cancle);
        btnReturnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoAnActivity.this, MainActivity.class);
                startActivity( intent);

            }
        });
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<MenuItem> menuItems = new ArrayList<>();

                for (int i = 0; i < 4; i++) {
                    String quantityText = editTexts[i].getText().toString().trim();
                    if (!quantityText.isEmpty()) {
                        try {
                            String itemName = imageViews[i].getContentDescription().toString();
                            int itemPrice = getItemPrice(itemName); // Implement this method.
                            Log.d("ĐC lưu", "onClick: "+itemName);
                            int quantity = Integer.parseInt(quantityText);

                            // Create a new MenuItem and add it to the list
                            MenuItem menuItem = new MenuItem(itemName, itemPrice);
                            menuItem.setQuantity(quantity);
                            menuItems.add(menuItem);
                        } catch (NumberFormatException e) {
                            // Handle parsing error
                        }
                    }
                }

                // Pass the menuItems list to MainActivity
                Intent intent = new Intent(DoAnActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("menuItems", (ArrayList<? extends Parcelable>) menuItems);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
    private int getItemPrice(String itemName) {
        // Implement logic to get the price of the item based on its name
        // For simplicity, let's assume a fixed price for each item
        if ("Bún bòa huế".equals(itemName)) {
            return 10; // Set the price for "bunbo"
        } else if ("Bánh xèo".equals(itemName)) {
            return 8; // Set the price for "banhxeo"
        } else if ("Bún đậu mếm tôm".equals(itemName)) {
            return 12; // Set the price for "bundau"
        } else if ("Cá viên chiên".equals(itemName)) {
            return 15; // Set the price for "cavien"
        } else {
            return 0; // Handle unknown items gracefully
        }
    }
    private boolean checkDuplicate(String itemName) {
        // Implement logic to get the price of the item based on its name
        // For simplicity, let's assume a fixed price for each item
        if ("Bún bòa huế".equals(itemName)) {
            return true; // Set the price for "bunbo"
        } else if ("Bánh xèo".equals(itemName)) {
            return true; // Set the price for "banhxeo"
        } else if ("Bún đậu mếm tôm".equals(itemName)) {
            return true; // Set the price for "bundau"
        } else if ("Cá viên chiên".equals(itemName)) {
            return true; // Set the price for "cavien"
        } else {
            return false; // Handle unknown items gracefully
        }
    }

}
