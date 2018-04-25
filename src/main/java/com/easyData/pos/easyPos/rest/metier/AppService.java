/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.metier;

import com.easyData.pos.easyPos.dto.AppDTO;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_TYPE;
import com.easyData.pos.easyPos.rest.repositoy.ComponentRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author taleb
 */
@Component
public class AppService {

    @Autowired
    private ComponentRepository applicationRepository;

    @Autowired
    private ServerResponse serverResponse;
    
    public ServerResponse getApps(final Long user_id) {
        //List<MNG_COMPOSANT> findAppByUserID = applicationRepository.findAppByUserID(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
        List<MNG_COMPOSANT> apps = applicationRepository.findAppByUserID(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
        List answer;
        answer = new ArrayList();
        apps.forEach((app) -> {
            answer.add(new AppDTO(app.getId(),app.getCmp_datas(),app.getCmp_type()));
        });
        serverResponse.setMessage("success");
        serverResponse.setContent(answer);
        
        return serverResponse;
    }
    
    public List<MNG_COMPOSANT> getFormatedApps(final Long user_id) {
        //List<MNG_COMPOSANT> findAppByUserID = applicationRepository.findAppByUserID(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
        List<MNG_COMPOSANT> apps = applicationRepository.findAppByUserID(user_id, MNG_COMPOSANT_TYPE.APPLICATION);
        return apps;
    }
    
}
