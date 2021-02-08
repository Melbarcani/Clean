package com.clean.library.user.domain;

public interface UserDao {
    boolean isUserExist();
    User loadUser();
}
