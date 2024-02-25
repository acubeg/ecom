package com.ecom.userService.repository;

import com.ecom.userService.entity.TokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

//    @Query
//        (value = "SELECT t From TokenEntity t WHERE t.userId=?1 " +
//        "AND t.expired = false AND t.revoked = false")
//    List<TokenEntity> findAllValidTokenByUserId(String userId);

    Optional<TokenEntity> findByToken(String token);
    List<TokenEntity> findByUserEmailId(String userId);
}
