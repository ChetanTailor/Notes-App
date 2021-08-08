package com.example.notes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface notes_dao {

    @Query("SELECT * FROM notesTable ")
    LiveData<List<notes_entity>> getallnotes();

    @Query("SELECT * FROM notesTable order by priority_column ASC")
    LiveData<List<notes_entity>> getallbygreen();

    @Query("SELECT * FROM notesTable order by priority_column DESC")
    LiveData<List<notes_entity>> getallbyRED();

    @Insert()
    void insertNote(notes_entity... x);

    @Query("DELETE FROM NOTESTABLE WHERE ID= :id")
    void deleteNote(int id);

    @Update()
    void updateNote(notes_entity x);

}
