package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.mynote.ui.item_note.NoteFragment;
import com.example.mynote.ui.list.NotesListFragment;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.list_note_view_container, NotesListFragment.newInstance())
                    .commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        Fragment backStackFragment = (Fragment) getSupportFragmentManager()
                .findFragmentById(R.id.list_note_view_container);
        if (backStackFragment != null && backStackFragment instanceof NoteFragment) {
            onBackPressed();
        }
    }
}