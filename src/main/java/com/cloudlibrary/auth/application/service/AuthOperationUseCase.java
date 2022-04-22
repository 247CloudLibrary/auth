package com.cloudlibrary.auth.application.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

public interface AuthOperationUseCase {

    void createAuth(AuthCreateCommand command);

    void updateAuth(AuthUpdateCommand command);

    void deleteAuth(AuthDeleteCommand command);


    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class AuthCreateCommand {
        private final String userId;
        private final String password;
        private final String userName;
        private final String gender;
        private final String birth;
        private final String address;
        private final String email;
        private final String tel;
    }


    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class AuthUpdateCommand {
        private final Long uid;
        private final String userId;
        private final String password;
        private final String userName;
        private final String gender;
        private final String birth;
        private final String address;
        private final String email;
        private final String tel;
    }

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class AuthDeleteCommand {
        private final Long uid;
    }
}
