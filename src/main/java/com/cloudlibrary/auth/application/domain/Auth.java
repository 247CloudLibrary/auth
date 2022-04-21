package com.cloudlibrary.auth.application.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@Builder
public class Auth {
    private final Long uid;
    private final String userId;
    private final String password;
    private final String userName;
    private final String gender;
    private final String birth;
    private final String address;
    private final String email;
    private final String tel;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
