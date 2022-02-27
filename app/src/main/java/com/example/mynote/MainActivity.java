package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NotesListFragment notesListFragment = NotesListFragment.newInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.notes_list, notesListFragment)
                .commit();

        /*if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.notes_list, notesListFragment.newInstance());
        }*/
    }
}