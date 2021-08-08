package com.example.notes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class NotesViewModel extends AndroidViewModel {

    notesRepository repository;
    public LiveData<List<notes_entity>> getallNOTES;
    public LiveData<List<notes_entity>> getallbyGreen;
    public LiveData<List<notes_entity>> getallbyRED;

    public NotesViewModel(Application application) {
        super(application);

        repository = new notesRepository(application);
        getallNOTES = repository.getall__notes;
        getallbyGreen = repository.getall__bygreen;
        getallbyRED = repository.getall__byred;
    }

    public void insertN(notes_entity y){
        repository.insertnote(y);
    }

    public void daleteN(int id){
        repository.deletenote(id);
    }

    public void updateN(notes_entity y){
        repository.updatenote(y);
    }

}