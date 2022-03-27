package com.example.mynote.domain;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class NotesRepositoryImpl implements NotesRepository {
    private ArrayList<Note> notesList;

    public NotesRepositoryImpl() {
        notesList = new ArrayList<>();
    }

    @Override
    public ArrayList<Note> getNotes() {
        return notesList;
    }

    public int size() {
        return notesList.size();
    }

    public void addNote(Note note) {
        notesList.add(note);
    }

    public void deleteNote(int id) {
        notesList.remove(id);
    }

    public void changeNote (int id, Note note) {
        notesList.set(id, note);
    }
}
