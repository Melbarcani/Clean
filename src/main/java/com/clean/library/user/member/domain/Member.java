package com.clean.library.user.member.domain;

import com.clean.library.user.domain.User;

public class Member extends User {
    public Member(String userLogin, String userRole) {
        super(userLogin, userRole);
    }
}
