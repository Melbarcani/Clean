package com.clean.library.user.librarian.infrastructure.swt;


import com.clean.library.user.domain.Book;
import com.clean.library.user.infrastructure.controller.BookController;
import com.clean.library.user.infrastructure.swt.UserWindow;
import com.clean.library.user.librarian.use_case.AddBookReference;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public class LibrarianWindow extends UserWindow implements AddBookReference {
    private static final String SUCCESS_DIALOG_TITLE = "Success";
    private static final String SUCCESS_DIALOG_TEXT = "Book added";
    private static final String FAIL_DIALOG_TITLE = "Something wrong";
    private static final String FAIL_DIALOG_TEXT = "Error !";
    private static final String ENTER_BOOK_NAME_LABEL = "Enter book name : ";
    private static final String ENTER_BOOK_AUTHOR_LABEL = "Enter book author : ";
    private static final String ENTER_BOOK_ID_LABEL = "Enter the serial number : ";

    private Text bookTitleTxt;
    private Text bookAuthorTxt;
    private Text bookIdTxt;

    public LibrarianWindow() {
        super();
        createAddComposite();
        addListeners();
    }

    private void addListeners() {
        bookIdTxt.addModifyListener(modifyEvent -> {
            if (!bookAuthorTxt.getText().isBlank() && !bookTitleTxt.getText().isBlank() ) {
                displayWindow.getOkBttn().setEnabled(true);
            }
        });
        displayWindow.getOkBttn().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseUp(MouseEvent mouseEvent) {
                okPressed();
            }
        });
    }

    private void createAddComposite() {
        Composite bottomComposite = displayWindow.getButtonComposite();
        displayWindow.getResetBtn().dispose();
        createLayout(bottomComposite);
        createTextsArea(bottomComposite);
    }

    private void createTextsArea(Composite bottomComposite) {
        Label bookNameLabel = new Label(bottomComposite, SWT.NONE);
        bookNameLabel.setText(ENTER_BOOK_NAME_LABEL);
        bookTitleTxt = new Text(bottomComposite, SWT.BORDER);
        Label lbl = new Label(bottomComposite, SWT.NONE);
        lbl.setText(ENTER_BOOK_AUTHOR_LABEL);
        bookAuthorTxt = new Text(bottomComposite, SWT.BORDER);
        Label bookIdLabel = new Label(bottomComposite, SWT.NONE);
        bookIdLabel.setText(ENTER_BOOK_ID_LABEL);
        bookIdTxt = new Text(bottomComposite, SWT.BORDER);
    }

    private void createLayout(Composite bottomComposite) {
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.horizontalSpacing = 20;
        bottomComposite.setLayout(gridLayout);
    }

    protected void okPressed() {
        int bookId = enterBookSerialNo();
        String bookTitle = enterBookTitle();
        String bookAuthor = enterBookAuthor();
        Book bookToAdd = new Book(bookId, bookTitle, bookAuthor);
        BookController bookController = new BookController();
        if(bookController.add(bookToAdd)){
            openResultMessageDialog(SUCCESS_DIALOG_TITLE, SUCCESS_DIALOG_TEXT);
        } else {
            openResultMessageDialog(FAIL_DIALOG_TITLE, FAIL_DIALOG_TEXT);
        }
    }

    private void openResultMessageDialog(String title, String message) {
        MessageBox dialog =
                new MessageBox(displayWindow.getShell(), SWT.OK);
        dialog.setText(title);
        dialog.setMessage(message);
        dialog.open();
    }

    @Override
    public String enterBookTitle() {
        return bookTitleTxt.getText();
    }

    @Override
    public String enterBookAuthor() {
        return bookAuthorTxt.getText();
    }

    @Override
    public int enterBookSerialNo() {
        try{
            return Integer.parseInt(bookIdTxt.getText());
        } catch (NumberFormatException e){
            return 0;
        }
    }
}
