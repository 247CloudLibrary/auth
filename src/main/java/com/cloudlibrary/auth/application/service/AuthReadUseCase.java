package com.cloudlibrary.auth.application.service;

import com.cloudlibrary.auth.application.domain.Auth;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

public interface AuthReadUseCase {

    FindAuthResult getAuthInfo(AuthFindQuery query);

    @NoArgsConstructor
    @EqualsAndHashCode(callSuper = false)
    @Getter
    @ToString
    class AuthFindQuery {
        private Long uid;

        public AuthFindQuery(Long uid) {
            this.uid = uid;
        }
    }

    @Getter
    @ToString
    @Builder
    class FindAuthResult {
        private final Long uid;
        private final String userId;
        private final String password;
        private final String userName;
        private final String gender;
        private final String birth;
        private final String address;
        private final String email;
        private final String tel;

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
                    .tel(auth.getTel())
                    .build();
        }
    }
}
