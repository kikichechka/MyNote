package com.example.mynote.domain;

import java.util.ArrayList;

public class NoteRepositoryImpl implements NoteRepository{
    private static final NoteRepository INSTANCE = new NoteRepositoryImpl();

    public static NoteRepository getInstance() {
        return INSTANCE;
    }

    @Override
    public ArrayList<Note> getNotes() {
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("1", "title1", "description1"));
        notes.add(new Note("2", "title2", "description2"));
        notes.add(new Note("3", "title3", "description3"));
        return notes;
    }
}
