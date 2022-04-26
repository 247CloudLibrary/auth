package com.cloudlibrary.auth.application.service;

import com.cloudlibrary.auth.application.domain.Auth;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface AuthReadUseCase extends UserDetailsService {

    FindAuthResult getAuthInfo(AuthFindQuery query);
    FindAuthResult getAuthById(String userId);

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
