package com.clean.library.user.use_case;

import com.clean.library.user.domain.Book;

import java.util.List;

public interface SeeBooks {
    List<Book> loadBooks();
    void displayBooks();
}
