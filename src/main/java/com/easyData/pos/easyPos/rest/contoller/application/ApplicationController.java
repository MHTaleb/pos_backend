/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import com.easyData.pos.easyPos.dto.component.ComponentForm;
import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_TYPE;
import com.easyData.pos.easyPos.service.ComponentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ceci est un Controller de L api Rest qui nous permet d inscrire des composant
 * de type application cela pour faciliter au front end developper la notion
 * application menu fonction qui se resumerai a 3 API rest signature lien :
 * SERVER_URL/applications
 *
 * @author taleb
 */
@RestController
@RequestMapping(ApplicationController.SERVICE_URI)
public class ApplicationController extends GenericController {

    /**
     *
     */
    public static final String SERVICE_URI = "/applications";

    /**
     *
     */
    @Autowired
    protected ComponentService componentService;

    /**
     * recuperer les application d un utilisateur lient :
     * GET:SERVER_URL/applications/user/{user_id}
     *
     * @param user_id id utilisateur (Path_Variable)
     * @return les applications d un utilisateur
     * @see MNG_COMPOSANT
     */
    @GetMapping(value = "/user/{user_id}")
    protected ServerResponse getApplications(@PathVariable(name = "user_id") final Long user_id) {

        if (isSessionValid()) {
            List<MNG_COMPOSANT> userApps = componentService.getAllByUserIdAndComponentType(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
            initSuccessResponse(userApps);
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }

    /**
     * lire toutes les application instauré dans le serveur lien :
     * GET:SERVER_URL/applications
     *
     * @return liste de tout les application dans le serveur
     * @see MNG_COMPOSANT
     */
    @GetMapping
    protected ServerResponse getAllApps() {

        if (isSessionValid()) {
            List<MNG_COMPOSANT> allApps = componentService.getAllByType(MNG_COMPOSANT_TYPE.APPLICATION);
            initSuccessResponse(allApps);
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;

    }

    /**
     * lire une application specifique par son id lien : GET
     * SERVER_URL/applications/{app_id}
     *
     * @param app_id id de l application
     * @return les donnees sur l application
     * @see MNG_COMPOSANT
     */
    @GetMapping("/{app_id}")
    protected ServerResponse getApp(
            @PathVariable("app_id") final Long app_id
    ) {

        if (isSessionValid()) {
            System.out.println("id of app " + app_id);
            MNG_COMPOSANT app = componentService.getComponent(app_id, MNG_COMPOSANT_TYPE.APPLICATION);
            initSuccessResponse(app);
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;

    }

    /**
     * creer une applicaiton grace a un post d un formulaire de creation lien :
     * POST SERVER_URL/applications
     *
     * @param applicationForm formulaire de creation d une application
     * @return les information sur l application créé ou un message d erreur
     * @see ComponentForm
     * @see MNG_COMPOSANT
     */
    @PostMapping
    protected ServerResponse createApp(
            @RequestParam("componentForm") final ComponentForm applicationForm
    ) {
        System.out.println("here");
        if (isSessionValid()) {

            initSuccessResponse(componentService.createComponent(MNG_COMPOSANT_TYPE.APPLICATION, applicationForm.getId_fils(), applicationForm.getId_parents(), applicationForm.getId_datas()));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }

    /**
     * edition d une application
     *
     * @param applicationForm formulaire d edition remplit des nouveaux données
     * de l application
     * @param id code interne de l application a changer
     * @return l application changé
     *      * @see ComponentForm
     * @see MNG_COMPOSANT
     *
     */
    @PutMapping
    protected ServerResponse editApp(
            @RequestParam("componentForm") final ComponentForm applicationForm, @RequestParam final Long id
    ) {
        System.out.println("here");
        if (isSessionValid()) {

            initSuccessResponse(componentService.editComponent(id, MNG_COMPOSANT_TYPE.APPLICATION, applicationForm.getId_fils(), applicationForm.getId_parents(), applicationForm.getId_datas()));
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;
    }

    /**
     * supression d une application
     *
     * @param app_id ID applicaiton a suprimer
     * @return message deleted
     */
    @DeleteMapping
    protected ServerResponse deleteApp(
            @RequestParam("app_id") final Long app_id
    ) {
        if (isSessionValid()) {
            componentService.removeComponent(app_id);
            initSuccessResponse("deleted");
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;

    }

}
