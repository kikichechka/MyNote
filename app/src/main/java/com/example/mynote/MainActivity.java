package com.example.mynote;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.mynote.ui.item_note.NoteFragment;
import com.example.mynote.ui.list.NotesListFragment;
import com.example.mynote.ui.menu.AboutFragment;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.list_note_view_container, new AboutFragment())
                        .addToBackStack(" ")
                        .commit();
                return true;
            case R.id.action_exit:
                showAlertDialog();
                return true;
        }
        return super.onOptionsItemSelected(item);
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
    void showAlertDialog() {
        new AlertDialog.Builder(this)
                .setTitle("AlertDialog")
                .setMessage("выйти из приложения?")
                .setPositiveButton("да", ((dialogInterface, i) ->{
                    finish();
                }))
                .setNeutralButton("нет", null)
                .show();
    }
}