package com.example.mynote;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynote.domain.Note;
import com.example.mynote.domain.NoteRepositoryImpl;

import java.util.ArrayList;

public class NotesListFragment extends Fragment {
    private Note note;
    public static String ARG_NOTE = "note";

    public static NotesListFragment newInstance(Note note) {
        NotesListFragment fragment = new NotesListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments()!= null) {
            this.note = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_notes, container, false);
        TextView textView = view.findViewById(R.id.title_view);
        TextView textView1 = view.findViewById(R.id.description_view);
        textView.setText(this.note.getTitle());
        textView1.setText(this.note.getDescription());
        return view;
    }
}
