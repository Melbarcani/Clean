package com.clean.library.user.librarian.domain;


import com.clean.library.user.domain.User;

public class Librarian extends User {
    public Librarian(String userLogin, String role) {
        super(userLogin, role);
    }
}
