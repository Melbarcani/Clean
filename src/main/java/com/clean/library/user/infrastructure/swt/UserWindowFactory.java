package com.clean.library.user.infrastructure.swt;

import com.clean.library.user.domain.User;
import com.clean.library.user.librarian.domain.Librarian;
import com.clean.library.user.librarian.infrastructure.swt.LibrarianWindow;
import com.clean.library.user.member.domain.Member;
import com.clean.library.user.member.infrastructure.swt.MemberWindow;

public class UserWindowFactory {
    public UserWindow createUserWindow(User user){
        if(user instanceof Librarian){
            return new LibrarianWindow();
        }
        if(user instanceof Member){
            return new MemberWindow();
        }
        return new UserWindow();
    }
}
