package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.notes.databinding.ActivityUpdateNoteBinding;
import com.example.notes.databinding.BottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class updateNoteActivity extends AppCompatActivity {

    ActivityUpdateNoteBinding binding;
    String priority = "1";
    String Ttitle, Ssubtitle, Nnote;
    int Id;
    NotesViewModel viewmodel;
    String title, subtitle, note;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateNoteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewmodel = ViewModelProviders.of(this).get(NotesViewModel.class);

        Id = getIntent().getIntExtra("ID", 0);
        Ttitle = getIntent().getStringExtra("TITLE");
        Ssubtitle = getIntent().getStringExtra("SUBTITLE");
        Nnote = getIntent().getStringExtra("NOTE");
        priority = getIntent().getStringExtra("PRIORITY");

        binding.Utitletextview.setText(Ttitle);
        binding.Usubtitletextview.setText(Ssubtitle);
        binding.Unotestextview.setText(Nnote);


        //for default priority on click listener
        switch (priority) {
            case "1":
                binding.greepriority.setImageResource(R.drawable.ic_baseline_done_24);
                binding.yellowpriority.setImageResource(0);
                binding.redpriority.setImageResource(0);
                break;
            case "2":
                binding.greepriority.setImageResource(0);
                binding.yellowpriority.setImageResource(R.drawable.ic_baseline_done_24);
                binding.redpriority.setImageResource(0);
                break;
            case "3":
                binding.greepriority.setImageResource(0);
                binding.yellowpriority.setImageResource(0);
                binding.redpriority.setImageResource(R.drawable.ic_baseline_done_24);
                break;
        }

        //for touch response
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


        binding.updatenotebutton.setOnClickListener(v -> {
            title = binding.Utitletextview.getText().toString();
            subtitle = binding.Usubtitletextview.getText().toString();
            note = binding.Unotestextview.getText().toString();
            updatenote(title, subtitle, note);

        });

    }

    private void updatenote(String title, String subtitle, String note) {
        Date date = new Date();
        CharSequence charSequence = DateFormat.getTimeInstance().format(date.getTime());

        notes_entity note12 = new notes_entity();
        note12.priority = priority;
        note12.note = note;
        note12.id = Id;
        note12.noteSubTitle = subtitle;
        note12.noteTitle = title;
        note12.noteDate = charSequence.toString();

        viewmodel.updateN(note12);
        Toast.makeText(this, "Note Successfully Updated", Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.deletemenuid) {

            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(updateNoteActivity.this,R.style.BottomSheetStyle);
            View view = LayoutInflater.from(updateNoteActivity.this).inflate(R.layout.bottom_sheet, (LinearLayout) findViewById(R.id.layoutid));
            bottomSheetDialog.setContentView(view);
            bottomSheetDialog.show();

            TextView yes,no;
            yes = view.findViewById(R.id.yesbutton);
            no = view.findViewById(R.id.nobutton);

            yes.setOnClickListener(v -> {
                viewmodel.daleteN(Id);
                finish();
            });

            no.setOnClickListener(v -> {
                bottomSheetDialog.hide();
            });

        }
        return true;
    }



}