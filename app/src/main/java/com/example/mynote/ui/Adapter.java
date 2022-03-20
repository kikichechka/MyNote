package com.example.mynote.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynote.R;
import com.example.mynote.domain.Note;
import com.example.mynote.domain.NotesRepositoryImpl;
import com.example.mynote.domain.OnItemClickListener;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    NotesRepositoryImpl notesRepository;
    OnItemClickListener onItemClickListener;
    private ArrayList<Note> arrayList;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setArrayList(ArrayList<Note> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new MyViewHolder (layoutInflater.inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(notesRepository.getNotes().get(position));
        //holder.bindTitleWithItemNote(notesRepository.getNotes().get(position).getTitle());
        //holder.bindDescriptionItemNote(notesRepository.getNotes().get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return notesRepository.getNotes().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        //private CardView cardView;
        private TextView textViewTitle;
        private TextView textViewDescription;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //cardView = (CardView) itemView;
            textViewTitle = (TextView) itemView.findViewById(R.id.title_for_item_note);
            textViewDescription = (TextView) itemView.findViewById(R.id.description_for_item_note);
        }

        public void bindContentWithLayout (Note content) {
            textViewTitle.setText(content.getTitle());
            textViewDescription.setText(content.getDescription());
        }
    }
}
