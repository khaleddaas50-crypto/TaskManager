package com.khaled.taskmanager.model;
/**
 * Notification sinifi kullaniciya bildirim gondermek icin kullanilir.
 */

public class Notification {
    private String message;

    public Notification(String message) {
        this.message = message;
    }

    public void send() {
        System.out.println("NOTIFICATION: " + message);
    }
}
