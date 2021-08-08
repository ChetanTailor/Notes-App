package com.example.notes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "notesTable")
public class notes_entity {

    @PrimaryKey(autoGenerate = true)

    public int id;

    @ColumnInfo(name= "priority_column")
    public String priority;

    @ColumnInfo(name= "noteTitle_column")
    public String noteTitle;

    @ColumnInfo(name= "noteSubTitle_column")
    public String noteSubTitle;

    @ColumnInfo(name= "note_column")
    public String note;

    @ColumnInfo(name= "noteData_column")
    public String noteDate;


}
