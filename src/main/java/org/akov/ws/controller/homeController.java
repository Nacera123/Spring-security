package org.akov.ws.controller;

import org.akov.ws.dto.AuthRequestDto;
import org.akov.ws.manager.AleatoireManager;
import org.akov.ws.manager.JwtsTokenGenerate;
import org.akov.ws.manager.TokenManager;
import org.akov.ws.model.User;
import org.akov.ws.service.RoleService;
import org.akov.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
public class homeController {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    /**
     * Controller pour le generateToken a partir de l'email et le mdp
     * @param authRequestDto email, mdp
     * @return json qui va contenir le token
     */
    @PostMapping("/generateToken")
    public String home(@RequestBody AuthRequestDto authRequestDto) {

        // associer le token à l'utilisateur
        // 1- vérifier le login
        UserDetails userDetails = userService.loadUserByUsername(authRequestDto.getEmail()); // il va me retourner l'utilisateur
        if (userDetails == null) { // l'utilisateur n'existe pas
            return "user not found";
        }

        // 2- Vérifier le mdp
        if (!passwordEncoder.matches(authRequestDto.getMdp(), userDetails.getPassword())){
            return "no";
        }

        // 3- générer le token
        /*String token;
        do {
            token = AleatoireManager.generateAZC(64);
        }while (userService.isTokenExiste(token)); // le token n'existe pas*/
        // il va sortir de la boucle lorsque le token n'existe pas

        // 4- Associer le token à l'utilisateur
        User user = (User) userDetails;
        //user.setToken(TokenManager.generateToken(userService)); // supprimer l'ancien pour générer un nouvel
        //userService.saveToken(user.getId(), user.getToken()); // modifier le token dans la base de donnée

        // 5- renvoyer le token
        return JwtsTokenGenerate.generateToken(user.getToken());
    }

    @PostMapping("/register")
    public String register(@RequestBody AuthRequestDto authRequestDto){
        // vérifier email existe deja
        try{
            userService.loadUserByUsername(authRequestDto.getEmail());
            return "user already exist";
        }catch (Exception e){

        }


        // l'ensemble des vérif => nom > 3 caract le mdp sup à 8 caract avec du majuscule ou minuscule

        User newUser = new User();
        //newUser.setDateCreation(new Date()); je n'est pas besoin d'ajouter cette ligne
        newUser.setEmail(authRequestDto.getEmail());
        newUser.setMdp(passwordEncoder.encode( authRequestDto.getMdp()));
        newUser.setActive(true);
        newUser.setRoles(List.of(this.roleService.addRole("USER")));

        // token sera comme id
        newUser.setToken(TokenManager.generateToken(userService));
        userService.save(newUser);
        return JwtsTokenGenerate.generateToken(newUser.getToken());
    }

}
