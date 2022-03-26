package com.example.mynote.ui.item_note;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.mynote.MainActivity;
import com.example.mynote.R;
import com.example.mynote.domain.Note;
import com.example.mynote.domain.NotesRepositoryImpl;
import com.example.mynote.publisher.Publisher;


public class CreateNoteFragment extends Fragment {
    private Note note;
    private NotesRepositoryImpl notesRepositoryImpl = new NotesRepositoryImpl();
    private String title;
    private String description;
    public static String ARG_NOTE = "note";
    int index;

    public static CreateNoteFragment newInstance(Note note) {
        CreateNoteFragment createNoteFragment = new CreateNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);
        createNoteFragment.setArguments(bundle);
        return createNoteFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.note = getArguments().getParcelable(ARG_NOTE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_create, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText editTextTitle = view.findViewById(R.id.edit_title_container);
        EditText editTextDescription = view.findViewById(R.id.edit_description_container);

        if (note != null) {
            index = note.getId();
            editTextTitle.setText(this.note.getTitle());
            editTextDescription.setText(this.note.getDescription());
        } else {
            index = notesRepositoryImpl.size();
        }

        editTextTitleWatcher(editTextTitle);
        editTextDescriptionWatcher(editTextDescription);

        implementationButtonSaveNewNote(view);
        implementationButtonBack(view);

    }

    void editTextTitleWatcher (EditText editTextTitle) {
        editTextTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                title = editTextTitle.getText().toString();
            }
        });
    }

    void editTextDescriptionWatcher (EditText editTextDescription) {
        editTextDescription.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                description = editTextDescription.getText().toString();
            }
        });
    }

    void implementationButtonSaveNewNote (View view) {
        view.findViewById(R.id.button_save_new_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                note = new Note(index, title, description, false);

                ((MainActivity) requireActivity()).getPublisher().sendMessage(note);
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    void implementationButtonBack (View view) {
        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }

}
