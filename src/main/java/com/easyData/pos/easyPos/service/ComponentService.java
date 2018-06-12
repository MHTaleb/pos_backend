/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.service;

import com.easyData.pos.easyPos.dto.AppDTO;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_TYPE;
import com.easyData.pos.easyPos.rest.repositoy.ComponentDataRepository;
import com.easyData.pos.easyPos.rest.repositoy.ComponentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ceci est le service de gestion de composant, il nous permet d ajouter
 * modifier supprimer rechercher un compsant qui peut etre une application , un
 * menu ou une fonction
 *
 * il possede aussi d autre fonctionalité comme la recherche d application lié a un 
 * utilisateur
 * 
 * @author taleb
 */
@Service
public class ComponentService {

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ComponentDataRepository componentDataRepository;

    //to be refactored when revising client pos javafx
    // the service should never have a server response attribute because a service proces only data to the rest controller
    @Autowired
    private ServerResponse serverResponse;

    /**
     * @deprecated ceci devrai etre supprimer et mis a jour adns une eventuel futur version, elle est tjr utiliser adns l application cliente
     * methode permatant la recherche de tout les application lié a  un utilisateur 
     * @param user_id id de l utilisateur 
     * @return liste des application lié a cet utilisateur
     */
    public ServerResponse getAppsByUserId(final Long user_id) {
        //List<MNG_COMPOSANT> findComponentByUserIdAndComponentType = applicationRepository.findComponentByUserIdAndComponentType(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
        List<MNG_COMPOSANT> apps = componentRepository.findComponentByUserIdAndComponentType(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
        List answer;
        answer = new ArrayList();
        apps.forEach((app) -> {
            answer.add(new AppDTO(app.getId(), app.getCmp_datas(), app.getCmp_type()));
        });
        serverResponse.setMessage("success");
        serverResponse.setContent(answer);

        return serverResponse;
    }
    // end of bad practice

    // this should be documented
    /**
     * ceci est la version final de la methode qui retourne la liste des application celon le id du utilisateur
     * @param user_id
     * @return
     */
    public List<MNG_COMPOSANT> getFormatedApps(final Long user_id) {
        //List<MNG_COMPOSANT> findComponentByUserIdAndComponentType = applicationRepository.findComponentByUserIdAndComponentType(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
        List<MNG_COMPOSANT> apps = componentRepository.findComponentByUserIdAndComponentType(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
        return apps;
    }

    // new component service manager for the Administration client
    /**
     * recuperer la liste de tout les composant
     * @return
     */
    public List<MNG_COMPOSANT> getAllComponents() {
        return componentRepository.findAll();
    }

    /**
     * recuper la liste de tout les composant d un type specifique
     * @param mng_composant_type type du composant 
     * @return
     * @see MNG_COMPOSANT_TYPE
     */
    public List<MNG_COMPOSANT> getAllByType(final MNG_COMPOSANT_TYPE mng_composant_type) {
        return componentRepository.findAllByComponentType(mng_composant_type);
    }

    /**
     * liste des composant par utilisateur
     * @param id id utilisateur
     * @return liste de composant
     * @see MNG_COMPOSANT
     */
    public List<MNG_COMPOSANT> getAllByUserId(final Long id) {
        return componentRepository.findAllByUser(id);
    }

    /**
     * liste d un type de composant specifique par utilisateur exemple user 5 type Menu
     * @param id id utilisateur
     * @param mng_composant_type type du composant
     * @return liste des composant
     */
    public List<MNG_COMPOSANT> getAllByUserIdAndComponentType(final Long id, final MNG_COMPOSANT_TYPE mng_composant_type) {
        return componentRepository.findComponentByUserIdAndComponentType(id, mng_composant_type);
    }

    /**
     *
     * pour creer un composant 
     * 
     * @param mng_composant_type type composant
     * @param id_fils list des id des composant fils de ce composant
     * @param id_parents list des id des composant pere de ce composant
     * @param id_datas list des detail ou information sur ce composant
     * @return le composant creer
     */
    public MNG_COMPOSANT createComponent(final MNG_COMPOSANT_TYPE mng_composant_type, final List<Long> id_fils, final List<Long> id_parents, final List<Long> id_datas) {
        final MNG_COMPOSANT mng_composant = new MNG_COMPOSANT();
        mng_composant.setCmp_type(mng_composant_type);
        mng_composant.setCmp_fils(componentRepository.findAllById(id_fils));
        mng_composant.setCmp_parents(componentRepository.findAllById(id_parents));
        mng_composant.setCmp_datas(componentDataRepository.findAllById(id_datas));
        System.out.println(id_datas);
        componentRepository.save(mng_composant);
        return mng_composant;
    }

    /**
     * pour mettre ajour un composant 
     * @param mng_component_id id du composant a mettre a jour 
     * @param mng_composant_type le nouveau type de ce composant
     * @param id_fils la nouvelle list des id des fils de ce composant
     * @param id_parents la nouvelle list des id des pere de ce compsant
     * @param id_datas la nouvelle liste des detail de ce composant
     * @return le composant edité 
     */
    public MNG_COMPOSANT editComponent(final Long mng_component_id, final MNG_COMPOSANT_TYPE mng_composant_type, final List<Long> id_fils, final List<Long> id_parents, final List<Long> id_datas) {
        final MNG_COMPOSANT mng_composant = componentRepository.getOne(mng_component_id);
        mng_composant.setCmp_type(mng_composant_type);
        mng_composant.getCmp_fils().clear();
        mng_composant.getCmp_fils().addAll(componentRepository.findAllById(id_fils));
        mng_composant.getCmp_parents().clear();
        mng_composant.getCmp_parents().addAll(componentRepository.findAllById(id_parents));
        mng_composant.getCmp_datas().clear();
        mng_composant.getCmp_datas().addAll(componentDataRepository.findAllById(id_datas));
        componentRepository.save(mng_composant);
        return mng_composant;
    }

    /**
     * pour supprimer un composant 
     * @param componentID
     */
    public void removeComponent(final Long componentID) {
        MNG_COMPOSANT mng_composant = componentRepository.findById(componentID).get();
        mng_composant.getCmp_datas().clear();
        mng_composant.getCmp_fils().clear();
        mng_composant.getCmp_parents().clear();
        componentRepository.deleteById(componentID);
    }

    /**
     * pour recuperer et verifier qu un composant est d un type specifique
     * @param app_id le id de composant a verifier 
     * @param mng_composant_type le type de composant attendu 
     * @return le composant s il est valide sinon null
     */
    public MNG_COMPOSANT getComponent(Long app_id, MNG_COMPOSANT_TYPE mng_composant_type) {
        MNG_COMPOSANT mng_composant = componentRepository.getOne(app_id);
        if (mng_composant.getCmp_type() == mng_composant_type) {
            return mng_composant;
        }
        return null;
    }

}
