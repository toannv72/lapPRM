package com.example.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.menu.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button goiDoAn, goiDoUong;
    ListView foodList;
    ListView foodListQuantity;
    ArrayList<String> foodNames;
    ArrayList<String> drinkNames;
    ArrayList<Integer> foodQuantity;
    ArrayList<Integer> drinkQuality;
    TextView totalTextView;
    static final int REQUEST_CODE_DOAN = 123;
    static final int REQUEST_CODE_DOUONG = 444;
    TextView clear;
    ArrayAdapter<Integer> foodQuantityAdapter;
    ArrayAdapter<String> foodNameAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        anhxa();

        // Khởi tạo danh sách foodNames và foodQuantity

        foodNames = new ArrayList<>();
        foodQuantity = new ArrayList<>();
        clear.setVisibility(View.GONE);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodQuantityAdapter.clear();
                foodNameAdapter.clear();
                totalTextView.setText("");
                clear.setVisibility(view.GONE);
            }
        });
        goiDoAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DoAnActivity.class);
                startActivityForResult(intent, REQUEST_CODE_DOAN);
            }
        });
        goiDoUong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DoUongActivity.class);
                startActivityForResult(intent, REQUEST_CODE_DOUONG);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_DOAN) {
            if (resultCode == RESULT_OK) {

                ArrayList<MenuItem> menuItems = data.getParcelableArrayListExtra("menuItems");
                Log.d("Check", "onActivityResult: " + menuItems);
                if (menuItems != null) {
                    for (MenuItem item : menuItems) {
                        foodNames.add(item.getName());
                        foodQuantity.add(item.getQuantity());
                        if (foodNames != null && foodQuantity != null) {
                            for (int i = 0; i < foodNames.size(); i++) {
                                if (checkDuplicate(foodNames.get(i).toString())) {

                                }
                            }
                        }
                    }
                }
                setDataToAdapter(foodNames, foodQuantity);

                ///
            }
        } else if (requestCode == REQUEST_CODE_DOUONG) {
            if (resultCode == RESULT_OK) {
                ArrayList<MenuItem> menuItems = data.getParcelableArrayListExtra("menuItemsDrinks");
                Log.d("Check", "onActivityResult: " + menuItems);
                if (menuItems != null) {
                    for (MenuItem item : menuItems) {

                        foodNames.add(item.getName());
                        foodQuantity.add(item.getQuantity());
                    }
                }

                setDataToAdapter(foodNames, foodQuantity);
            } else {
                // Xử lý kết quả khi hoạt động DoUongActivity không thành công (nếu cần)
            }
        }
    }
      void setDataToAdapter(ArrayList foodNames, ArrayList foodQuantity){


            foodNameAdapter = new ArrayAdapter<>(MainActivity.this,
                    android.R.layout.simple_list_item_1, foodNames);
            foodList.setAdapter(foodNameAdapter);

            // Tạo và đặt Adapter cho ListView số lượng tương ứng
            foodQuantityAdapter = new ArrayAdapter<>(MainActivity.this,
                    android.R.layout.simple_list_item_1, foodQuantity);
            foodListQuantity.setAdapter(foodQuantityAdapter);

            if (foodNameAdapter.getCount() > 0) {
                findViewById(R.id.clear).setVisibility(View.VISIBLE);
            }
            totalTextView.setText("Total: " + calculateTotal(foodNames, foodQuantity));
        }

    private int calculateTotal(ArrayList<String> foodNames,ArrayList<Integer> foodQuantity) {
        int total = 0;
        for (int i=0; i< foodNames.size(); i++) {
            total += getTotalFood(foodNames.get(i),foodQuantity.get(i));
            Log.d("TEST", "calculateTotal: "+foodNames.get(i));
        }
        return total;
    }
    private int getTotalFood(String itemName, int quantities) {
        // Implement logic to get the price of the item based on its name
        // For simplicity, let's assume a fixed price for each item

        if ("Coca".equals(itemName)) {
            return 10*quantities;
        } else if ("Bia".equals(itemName)) {
            return 17*quantities;
        } else if ("Nước Suôi".equals(itemName)) {
            return 15*quantities;
        } else if ("Trà Sữa".equals(itemName)) {
            return 16*quantities;
        } else if ("Bún bòa huế".equals(itemName)) {
            return 10*quantities;
        } else if ("Bánh xèo".equals(itemName)) {
            return 8*quantities;
        } else if ("Bún đậu mếm tôm".equals(itemName)) {
            return 12*quantities;
        } else if ("Cá viên chiên".equals(itemName)) {
            return 15*quantities;
        } else {
            return 0;
        }
    }
    private boolean checkDuplicate(String itemName) {
        // Implement logic to get the price of the item based on its name
        // For simplicity, let's assume a fixed price for each item

        if ("Coca".equals(itemName)) {
            return true;
        } else if ("Bia".equals(itemName)) {
            return true;
        } else if ("Nước Suôi".equals(itemName)) {
            return true;
        } else if ("Trà Sữa".equals(itemName)) {
            return true;
        } else if ("Bún bòa huế".equals(itemName)) {
            return true;
        } else if ("Bánh xèo".equals(itemName)) {
            return true;
        } else if ("Bún đậu mếm tôm".equals(itemName)) {
            return true;
        } else if ("Cá viên chiên".equals(itemName)) {
            return true;
        } else {
            return false;
        }
    }
    private void anhxa() {
        goiDoAn = findViewById(R.id.doan);
        goiDoUong = findViewById(R.id.douong);
        foodList = findViewById(R.id.foodnames);
        foodListQuantity = findViewById(R.id.foodquantity);
        totalTextView = findViewById(R.id.tong);
        clear=(TextView) findViewById(R.id.clear);
    }
}
