package com.example.mynote.publisher;

import com.example.mynote.domain.Note;

public interface Observer {
    void receiveMessage(Note note); // получить сообщение
}
