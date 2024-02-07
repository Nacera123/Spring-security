package com.abdel.stock.service;


import com.abdel.stock.model.Photo;
import com.abdel.stock.model.Role;
import com.abdel.stock.repository.PhotoRepository;
import com.abdel.stock.repository.RoleRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    // Ajouter un role
    public Role addRole(String nom) {
        // avant d'ajouter un role on doit vérifier si le role existe deja

        List<Role> role = roleRepository.findByNomLikeIgnoreCase(nom);
        if (role.size() >= 1) {
            return role.get(0);
        }

        // si le role n'existe pas je vais le créer
        Role newRole = new Role();
        newRole.setNom(nom);
        return roleRepository.save(newRole);
    }
}
