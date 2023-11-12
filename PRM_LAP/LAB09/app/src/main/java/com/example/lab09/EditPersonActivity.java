package com.example.lab09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.example.lab09.adapter.AppDatabase;
import com.example.lab09.constants.Constants;
import com.example.lab09.model.Person;

public class EditPersonActivity extends AppCompatActivity {
    private EditText etFirstName;
    private EditText etLastName;

    private Button btnSave;
    private int mPersonId;

    private Intent intent;
    private AppDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_person);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initViews();

        mDb = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").build();
        intent = getIntent();
        if (intent != null && intent.hasExtra(Constants.UPDATE_Person_Id)) {
            btnSave.setText("Update");
            mPersonId = intent.getIntExtra(Constants.UPDATE_Person_Id, -1);
            AppExecutors.getInstance().diskIO().execute(new Runnable() {
                @Override
                public void run() {
                    Person person = mDb.personDao().loadPersonById(mPersonId);
                    populateUI(person);
                }
            });
        }
    }

    private void populateUI(Person person) {
        if (person == null) {
            return;
        }
        etFirstName.setText(person.getFirstName());
        etLastName.setText(person.getLastName());
    }

    @SuppressLint("WrongViewCast")
    private void initViews() {
        etFirstName = findViewById(R.id.etFirstName);
        etLastName = findViewById(R.id.etLastName);

        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(view -> {
            onSaveButtonClicked();
        });
    }

    public void onSaveButtonClicked() {
        final Person person = new Person(etFirstName.getText().toString(), etLastName.getText().toString());
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                if (!intent.hasExtra(Constants.UPDATE_Person_Id)) {
                    mDb.personDao().insert(person);
                } else {
                    person.setUid(mPersonId);
                    mDb.personDao().update(person);
                }
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}