package com.example.notes;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class adapterClass extends RecyclerView.Adapter<adapterClass.noteViewHolder> {


    MainActivity mainActivity;
    List<notes_entity> allnotes;

    public adapterClass(MainActivity mainActivity, List<notes_entity> allnotes) {
        this.mainActivity = mainActivity;
        this.allnotes = allnotes;
    }

    @Override
    public noteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new noteViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.note_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(noteViewHolder holder, int position) {

        notes_entity note555 = allnotes.get(position);

        holder.title.setText(note555.noteTitle);
        holder.subtitle.setText(note555.noteSubTitle);
        holder.date.setText(note555.noteDate);

        switch (note555.priority) {
            case "1":
                holder.priorityview.setBackgroundResource(R.drawable.green_circle);
                break;
            case "2":
                holder.priorityview.setBackgroundResource(R.drawable.yellow_circle);
                break;
            case "3":
                holder.priorityview.setBackgroundResource(R.drawable.red_circle);
                break;
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mainActivity, updateNoteActivity.class);
            intent.putExtra("TITLE", note555.noteTitle);
            intent.putExtra("SUBTITLE", note555.noteSubTitle);
            intent.putExtra("NOTE", note555.note);
            intent.putExtra("ID", note555.id);
            intent.putExtra("PRIORITY", note555.priority);
            mainActivity.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return allnotes.size();
    }




    class noteViewHolder extends RecyclerView.ViewHolder {

        TextView title, subtitle, date;
        View priorityview;

        public noteViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.notestitle);
            subtitle = itemView.findViewById(R.id.notessubtitle);
            date = itemView.findViewById(R.id.notesdate);
            priorityview = itemView.findViewById(R.id.notespriority);
        }
    }

}
