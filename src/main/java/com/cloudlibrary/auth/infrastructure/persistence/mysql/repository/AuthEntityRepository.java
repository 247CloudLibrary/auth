package com.cloudlibrary.auth.infrastructure.persistence.mysql.repository;

import com.cloudlibrary.auth.application.domain.Auth;

import java.util.List;
import java.util.Optional;

public interface AuthEntityRepository {
    Optional<Auth> findAuthById(Long authId);
    List<Auth> findAuthAll();
}
