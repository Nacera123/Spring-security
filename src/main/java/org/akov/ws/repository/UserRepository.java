package com.abdel.stock.repository;


import com.abdel.stock.model.Photo;
import com.abdel.stock.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmailIgnoreCase(String email);
}
