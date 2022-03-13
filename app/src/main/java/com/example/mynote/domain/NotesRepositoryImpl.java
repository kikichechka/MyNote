package com.example.mynote.domain;

import java.util.ArrayList;
import java.util.Collection;

public class NotesRepositoryImpl implements NotesRepository {
    ArrayList<Note> notesList = new ArrayList<>();

    @Override
    public ArrayList<Note> getNotes() {


        /*notesList.add(new Note(0, "Заметка 1", "Описание первой заметки"));
        notesList.add(new Note(1, "Заметка 2", "Описание второй заметки"));
        notesList.add(new Note(2, "Заметка 3", "Описание третьей заметки"));
        notesList.add(new Note(3, "Заметка 4", "Описание четвертой заметки"));
        notesList.add(new Note(4, "Заметка 5", "Описание пятой заметки"));
        notesList.add(new Note(5, "Заметка 6", "Описание шестой заметки"));
        notesList.add(new Note(6, "Заметка 7", "Описание седьмой заметки"));
        notesList.add(new Note(7, "Заметка 8", "Описание восьмой заметки"));
        notesList.add(new Note(8, "Заметка 9", "Описание девятой заметки"));*/


        return notesList;
    }

    public void addNote(Note note) {
        notesList.add(note);
    }
}
