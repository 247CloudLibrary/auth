package com.cloudlibrary.auth.infrastructure.persistence.mysql.repository;

import com.cloudlibrary.auth.application.domain.Auth;
import com.cloudlibrary.auth.infrastructure.persistence.mysql.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthEntityRepository extends JpaRepository<AuthEntity, Long> {

}
