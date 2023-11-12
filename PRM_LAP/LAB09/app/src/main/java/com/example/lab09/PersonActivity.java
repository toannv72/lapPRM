package com.example.lab09;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.lab09.PersonDao.PersonAdapter;
import com.example.lab09.adapter.AppDatabase;
import com.example.lab09.model.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class PersonActivity extends AppCompatActivity {
    private FloatingActionButton fabAdd;
    private RecyclerView mRecycleView;
    private PersonAdapter mAdapter;
    private AppDatabase mDb;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        fabAdd = findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(view -> {
            startActivity(new Intent(PersonActivity.this, EditPersonActivity.class));
        });

        btnNext = findViewById(R.id.btnNext1);
        btnNext.setOnClickListener(view -> {
            Intent intent = new Intent(PersonActivity.this, MainActivity2.class);
            startActivity(intent);
        });

        mRecycleView = findViewById(R.id.rvPerson);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new PersonAdapter(this);
        mRecycleView.setAdapter(mAdapter);
        mDb = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app-database").build();
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                AppExecutors.getInstance().diskIO().execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        List<Person> tasks = mAdapter.getTasks();
                        mDb.personDao().delete(tasks.get(position));
                        retrieveTasks();
                    }
                });
            }
        }).attachToRecyclerView(mRecycleView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveTasks();
    }

    private void retrieveTasks() {
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Person> persons = mDb.personDao().getAll();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setTasks(persons);
                    }
                });
            }
        });
    }
}
