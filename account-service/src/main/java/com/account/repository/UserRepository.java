package com.account.repository;


import com.account.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Boolean existsByEmailAndIsDeletedIsFalse(String email);

    Optional<User> findByPasswordResetToken(String token);
}
