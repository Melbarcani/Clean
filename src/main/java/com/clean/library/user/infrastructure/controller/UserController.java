package com.clean.library.user.infrastructure.controller;

import com.clean.library.user.domain.User;
import com.clean.library.user.domain.UserDao;
import com.clean.library.user.infrastructure.dao.InMemoryUserDao;
import com.clean.library.user.infrastructure.swt.UserWindowFactory;
import com.clean.library.user.librarian.domain.Librarian;
import com.clean.library.user.member.domain.Member;

public class UserController {
    private final static String LIBRARIAN_ROLE = "librarian";
    private final static String MEMBER_ROLE = "member";
    private String userLogin;
    private UserDao userDao;


    public UserController(String userLogin) {
        this.userLogin = userLogin;
        userDao = new InMemoryUserDao(userLogin);
    }

    public boolean isUserExist() {
        return userDao.isUserExist();
    }

    public User getUser(){
        User user = userDao.loadUser();
        if(LIBRARIAN_ROLE.equals(user.getUserRole())){
            return new Librarian(user.getUserLogin(), user.getUserRole());
        }
        if(MEMBER_ROLE.equals(user.getUserRole())){
            return new Member(user.getUserLogin(), user.getUserRole());
        }
        return user;
    }
}
