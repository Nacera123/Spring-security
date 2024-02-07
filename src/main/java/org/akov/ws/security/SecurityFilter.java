package org.akov.ws.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.akov.ws.manager.JwtsTokenGenerate;
import org.akov.ws.model.User;
import org.akov.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * La classe qui sera executer avant la requête
 */
@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // request.getRequestURI() => URL
        // on va executer que les request qui commencent par /api
        if (!request.getRequestURI().startsWith("/api/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // on va executer que les request qui commencent par /api/
        // on doit vérifier que le token existe
        String token = request.getHeader("Authorization");
        try {
            token = JwtsTokenGenerate.readToken(token.substring(7));
        } catch (Exception e) {
            //throw new RuntimeException(e);
            return;
        }

        // récupérer l'utilisateur qui contient ce token
        User userConnect = this.userService.findByToken(token);
        if (userConnect == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // token et valide => enregistrer l'utilisateur dans le systeme avec ces roles
        Authentication authentication = new UsernamePasswordAuthenticationToken(userConnect, null, userConnect.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }
}
