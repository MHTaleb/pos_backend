/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller;

import com.easyData.pos.easyPos.DatabaseInit;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER;
import com.easyData.pos.easyPos.rest.repositoy.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * un controller Rest qui nous permet de forcer le serveur a se renitialiser via
 * un lien : SERVER_URL/setup
 *
 * @deprecated remplacer par database init
 * @see DatabaseInit
 * @author taleb
 */
@RestController
@RequestMapping(ServerSetupController.REST_URL)
public class ServerSetupController {

    /**
     *
     */
    public static final String REST_URL = "/setup";

    /**
     *
     */
    @Autowired
    protected ServerResponse response;

    @Autowired
    private UserRepository userRepository;

    /**
     *
     */
    @Autowired
    public MNG_USER mng_user;

    /**
     * une methode POST pour forcer serveur a creer la bdd utilisateur avec un
     * utilisateur standar
     *
     * @param signature la signature pour que cette methode s execute
     * @return
     */
    @PostMapping
    protected ServerResponse doSetup(
            @RequestParam final String signature
    ) {
        if (signature.equals("odsfiusdf65498461dsfkjhgsdfdsf987321324687sdfjegzkugojksdf984321'tè-'_sdfjhbkçè_-çjhsgqkd216987sqjhgkqsd&éé&jsqhkdj98732121")) {

            userRepository.save(mng_user);

        }

        return response;
    }

}
