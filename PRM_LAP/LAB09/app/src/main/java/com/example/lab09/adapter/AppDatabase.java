package com.example.lab09.adapter;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.lab09.PersonDao.PersonDao;
import com.example.lab09.model.Person;

@Database(entities = {Person.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao personDao();
}
