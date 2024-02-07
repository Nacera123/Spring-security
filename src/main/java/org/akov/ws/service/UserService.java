package org.akov.ws.service;


import lombok.Data;
import org.akov.ws.model.User;
import org.akov.ws.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User findByToken(String token){
        List<User> users = userRepository.findByToken(token);
        if (users.size() != 1){
            return null;
        }
        return users.get(0);
    }


    public void saveToken(Integer id, String token){
        userRepository.updateTokenById(token, id);
    }

    public boolean isTokenExiste(String token){
        return userRepository.countByTokenLike(token) > 0;
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
