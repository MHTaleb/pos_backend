/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.service;

import com.easyData.pos.easyPos.rest.model.Role;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_NIVEAU_ACCEE;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import com.easyData.pos.easyPos.rest.repositoy.UserRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * ceci est un service de configuration de securité et authentification spring
 * boot il implemente la class UserDetailsService de spring boot qui permetra de
 * connaitre la base de données des utilisateur cette imlementation n st pas une
 * implmentation generique elle repond a notre architecture metier interdit de
 * toucher
 *
 * @author taleb
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * il est strictement interdit de toucher ici, ceci est un service de
     * configuration de securité de spring boot qui lui permet de lire de la
     * table des utilisateur
     *
     * do not remove or update this is a configuration service mehode consumed
     * by spring boot should never be used in controllers
     *
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("username connect =" + username);
        MNG_USER user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("login attempt failed + " + username);
        }
        final ArrayList<GrantedAuthority> authorities = new ArrayList<>();

        MNG_NIVEAU_ACCEE niveauAcces = user.getNiveauAcces();
        System.out.println("role : " + niveauAcces.getRoles().toString());
        for (Role role : niveauAcces.getRoles()) {
            System.out.println("role " + role);
            authorities.add(
                    new SimpleGrantedAuthority(
                            role.toString()
                    )
            );

        }
        boolean active = user.isEtatUtilisateur();
        String role_active = (active) ? "ROLE_ACTIVE" : "ROLE_INACTIVE";
        System.out.println("role : " + role_active);
        authorities.add(new SimpleGrantedAuthority(role_active));
        System.out.println(authorities);
        return new User(username, user.getPassword(), authorities);
    }

}
