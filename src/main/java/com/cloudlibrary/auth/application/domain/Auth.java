package com.cloudlibrary.auth.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@Builder
public class Auth {
    private final Long uid;
    private final long userId;
    private final String password;
    private final String userName;
    private final String gender;
    private final Date birth;
    private final String address;
    private final String email;
    private final String tell;
    private final boolean sendAgree;
}
