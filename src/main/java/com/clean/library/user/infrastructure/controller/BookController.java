package com.clean.library.user.infrastructure.controller;

import com.clean.library.user.domain.Book;
import com.clean.library.user.domain.BookDao;
import com.clean.library.user.infrastructure.dao.InMemoryBookDao;

import java.util.List;

public class BookController {
    private BookDao bookDao;

    public BookController() {
        bookDao = new InMemoryBookDao();
    }

    public boolean isBooksDataExist(){
        return bookDao.isBooksDataExist();
    }

    public List<Book> getAllAvailableBooks(){
        return getAllBooks();
    }

    private List<Book> getAllBooks(){
        return bookDao.loadAllBooks();
    }

    public boolean borrow(int bookId) {
        return bookDao.setBookUnavailable(bookId);
    }

    public boolean add(Book bookToAdd) {
        String id = String.valueOf(bookToAdd.getId());
        String title = bookToAdd.getTitle();
        String author = bookToAdd.getAuthorName();
        return bookDao.addBook(id, title, author);
    }
}
