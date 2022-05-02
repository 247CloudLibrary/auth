package com.cloudlibrary.auth.infrastructure.persistence.mysql.repository;


import com.cloudlibrary.auth.infrastructure.persistence.mysql.entity.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AuthEntityRepository extends JpaRepository<AuthEntity, Long> {

   Optional<AuthEntity> findByEmail(String email);

   Optional<AuthEntity> findByUserIdAndEmail(String userId, String email);

   List<AuthEntity> findByUserIdOrEmail(String userId, String email);

   Optional<AuthEntity> findByUserId(String userId);

   Optional<AuthEntity> findByUid(Long uid);
}
