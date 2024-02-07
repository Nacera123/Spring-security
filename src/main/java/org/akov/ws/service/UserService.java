package com.abdel.stock.service;


import com.abdel.stock.model.Photo;
import com.abdel.stock.model.User;
import com.abdel.stock.repository.PhotoRepository;
import com.abdel.stock.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User add(User user) {
        return userRepository.save(user);
    }


    /**
     * Récupérer l'utilisateur à partir de son email
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetails userDetails = userRepository.findByEmailIgnoreCase(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return userDetails;
    }
}
