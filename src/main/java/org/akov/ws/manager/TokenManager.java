package org.akov.ws.manager;

import org.akov.ws.service.UserService;

public class TokenManager {

    /**
     * Cette méthode elle permet de me générer un token unique
     * @param userService
     * @return token
     */
    public static String generateToken(UserService userService){
        String token;
        do {
            token = AleatoireManager.generateAZC(64);
        }while (userService.isTokenExiste(token));
        return token;
    }

}
