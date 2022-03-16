package com.example.mynote.ui.item_note;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
    private NotesRepositoryImpl notesRepositoryImpl = new NotesRepositoryImpl();
    private EditText editTextTitle;
    private EditText editTextDescription;
    private String title;
    private String description;
    public static String ARG_TITLE = "title";
    public static String ARG_DESCRIPTION = "description";

    public static CreateNoteFragment newInstance() {
        return new CreateNoteFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.title = getArguments().getParcelable(ARG_TITLE);
            this.description = getArguments().getParcelable(ARG_DESCRIPTION);
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

        editTextTitle = view.findViewById(R.id.edit_title_container);
        editTextDescription = view.findViewById(R.id.edit_description_container);

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

        view.findViewById(R.id.button_save_new_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = notesRepositoryImpl.getNotes().size();
                    notesRepositoryImpl.addNote(new Note(index, title, description));
                    requireActivity().getSupportFragmentManager().popBackStack();
            }
        });

        view.findViewById(R.id.button_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requireActivity().getSupportFragmentManager().popBackStack();
            }
        });
    }
}
