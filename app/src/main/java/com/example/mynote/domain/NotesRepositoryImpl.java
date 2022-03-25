package com.example.mynote.domain;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

public class NotesRepositoryImpl implements NotesRepository {
    private ArrayList<Note> notesList = new ArrayList<>();


    @Override
    public ArrayList<Note> getNotes() {

        /*notesList.add(new Note(0, "Заметка 1", "Описание первой заметки", false));
        notesList.add(new Note(1, "Заметка 2", "Описание второй заметки", false));
        notesList.add(new Note(2, "Заметка 3", "Описание третьей заметки", false));
        notesList.add(new Note(3, "Заметка 4", "Описание четвертой заметки", false));
        notesList.add(new Note(4, "Заметка 5", "Описание пятой заметки", true));
        notesList.add(new Note(5, "Заметка 6", "Описание шестой заметки", false));
        notesList.add(new Note(6, "Заметка 7", "Описание седьмой заметки", false));
        notesList.add(new Note(7, "Заметка 8", "Описание восьмой заметки", false));
        notesList.add(new Note(8, "Заметка 9", "Описание девятой заметки", false));
        notesList.add(new Note(9, "Заметка 9", "Описание девятой заметки", false));
        notesList.add(new Note(10, "Заметка 9", "Описание девятой заметки", false));
         */

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
