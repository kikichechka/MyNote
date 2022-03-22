package com.example.mynote.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynote.R;
import com.example.mynote.domain.Note;
import com.example.mynote.domain.NotesRepositoryImpl;
import com.example.mynote.domain.OnItemClickListener;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    OnItemClickListener onItemClickListener;
    private ArrayList<Note> arrayList;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setArrayList(ArrayList<Note> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        MyViewHolder myViewHolder = new MyViewHolder (layoutInflater.inflate(R.layout.item_note, parent, false));
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindContentWithLayout(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTitle;
        private TextView textViewDescription;
        private ToggleButton toggleButton;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.title_for_item_note);
            textViewDescription = (TextView) itemView.findViewById(R.id.description_for_item_note);
            toggleButton = (ToggleButton) itemView.findViewById(R.id.like_in_item_note);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getLayoutPosition());
                    }
                }
            });
        }

        public void bindContentWithLayout (Note content) {
            textViewTitle.setText(content.getTitle());
            textViewDescription.setText(content.getDescription());
            toggleButton.setChecked(content.isLike());
        }
    }
}
