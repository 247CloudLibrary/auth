package com.cloudlibrary.auth.application.service;

import com.cloudlibrary.auth.application.domain.Auth;
import com.cloudlibrary.auth.infrastructure.persistence.mysql.entity.AuthEntity;
import com.cloudlibrary.auth.infrastructure.persistence.mysql.repository.AuthEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements AuthOperationUseCase{

    private final AuthEntityRepository authEntityRepository;

    public AuthService(AuthEntityRepository authEntityRepository) {
        this.authEntityRepository = authEntityRepository;
    }

    @Override
    @Transactional
    public void createdAuth(AuthCreateCommand command) {
        Auth auth = Auth.builder()
                .userId(command.getUserId())
                .password(command.getPassword())
                .userName(command.getUserName())
                .gender(command.getGender())
                .birth(command.getBirth())
                .address(command.getAddress())
                .email(command.getEmail())
                .tel(command.getTel())
                .build();

        authEntityRepository.save(new AuthEntity(auth));
    }

    @Override
    public Long updateAuth(AuthUpdateCommand command) {
        return null;
    }

    @Override
    public void deleteAuth(AuthDeleteCommand command) {

    }
}
