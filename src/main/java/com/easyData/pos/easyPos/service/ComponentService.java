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
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
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
    
    public ServerResponse getAppsByUserId(final Long user_id) {
        //List<MNG_COMPOSANT> findComponentByUserIdAndComponentType = applicationRepository.findComponentByUserIdAndComponentType(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
        List<MNG_COMPOSANT> apps = componentRepository.findComponentByUserIdAndComponentType(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
        List answer;
        answer = new ArrayList();
        apps.forEach((app) -> {
            answer.add(new AppDTO(app.getId(),app.getCmp_datas(),app.getCmp_type()));
        });
        serverResponse.setMessage("success");
        serverResponse.setContent(answer);
        
        return serverResponse;
    }
    // end of bad practice
    
    // this should be documented
    public List<MNG_COMPOSANT> getFormatedApps(final Long user_id) {
        //List<MNG_COMPOSANT> findComponentByUserIdAndComponentType = applicationRepository.findComponentByUserIdAndComponentType(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
        List<MNG_COMPOSANT> apps = componentRepository.findComponentByUserIdAndComponentType(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
        return apps;
    }
    
    // new component service manager for the Administration client
    public List<MNG_COMPOSANT> getAllComponents() {
        return componentRepository.findAll();
    }
    
    public List<MNG_COMPOSANT> getAllByType(final MNG_COMPOSANT_TYPE mng_composant_type) {
        return componentRepository.findAllByComponentType(mng_composant_type);
    }
    
    public List<MNG_COMPOSANT> getAllByUserId(final Long id) {
        return componentRepository.findAllByUser(id);
    }
    
    public List<MNG_COMPOSANT> getAllByUserIdAndComponentType(final Long id, final MNG_COMPOSANT_TYPE mng_composant_type) {
        return componentRepository.findComponentByUserIdAndComponentType(id, mng_composant_type);
    }
    
    public MNG_COMPOSANT createComponent(final MNG_COMPOSANT_TYPE mng_composant_type,final List<Long> id_fils,final List<Long> id_parents,final List<Long> id_datas){
        final MNG_COMPOSANT mng_composant = new MNG_COMPOSANT();
        mng_composant.setCmp_type(mng_composant_type);
        mng_composant.setCmp_fils(componentRepository.findAllById(id_fils));
        mng_composant.setCmp_parents(componentRepository.findAllById(id_parents));
        mng_composant.setCmp_datas(componentDataRepository.findAllById(id_datas));
        System.out.println(id_datas);
        componentRepository.save(mng_composant);
        return mng_composant;
    }
    
    
    public MNG_COMPOSANT editComponent(final Long mng_component_id,final MNG_COMPOSANT_TYPE mng_composant_type,final List<Long> id_fils,final List<Long> id_parents,final List<Long> id_datas){
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
    
    public void removeComponent(final Long componentID){
        MNG_COMPOSANT mng_composant = componentRepository.findById(componentID).get();
        mng_composant.getCmp_datas().clear();
        mng_composant.getCmp_fils().clear();
        mng_composant.getCmp_parents().clear();
        componentRepository.deleteById(componentID);
    }

    public MNG_COMPOSANT getComponent(Long app_id, MNG_COMPOSANT_TYPE mng_composant_type) {
        MNG_COMPOSANT mng_composant = componentRepository.getOne(app_id);
        if(mng_composant.getCmp_type() == mng_composant_type) return mng_composant;
        return null;
    }
    
    
    
}
