package org.akov.ws.repository;


import org.akov.ws.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmailIgnoreCase(String email);

    @Transactional
    @Modifying
    @Query("update User u set u.token = ?1 where u.id = ?2")
    int updateTokenById(String token, Integer id);

    long countByTokenLike(String token);

    List<User> findByToken(String token);
}
