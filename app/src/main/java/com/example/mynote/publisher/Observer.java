package com.example.mynote.publisher;

import com.example.mynote.domain.Note;

public interface Observer {
    final String delete = "delete";
    final String add = "add";
    final String change = "change";

    void receiveMessage(String message, Note note); // получить сообщение
}
