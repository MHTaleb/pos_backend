/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import com.easyData.pos.easyPos.dto.component.ComponentDataForm;
import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_DATA;
import com.easyData.pos.easyPos.rest.repositoy.ComponentDataRepository;
import com.easyData.pos.easyPos.rest.repositoy.ComponentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service Rest qui gere les information sur les composants
 * il offre un mechanisme de creation et de recherche
 * la supression et la modification sont faite en cascade avec le composant
 * @author taleb
 */
@RestController
@RequestMapping(ComponentDataController.REST_URL)
public class ComponentDataController extends GenericController {

    /**
     * url du service a ajouter sur l url du serveur
     */
    public static final String REST_URL = "/components/datas";

    @Autowired
    private ComponentDataRepository componentDataRepository;

    @Autowired
    private ComponentRepository componentRepository;

    /**
     * une methode de creation d une liste d attribut (information) sur un composant
     * @param componentDataFormValues
     * @return
     */
    @PostMapping
    protected ServerResponse creatAllCompDatas(
            @RequestBody final List<ComponentDataForm> componentDataFormValues
    ) {

        System.out.println(componentDataFormValues);

        if (isSessionValid()) {
            List<Long> datasIds = new ArrayList();
            componentDataFormValues.stream().forEach(formValue -> {
                System.out.println("form v = " + formValue);
                MNG_COMPOSANT_DATA mng_composant_data = new MNG_COMPOSANT_DATA();

                mng_composant_data.setCmp_attr_code(formValue.getAttCode());
                mng_composant_data.setCmp_attr_label(formValue.getAttTitle());
                mng_composant_data.setCmp_attr_value(formValue.getAttVal());

                componentDataRepository.save(mng_composant_data);
                datasIds.add(mng_composant_data.getId());
            });

            initSuccessResponse(datasIds);
            return serverResponse;

        }
        initFailLoginResponse();
        return serverResponse;
    }

    /**
     * pour lire une donnée sur un composant qui as un certain id
     * @param id le id du composant
     * @return list des donnee en relation a ce composant ( attributs)
     */
    @GetMapping
    protected ServerResponse getDataByComponentID(
            @RequestParam final Long id
    ) {
        if (isSessionValid()) {

            initSuccessResponse(componentRepository.getOne(id).getCmp_datas());
            return serverResponse;
        }
        initFailLoginResponse();
        return serverResponse;

    }

}
