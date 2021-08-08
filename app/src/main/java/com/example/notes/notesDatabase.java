package com.example.notes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {notes_entity.class}, version = 1)
public abstract class notesDatabase extends RoomDatabase {

    public abstract notes_dao notesDao();

    public static notesDatabase INSTANCE;

    public static notesDatabase getDatabaseInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), notesDatabase.class, "notes_entity").allowMainThreadQueries().build();
        }
        return INSTANCE;

    }
}
