package com.cloudlibrary.auth.application.service;

import com.cloudlibrary.auth.application.domain.Auth;
import lombok.*;

import java.util.Date;
import java.util.List;

public interface AuthReadUseCase {
    List<FindAuthResult> getAuthListAll();
    FindAuthResult getAuth(AuthFindQuery query);

    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    @ToString
    class AuthFindQuery {
        private long uid;

        public AuthFindQuery(long uid) {
            this.uid = uid;
        }
    }

    @Getter
    @ToString
    @Builder
    class FindAuthResult {
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

        public static FindAuthResult findByAuth(Auth auth) {
            return FindAuthResult.builder()
                    .uid(auth.getUid())
                    .userId(auth.getUserId())
                    .password(auth.getPassword())
                    .userName(auth.getUserName())
                    .gender(auth.getGender())
                    .birth(auth.getBirth())
                    .address(auth.getAddress())
                    .email(auth.getEmail())
                    .tell(auth.getTell())
                    .sendAgree(auth.isSendAgree())
                    .build();
        }
    }
}
