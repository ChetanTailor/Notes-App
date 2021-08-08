package com.example.notes;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

public class notesRepository {

    public notes_dao notes__Dao;
    public LiveData<List<notes_entity>> getall__notes;
    public LiveData<List<notes_entity>> getall__bygreen;
    public LiveData<List<notes_entity>> getall__byred;

    public notesRepository(Application application) {
        notesDatabase database = notesDatabase.getDatabaseInstance(application);
        notes__Dao = database.notesDao();

        getall__notes = notes__Dao.getallnotes();
        getall__bygreen = notes__Dao.getallbygreen();
        getall__byred = notes__Dao.getallbyRED();
    }

    public void insertnote(notes_entity note){
        notes__Dao.insertNote(note);
    }

    public void deletenote(int id){
        notes__Dao.deleteNote(id);
    }

    public void updatenote(notes_entity note){
        notes__Dao.updateNote(note);
    }


}
