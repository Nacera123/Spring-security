package org.akov.ws.repository;


import org.akov.ws.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findByNomLikeIgnoreCase(String nom);
}
