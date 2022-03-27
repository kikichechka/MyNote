package com.example.mynote.domain;

import java.util.ArrayList;

public interface NotesRepository {
    ArrayList<Note> listNotes = new ArrayList<>();

    ArrayList<Note> getNotes();
}
