package com.example.demo.model;

public class Todo {
    private int id;
    private String title;
    private String status;
    private String description;

    public Todo(int id, String title, String description, String status) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.description = description;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }

    public void setId(int id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
