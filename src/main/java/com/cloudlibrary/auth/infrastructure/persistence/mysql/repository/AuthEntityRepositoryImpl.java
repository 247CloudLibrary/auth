package com.cloudlibrary.auth.infrastructure.persistence.mysql.repository;

import com.cloudlibrary.auth.application.domain.Auth;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class AuthEntityRepositoryImpl implements AuthEntityRepository {


    @Override
    public Optional<Auth> findAuthById(long authId) {
        return Optional.empty();
    }

    @Override
    public List<Auth> findAuthAll() {
        return Collections.emptyList();
    }
}
