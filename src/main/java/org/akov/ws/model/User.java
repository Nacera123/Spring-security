package org.akov.ws.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "user")
public class User implements UserDetails {

    public User() {
        this.dateCreation = new Date();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String mdp;
    private String token;

    //@Column(name="dateCreation", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;
    private boolean active; // actif ou pas

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;


    public boolean isRole(String roleName){
        //return this.roles.stream().anyMatch(role -> role.getNom().equals(roleName));
        if (this.roles == null) {
            return false;
        }
        for (Role role : roles) {
            if (role.getNom().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    /*********** UserDetails *******/




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.mdp;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
