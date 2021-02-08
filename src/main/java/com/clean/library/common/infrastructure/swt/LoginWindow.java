package com.clean.library.common.infrastructure.swt;

import com.clean.library.Login;
import com.clean.library.user.domain.User;
import com.clean.library.user.infrastructure.controller.UserController;
import com.clean.library.user.infrastructure.swt.UserWindow;
import com.clean.library.user.infrastructure.swt.UserWindowFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;

public class LoginWindow implements Login {
    private static final String ENTER_YOUR_USER_ID = "Enter your login";
    private static final String FAIL_DIALOG_TITLE = "Something wrong";
    private static final String FAIL_DIALOG_TEXT = "This user doesn't exit or connection failed";
    private Text userTxt;
    private String userLogin;

    private DisplayWindow displayWindow;

    public LoginWindow() {
        displayWindow = new DisplayWindow();
        createMainComposite(displayWindow.getShell());
        addListeners();
        displayWindow.open();
    }

    public void createMainComposite(Composite parent) {
        Composite mainComposite = displayWindow.getMainComposite();
        Label lbl = new Label(mainComposite, SWT.NONE);
        lbl.setText(ENTER_YOUR_USER_ID);
        userTxt = new Text(mainComposite, SWT.BORDER);
    }

    protected void addListeners() {
        displayWindow.addListeners();
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

    protected void okPressed() {
        userLogin = userTxt.getText();
        UserController userController = new UserController(userLogin);
            displayWindow.dispose();
            User user = userController.getUser();
            UserWindowFactory windowFactory = new UserWindowFactory();
            UserWindow userWindow = windowFactory.createUserWindow(user);
            userWindow.display();
    }

    private void openFailMessageDialog() {
        MessageBox dialog =
                new MessageBox(displayWindow.getShell(), SWT.OK);
        dialog.setText(FAIL_DIALOG_TITLE);
        dialog.setMessage(FAIL_DIALOG_TEXT);
        dialog.open();
    }
}
