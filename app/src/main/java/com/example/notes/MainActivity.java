package com.example.notes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.icu.lang.UCharacter;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity {

    NotesViewModel view__Model;
    RecyclerView recyclerView;
    adapterClass adapter;
    TextView lowtohigh,nofilter,hightolow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.MArecyclerView);
        view__Model = ViewModelProviders.of(this).get(NotesViewModel.class);

        view__Model.getallNOTES.observe(this,notes_entities -> {

            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            adapter = new adapterClass(MainActivity.this,notes_entities);
            recyclerView.setAdapter(adapter);
        });

        lowtohigh = findViewById(R.id.ltohigh);
        nofilter = findViewById(R.id.nofilter);
        hightolow = findViewById(R.id.hightolow);
        nofilter.setBackgroundResource(R.drawable.selectedbuttonfilter);


        nofilter.setOnClickListener(v -> {
            nofilter.setBackgroundResource(R.drawable.selectedbuttonfilter);
            lowtohigh.setBackgroundResource(R.drawable.yesbtnbar);
            hightolow.setBackgroundResource(R.drawable.yesbtnbar);

            view__Model.getallNOTES.observe(this,notes_entities -> {
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                adapter = new adapterClass(MainActivity.this,notes_entities);
                recyclerView.setAdapter(adapter);
            });

        });
        lowtohigh.setOnClickListener(v -> {
            nofilter.setBackgroundResource(R.drawable.yesbtnbar);
            lowtohigh.setBackgroundResource(R.drawable.selectedbuttonfilter);
            hightolow.setBackgroundResource(R.drawable.yesbtnbar);

            view__Model.getallbyGreen.observe(this,notes_entities -> {
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                adapter = new adapterClass(MainActivity.this,notes_entities);
                recyclerView.setAdapter(adapter);
            });

        });
        hightolow.setOnClickListener(v -> {
            nofilter.setBackgroundResource(R.drawable.yesbtnbar);
            lowtohigh.setBackgroundResource(R.drawable.yesbtnbar);
            hightolow.setBackgroundResource(R.drawable.selectedbuttonfilter);

            view__Model.getallbyRED.observe(this,notes_entities -> {
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                adapter = new adapterClass(MainActivity.this,notes_entities);
                recyclerView.setAdapter(adapter);
            });

        });


    }

    public void addnewnoteCLICKED(View view) {
        Intent intent = new Intent(MainActivity.this , insertNoteActivity.class);
        startActivity(intent);
    }


 //   @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.search_menu,menu);
//
//        MenuItem menuItem = menu.findItem(R.id.search_id);
//        SearchView searchView = (SearchView) menuItem.getActionView();
//        searchView.setQueryHint("Search your note");
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                NotesFilter(newText);
//                return false;
//            }
//        });
//
//
//        return true;
//
//    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.search_id){
            Toast.makeText(this,"Search in Progress",Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}