package com.example.mynote.ui.item_note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.mynote.R;
import com.example.mynote.domain.Note;
import com.example.mynote.domain.NotesRepositoryImpl;
import com.example.mynote.ui.menu.MyDialogFragment;

public class NoteFragment extends Fragment {
    private Note note;
    public static String ARG_NOTE = "note";
    NotesRepositoryImpl notesRepository;

    public static NoteFragment newInstance(Note note) {
        NoteFragment fragment = new NoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_NOTE, note);
        fragment.setArguments(bundle);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_note, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView titleTextView = view.findViewById(R.id.title_view);
        TextView descriptionTextView = view.findViewById(R.id.description_view);
        //show(view);

        if (note != null) {
            titleTextView.setText(this.note.getTitle());
            descriptionTextView.setText(this.note.getDescription());
            show(view);
        }
    }

    private void show(View view) {
        view.findViewById(R.id.button_menu_for_fragment_note).setOnClickListener(view1 -> {
            PopupMenu popupMenu = new PopupMenu(requireContext(), view1);
            requireActivity().getMenuInflater().inflate(R.menu.popup_menu_for_fragment_note, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    switch (menuItem.getItemId()) {
                        case R.id.button_note_delete:
                            //showAlertDialog();
                            notesRepository.deleteNote(note.getId());
                            new MyDialogFragment().show(getActivity().getSupportFragmentManager(), "abc");
                            return true;
                        case R.id.button_note_edit:
                            requireActivity().getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.list_note_view_container, CreateNoteFragment.newInstance(note))
                                    .addToBackStack("abc")
                                    .commit();
                            Toast.makeText(requireContext(), "текст", Toast.LENGTH_SHORT).show();
                            return true;
                        default:
                            return false;
                    }
                }
            });
            popupMenu.show();
        });
    }

    void showAlertDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("AlertDialog")
                .setMessage("удалить заметку?")
                .setPositiveButton("да", (dialogInterface, i) -> {
                    Toast.makeText(requireContext(), this.note.getTitle() + " удалена", Toast.LENGTH_SHORT).show();
                    requireActivity().getSupportFragmentManager().popBackStack();
                })
                .setNeutralButton("нет", null)
                .show();
    }

    public Note getNote() {
        return note;
    }
}
