package com.clean.library.user.member.infrastructure.swt;

import com.clean.library.user.infrastructure.controller.BookController;
import com.clean.library.user.infrastructure.swt.UserWindow;
import com.clean.library.user.member.usa_case.BorrowBooks;
import com.clean.library.user.use_case.SeeBooks;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public class MemberWindow extends UserWindow implements SeeBooks, BorrowBooks {

    private static final String SUCCESS_DIALOG_TITLE = "Success";
    private static final String SUCCESS_DIALOG_TEXT = "Book reserved!";
    private static final String FAIL_DIALOG_TITLE = "Something wrong";
    private static final String FAIL_DIALOG_TEXT = "Error !";
    private static final String CHOOSE_BOOK_LABEL = "Enter the number of the book you want to borrow : ";

    private Text userTxt;
    private int bookId;

    public MemberWindow() {
        super();
        chooseBookToBorrow();
        addListeners();
    }

    private void addListeners() {
        userTxt.addModifyListener(modifyEvent -> {
            if (!userTxt.getText().isBlank()) {
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

    @Override
    public int chooseBookToBorrow() {
        createBorrowComposite();
        return bookId;
    }

    private void createBorrowComposite() {
        Composite mainComposite = displayWindow.getMainComposite();
        Label lbl = new Label(mainComposite, SWT.NONE);
        lbl.setText(CHOOSE_BOOK_LABEL);
        userTxt = new Text(mainComposite, SWT.BORDER);
    }

    protected void okPressed() {
        try{
            bookId = Integer.parseInt(userTxt.getText());
        } catch (NumberFormatException e){
            bookId = 0;
        }
        BookController bookController = new BookController();
        if(bookController.borrow(bookId)){
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


}
