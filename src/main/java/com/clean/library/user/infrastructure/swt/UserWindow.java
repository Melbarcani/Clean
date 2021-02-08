package com.clean.library.user.infrastructure.swt;

import com.clean.library.common.infrastructure.swt.DisplayWindow;
import com.clean.library.user.domain.Book;
import com.clean.library.user.infrastructure.controller.BookController;
import com.clean.library.user.use_case.SeeBooks;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import java.util.Collections;
import java.util.List;

public class UserWindow implements SeeBooks {

    protected DisplayWindow displayWindow;

    public UserWindow() {
        displayWindow = new DisplayWindow();
        displayBooks();
    }

    @Override
    public List<Book> loadBooks() {
        BookController bookController = new BookController();
        if(bookController.isBooksDataExist()){
            return bookController.getAllAvailableBooks();
        }
        return Collections.emptyList();
    }

    @Override
    public void displayBooks() {
        Composite mainComposite = displayWindow.getMainComposite();
        createBooksComposite(mainComposite);
        mainComposite.layout();
    }

    private void createBooksComposite(Composite parent) {
        createLayout(parent);
        populateBooksComposite(parent);
    }

    private void populateBooksComposite(Composite parent) {
        List<Book> books = loadBooks();
        for(Book book : books){
            Label idLabel = new Label(parent, SWT.NONE);
            idLabel.setText(String.valueOf(book.getId()));
            Label titleLabel = new Label(parent, SWT.NONE);
            titleLabel.setText(book.getTitle());
            Label authorLabel = new Label(parent, SWT.NONE);
            authorLabel.setText(book.getAuthorName());
        }
    }

    private void createLayout(Composite parent) {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 3;
        gridLayout.horizontalSpacing = 20;
        parent.setLayout(gridLayout);
    }

    public void display(){
        displayWindow.open();
    }

}
