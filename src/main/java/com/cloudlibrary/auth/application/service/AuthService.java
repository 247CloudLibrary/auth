package com.cloudlibrary.auth.application.service;

import com.cloudlibrary.auth.application.domain.Auth;
import com.cloudlibrary.auth.exception.CloudLibraryException;
import com.cloudlibrary.auth.exception.MessageType;
import com.cloudlibrary.auth.infrastructure.persistence.mysql.entity.AuthEntity;
import com.cloudlibrary.auth.infrastructure.persistence.mysql.repository.AuthEntityRepository;
import com.cloudlibrary.auth.ui.security.config.SecurityConfig;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

        Optional<AuthEntity> duplicateCheck = authEntityRepository.findByUserIdOrEmail(command.getUserId(), command.getEmail())
                                            .stream().findAny();

        if (duplicateCheck.isPresent()) {
            throw new CloudLibraryException(MessageType.CONFLICT);
        }

        String encoderPassword = SecurityConfig.passwordEncoder().encode(command.getPassword());

        Auth auth = Auth.builder()
                .userId(command.getUserId())
                .password(encoderPassword)
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


        String encoderPassword = SecurityConfig.passwordEncoder().encode(command.getPassword());

        Auth auth = Auth.builder()
                .uid(command.getUid())
                .userId(command.getUserId())
                .password(encoderPassword)
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
    public FindAuthResult findAuthId(AuthFindIdCommand command) {


        Auth auth = authEntityRepository.findByEmail(command.getEmail()).stream().findAny().map(AuthEntity::toAuth)
                .orElseThrow(() -> new CloudLibraryException(MessageType.NOT_FOUND));

        return FindAuthResult.findByAuth(auth);
    }

    @Override
    @Transactional
    public String findAuthPW(AuthFindPWCommand command) {

        AuthEntity authEntity = authEntityRepository.findByUserIdAndEmail(command.getUserId(), command.getEmail())
                .stream().findAny()
                .orElseThrow(() -> new CloudLibraryException(MessageType.NOT_FOUND));

        String randomPassword = AuthOperationUseCase.tempPassword(13);

        authEntity.updatePassword(randomPassword);

       return randomPassword;
    }


    @Override
    public FindAuthResult getAuthInfo(AuthFindQuery query) {

        Optional<Auth> result = authEntityRepository.findById(query.getUid()).stream().findAny()
                .map(AuthEntity::toAuth);

        if (result.isEmpty()) {
            throw new CloudLibraryException(MessageType.NOT_FOUND);
        }

        return FindAuthResult.findByAuth(result.get());
    }

    @Override
    public FindAuthResult getAuthById(String userId) {
        Optional<AuthEntity> result = authEntityRepository.findByUserId(userId);

        if(result.isEmpty()){
            throw new CloudLibraryException(MessageType.NOT_FOUND);
        }

        return FindAuthResult.findByAuth(result.get().toAuth());
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<AuthEntity> result = authEntityRepository.findByUserId(userName);
        if (result == null) {
            throw new UsernameNotFoundException(userName);
        }
        return new User(result.get().getUserId(), result.get().getPassword(),
                true, true, true, true, new ArrayList<>());

    }
}
