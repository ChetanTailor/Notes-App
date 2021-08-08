package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Toast;

import com.example.notes.databinding.ActivityInsertNoteBinding;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class insertNoteActivity extends AppCompatActivity {

    ActivityInsertNoteBinding binding;
    String title,subtitle,note ;
    NotesViewModel viewModel ;
    String priority ="1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInsertNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = ViewModelProviders.of(this).get(NotesViewModel.class);


        binding.INSERTfab.setOnClickListener(v -> {
            title = binding.INSERTtitletextview.getText().toString();
            subtitle = binding.INSERTsubtitletextview.getText().toString();
            note = binding.INSERTnotetxtv.getText().toString();
            createnote(title,subtitle,note);

        });

        binding.greepriority.setOnClickListener(v -> {
            priority = "1";
            binding.greepriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.yellowpriority.setImageResource(0);
            binding.redpriority.setImageResource(0);
        });
        binding.yellowpriority.setOnClickListener(v -> {
            priority = "2";
            binding.greepriority.setImageResource(0);
            binding.yellowpriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redpriority.setImageResource(0);
        });
        binding.redpriority.setOnClickListener(v -> {
            priority = "3";
            binding.greepriority.setImageResource(0);
            binding.yellowpriority.setImageResource(0);
            binding.redpriority.setImageResource(R.drawable.ic_baseline_done_24);
        });

    }

    private void createnote(String title, String subtitle, String note) {
        Date date = new Date();
        CharSequence charSequence = DateFormat.getTimeInstance().format(date.getTime());

        notes_entity note123 = new notes_entity();
        note123.noteSubTitle = subtitle;
        note123.noteTitle = title;
        note123.note = note;
        note123.noteDate = charSequence.toString();
        note123.priority = priority;
        //main provider to viewModel
        viewModel.insertN(note123);
        Toast.makeText(this,"Note Successfully Saved",Toast.LENGTH_SHORT).show();
        finish();

    }


}