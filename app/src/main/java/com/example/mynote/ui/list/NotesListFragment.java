package com.example.mynote.ui.list;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynote.MainActivity;
import com.example.mynote.R;
import com.example.mynote.domain.NotesRepository;
import com.example.mynote.publisher.Observer;
import com.example.mynote.ui.Adapter;
import com.example.mynote.domain.Note;
import com.example.mynote.domain.NotesRepositoryImpl;
import com.example.mynote.domain.OnItemClickListener;
import com.example.mynote.ui.item_note.CreateNoteFragment;
import com.example.mynote.ui.item_note.NoteFragment;
import com.example.mynote.ui.menu.setting.AccountFragment;
import com.example.mynote.ui.menu.setting.SettingFragment;

import java.util.ArrayList;

public class NotesListFragment extends Fragment implements OnItemClickListener, Observer {
    NotesRepositoryImpl notesRepositoryImpl = new NotesRepositoryImpl();
    Note currentNote;
    Note nullNote;
    public static String KEY_NOTE = "note";
    Adapter adapter = new Adapter();

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
        setHasOptionsMenu(true);

        initAdapter();
        initRecyclerView(view);


        view.findViewById(R.id.button_create_for_fragment_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Observer observer = new Observer() {
                    @Override
                    public void receiveMessage(Note note) {
                        ((MainActivity) requireActivity()).getPublisher().unSubscribe(this);
                        notesRepositoryImpl.addNote(note);
                        adapter.notifyDataSetChanged();
                    }
                };
                ((MainActivity) requireActivity()).getPublisher().subscribe(observer);

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.list_note_view_container, CreateNoteFragment.newInstance(nullNote))
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

    }

    void initAdapter() {
        adapter.setArrayList(notesRepositoryImpl.getNotes());
        adapter.setOnItemClickListener(this);
    }

    void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.list_note_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragmen_list_notes, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_account:
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.list_note_view_container, new AccountFragment())
                        .addToBackStack(" ")
                        .commit();
                return true;
            case R.id.action_setting:
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.list_note_view_container, new SettingFragment())
                        .addToBackStack("  ")
                        .commit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putParcelable(KEY_NOTE, currentNote);
    }

    @Override
    public void onItemClick(int position) {
        ArrayList<Note> arrayList = notesRepositoryImpl.getNotes();
        currentNote = arrayList.get(position);
        showNoteFragment();
    }

    private void showNoteFragment() {

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            showNoteFragmentLand();
        } else {
            showNoteFragmentPort();
        }
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

    @Override
    public void receiveMessage(Note note) {
        int id = note.getId();
        if (notesRepositoryImpl.getNotes().get(id) != null) {
            notesRepositoryImpl.changeNote(id, note);
            adapter.notifyItemInserted(id);
        } else {
            notesRepositoryImpl.addNote(note);
            adapter.notifyDataSetChanged();
        }
    }
}
