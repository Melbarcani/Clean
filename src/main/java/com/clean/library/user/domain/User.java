package com.clean.library.user.domain;

public class User {
    private String GUEST ="guest";

    private String userLogin;
    private String userRole;

    public User(){
        userRole = GUEST;
    }

    public User(String userLogin, String role) {
        this.userLogin = userLogin;
        this.userRole = role;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public String getUserRole(){
        return userRole;
    }
}
