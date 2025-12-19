
package com.khaled.taskmanager.model;

public class Notification {
    private String message;

    public Notification(String message) {
        this.message = message;
    }

    public void send() {
        System.out.println("NOTIFICATION: " + message);
    }
}
