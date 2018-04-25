/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import com.easyData.pos.easyPos.rest.contoller.tools.HttpSessionVars;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.contoller.tools.RequestParamVars;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_DATA;
import com.easyData.pos.easyPos.rest.repositoy.ComponentDataRepository;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taleb
 */
@RestController
@RequestMapping(ComponentDataController.REST_URL)
public class ComponentDataController {
    
    public static final String REST_URL ="/components/datas";
    
    
    @Autowired
    private HttpSession httpSession;

    @Autowired
    private HttpSessionVars httpSessionVars;

    
    @Autowired
    private ServerResponse serverResponse;
    
    @Autowired
    private ComponentDataRepository componentDataRepository;
    
            
    @Autowired
    private MNG_COMPOSANT_DATA mng_composant_data;
    
    @PostMapping
    public ServerResponse addData(
            @RequestParam(RequestParamVars.CODE_ATT) String code_att, 
            @RequestParam(RequestParamVars.LABEL_ATT) String label_att,
            @RequestParam(RequestParamVars.VALUE_ATT) String value_att 
    ){
        
        if(httpSession.getAttribute(httpSessionVars.CURRENT_USER) == null)return serverResponse;
        
        
        mng_composant_data.setCmp_attr_code(code_att);
        mng_composant_data.setCmp_attr_label(label_att);
        mng_composant_data.setCmp_attr_value(value_att);
        
        componentDataRepository.save(mng_composant_data);
        
        return serverResponse;
    }
    
    
}
