package com.example.mynote.ui.list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynote.R;
import com.example.mynote.domain.Note;
import com.example.mynote.domain.NotesRepositoryImpl;
import com.example.mynote.ui.item_note.CreateNoteFragment;
import com.example.mynote.ui.item_note.NoteFragment;

public class NotesListFragment extends Fragment {
    NotesRepositoryImpl notesRepositoryImpl = new NotesRepositoryImpl();
    LinearLayout linearLayout;
    Note currentNote = notesRepositoryImpl.getNotes().get(0);
    public static String KEY_NOTE = "note";


    public static NotesListFragment newInstance() {
        return new NotesListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list_notes, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = (LinearLayout) view.findViewById(R.id.list_note_view);

        view.findViewById(R.id.button_create_for_fragment_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.list_note_view_container, CreateNoteFragment.newInstance())
                        .addToBackStack("")
                        .commit();
            }
        });

        if (savedInstanceState != null) {
            currentNote = savedInstanceState.getParcelable(KEY_NOTE);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showNoteFragment();
        }
        addViewOnLinearlayout();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_NOTE, currentNote);
    }

    private void addViewOnLinearlayout() {
        for (Note n : notesRepositoryImpl.getNotes()) {
            String title = n.getTitle();
            TextView textView = new TextView(getContext());
            textView.setText(title);
            textView.setTextSize(30);
            linearLayout.addView(textView);
            int finalId = n.getId();
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    currentNote = notesRepositoryImpl.getNotes().get(finalId);
                    showNoteFragment();
                }
            });
        }
    }

    private void showNoteFragment() {

        //assignValueCurrentNote(index);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showNoteFragmentLand();
        } else {
            showNoteFragmentPort();
        }
    }

    private void assignValueCurrentNote(int index) {
        currentNote = new Note(index,
                notesRepositoryImpl.getNotes().get(index).getTitle(),
                notesRepositoryImpl.getNotes().get(index).getDescription());
    }


    private void showNoteFragmentPort() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.list_note_view_container, NoteFragment.newInstance(currentNote))
                .addToBackStack("")
                .commit();
    }

    private void showNoteFragmentLand() {
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.note_view_container, NoteFragment.newInstance(currentNote))
                .commit();
    }
}
