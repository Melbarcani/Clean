package com.clean.library.user.domain;

import java.util.List;

public interface BookDao {
    boolean isBooksDataExist();
    Book loadBook();
    List<Book> loadAllBooks();

    boolean setBookUnavailable(int bookId);

    boolean addBook(String id,String title,String author);
}
