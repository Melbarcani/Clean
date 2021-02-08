package com.clean.library.user.infrastructure.dao;

import com.clean.library.user.domain.Book;
import com.clean.library.user.domain.BookDao;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class InMemoryBookDao implements BookDao {
    private final static String BOOKS_FILE_NAME = "books.txt";
    private final static String ENCODING = "utf-8";
    private final static String SEPARATOR = ",";

    public InMemoryBookDao() {
    }

    @Override
    public boolean isBooksDataExist() {
        Path path = Paths.get(BOOKS_FILE_NAME);
        return Files.exists(path);
    }

    @Override
    public Book loadBook() {
        return null;
    }

    @Override
    public List<Book> loadAllBooks() {
        List<Book> books = new ArrayList<>();
        try {
            List<String> fileLines = FileUtils.readLines(new File(BOOKS_FILE_NAME));
            for (String line : fileLines) {
                String[] data = line.split(SEPARATOR);
                int bookId = Integer.parseInt(data[0]);
                String bookTitle = data[1];
                String bookAuthor = data[2];
                books.add(new Book(bookId, bookTitle, bookAuthor));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean setBookUnavailable(int bookId) {
        return false;
    }

    @Override
    public boolean addBook(String id, String title, String author) {
        return false;
    }
}
