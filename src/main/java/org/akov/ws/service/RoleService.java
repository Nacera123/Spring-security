package org.akov.ws.service;


import lombok.Data;
import org.akov.ws.model.Role;
import org.akov.ws.repository.RoleRepository;
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
