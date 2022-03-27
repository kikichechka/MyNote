package com.example.mynote.publisher;

import com.example.mynote.domain.Note;

import java.util.ArrayList;

public class Publisher {
    private ArrayList<Observer> observers;

    public Publisher() {
        observers = new ArrayList<>();
    }


    public void subscribe(Observer observer) {
        observers.add(observer);
    } // подписать

    public void unSubscribe(Observer observer) {
        observers.remove(observer);
    } // отписать

    public void sendMessage(String message, Note note) { // отправить сообщение
        for (Observer o: observers) {
            o.receiveMessage(message, note);
        }
    }
}
