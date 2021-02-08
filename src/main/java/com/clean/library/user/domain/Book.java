package com.clean.library.user.domain;

public class Book {
    private final int id;
    private final String title;
    private final String authorName;

    public Book(int id, String title, String authorName) {
        this.id =id;
        this.title = title;
        this.authorName = authorName;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorName() {
        return authorName;
    }
}
