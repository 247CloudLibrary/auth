package com.cloudlibrary.auth.application.service;

import com.cloudlibrary.auth.application.domain.Auth;
import com.cloudlibrary.auth.infrastructure.persistence.mysql.entity.AuthEntity;
import com.cloudlibrary.auth.infrastructure.persistence.mysql.repository.AuthEntityRepository;
import org.aspectj.lang.annotation.After;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest
class AuthServiceTest {

    @Autowired
    AuthEntityRepository authRepository;


    @Test
    public void 회원가입() throws Exception{
        //given
        Auth auth = Auth.builder()
                .userId("hansu")
                .password("Hhansu0700!!")
                .userName("김한수")
                .gender("여")
                .birth("1997-05-12")
                .address("제주시 레포츠공원")
                .email("hansu@mail.com")
                .tel("010-1111-2222")
                .build();

        //when
        AuthEntity saveAuth = authRepository.save(new AuthEntity(auth));

        //then
        Optional<AuthEntity> getAuth = authRepository.findById(saveAuth.getUid());
        Assertions.assertThat(getAuth.get().getUserId()).isEqualTo(auth.getUserId());
    }


}