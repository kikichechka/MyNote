package com.example.mynote.ui.item_note;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynote.R;
import com.example.mynote.domain.Note;
import com.example.mynote.domain.NotesRepositoryImpl;


public class CreateNoteFragment extends Fragment {
    NotesRepositoryImpl notesRepositoryImpl = new NotesRepositoryImpl();
    EditText editTextTitle;
    EditText editTextDescription;
    String title;
    String description;

    public static CreateNoteFragment newInstance() {
        return new CreateNoteFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_create, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextTitle = view.findViewById(R.id.edit_title_container);
        editTextDescription = view.findViewById(R.id.edit_description_container);

        editTextTitle.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                    title = editTextTitle.getText().toString();

                return false;
            }
        });

        editTextDescription.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                description = editTextDescription.getText().toString();

                return false;
            }
        });

        view.findViewById(R.id.button_save_new_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!title.equals("") || !description.equals("")) {
                    notesRepositoryImpl.addNote(new Note(10, title, description));
                    notesRepositoryImpl.getNotes();
                    requireActivity().getSupportFragmentManager().popBackStack();
                }
            }
        });
    }
}
