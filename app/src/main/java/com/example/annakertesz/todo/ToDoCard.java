package com.example.annakertesz.todo;

/**
 * Created by annakertesz on 5/22/17.
 */

public class ToDoCard {

    //private variables
    int id;
    String title;
    boolean isDone;

    public ToDoCard() {
    }

    public ToDoCard(String title){
        this.title= title;
        this.isDone = false;
    }

    public ToDoCard(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public ToDoCard(int id, String title, boolean isDone) {
        this.id = id;
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsDone(boolean done) {
        isDone = done;
    }
}
