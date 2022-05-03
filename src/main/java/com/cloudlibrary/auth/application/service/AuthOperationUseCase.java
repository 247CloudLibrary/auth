package com.cloudlibrary.auth.application.service;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.mail.MessagingException;
import java.util.Random;

public interface AuthOperationUseCase {

    void createAuth(AuthCreateCommand command);

    void updateAuth(AuthUpdateCommand command);

    void updatePassword(AuthUpdatePasswordCommand command);

    void deleteAuth(AuthDeleteCommand command);

    AuthReadUseCase.FindAuthResult findAuthId(AuthFindIdCommand command);

    String findAuthPW(AuthFindPWCommand command);



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
    class AuthUpdatePasswordCommand {
        private final Long uid;
        private final String oldPassword;
        private final String newPassword;
    }


    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class AuthDeleteCommand {
        private final Long uid;
    }

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class AuthFindIdCommand {
        private final String email;

    }

    @EqualsAndHashCode(callSuper = false)
    @Builder
    @Getter
    @ToString
    class AuthFindPWCommand {
        private final String userId;
        private final String email;

    }

    /**
     * 랜덤 비밀번호 생성
     * @param passwordLength
     * @return
     */
    static String tempPassword(int passwordLength) {

        int index = 0;

        char[] charSet = new char[]{
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
                '~','!','@','#','$','%','^','&','*','/','-','+','|'
        };

        StringBuffer password = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < passwordLength; i++) {
            index = (int) (charSet.length * random.nextDouble());
            password.append(charSet[index]);

        }
        return password.toString();
    }


}
