package com.ecom.userService.repository;

import com.ecom.userService.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
//    Optional<UserEntity> findByUserId(String userId);
//
//    Optional<UserEntity> findByUserIdOrEmailId(String userId, String emailId);

    Optional<UserEntity> findByEmailId(String emailId);
}
