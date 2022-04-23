package com.cloudlibrary.auth.application.service;

import com.cloudlibrary.auth.application.domain.Auth;
import com.cloudlibrary.auth.exception.CloudLibraryException;
import com.cloudlibrary.auth.exception.MessageType;
import com.cloudlibrary.auth.infrastructure.persistence.mysql.entity.AuthEntity;
import com.cloudlibrary.auth.infrastructure.persistence.mysql.repository.AuthEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthService implements AuthOperationUseCase,AuthReadUseCase{

    private final AuthEntityRepository authEntityRepository;

    public AuthService(AuthEntityRepository authEntityRepository) {
        this.authEntityRepository = authEntityRepository;
    }

    @Override
    @Transactional
    public void createAuth(AuthCreateCommand command) {
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
    @Transactional
    public void updateAuth(AuthUpdateCommand command) {

        AuthEntity authEntity = authEntityRepository.findById(command.getUid()).stream().findAny()
                .orElseThrow(() -> new CloudLibraryException(MessageType.NOT_FOUND));

        Auth auth = Auth.builder()
                .uid(command.getUid())
                .userId(command.getUserId())
                .password(command.getPassword())
                .userName(command.getUserName())
                .gender(command.getGender())
                .birth(command.getBirth())
                .address(command.getAddress())
                .email(command.getEmail())
                .tel(command.getTel())
                .build();

        authEntity.update(auth);

    }

    @Override
    @Transactional
    public void deleteAuth(AuthDeleteCommand command) {
        authEntityRepository.findById(command.getUid()).stream().findAny()
                .orElseThrow(() -> new CloudLibraryException(MessageType.NOT_FOUND));

        authEntityRepository.deleteById(command.getUid());
    }

    @Override
    @Transactional
    public FindAuthResult findAuthId(AuthFindIdCommand command) {


        Auth auth = authEntityRepository.findByEmail(command.getEmail()).stream().findAny().map(AuthEntity::toAuth)
                .orElseThrow(() -> new CloudLibraryException(MessageType.NOT_FOUND));
        System.out.println(auth);
        return FindAuthResult.findByAuth(auth);
    }


    @Override
    @Transactional(readOnly = true)
    public FindAuthResult getAuthInfo(AuthFindQuery query) {

        Optional<Auth> result = authEntityRepository.findById(query.getUid()).stream().findAny()
                .map(AuthEntity::toAuth);

        if (result.isEmpty()) {
            throw new CloudLibraryException(MessageType.NOT_FOUND);
        }

        return FindAuthResult.findByAuth(result.get());
    }
}
