/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import com.easyData.pos.easyPos.annotation.security.AdminSecured;
import com.easyData.pos.easyPos.rest.contoller.tools.HttpSessionVars;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.contoller.tools.RequestParamVars;
import com.easyData.pos.easyPos.rest.contoller.tools.RequestPathVars;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_DATA;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT_TYPE;
import com.easyData.pos.easyPos.rest.repositoy.ComponentDataRepository;
import com.easyData.pos.easyPos.rest.repositoy.ComponentRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author taleb
 */
@RestController
@RequestMapping(FunctionController.URL_PATH)
public class FunctionController {

    public static final String URL_PATH = "/functions";

    @Autowired
    private HttpSession httpSession;

    @Autowired
    private HttpSessionVars httpSessionVars;

    @Autowired
    private ServerResponse serverResponse ;
    
    @Autowired
    private ComponentDataRepository componentDataRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @PostMapping
    private ServerResponse addFunction(
            @RequestParam(RequestParamVars.DATAS) List<Long> cmp_datas_ids,
            @RequestParam(RequestParamVars.DATAS) List<Long> cmp_pere_ids,
            @RequestParam(RequestParamVars.FILS) List<Long> cmp_fils_ids
    ){
        
        if(httpSession.getAttribute(httpSessionVars.CURRENT_USER) == null)return serverResponse;
        
        MNG_COMPOSANT mng_composant = new MNG_COMPOSANT();
        
        mng_composant.setCmp_type(MNG_COMPOSANT_TYPE.FONCTION);
        
        List<MNG_COMPOSANT_DATA> cmp_datas = componentDataRepository.findByIds(cmp_datas_ids);
        mng_composant.setCmp_datas(cmp_datas);
        
        List<MNG_COMPOSANT> cmp_fils = componentRepository.findByComponentIds(cmp_fils_ids);
        mng_composant.setCmp_fils(cmp_fils);
        
        List<MNG_COMPOSANT> cmp_peres = componentRepository.findByComponentIds(cmp_pere_ids);
        mng_composant.setCmp_parents(cmp_peres);
        
        componentRepository.save(mng_composant);
        
        serverResponse.setContent(null);
        serverResponse.setMessage("success");
        serverResponse.setSequence("not implemented yet");

        
        return serverResponse;
    }
    
    
    @PutMapping
    private ServerResponse addFunctionData(
            @PathVariable(RequestPathVars.ID_COMPOSANT) Long ID_COMPOSANT,
            @RequestParam(RequestParamVars.DATAS) List<Long> cmp_datas_ids,
            @RequestParam(RequestParamVars.DATAS) List<Long> cmp_pere_ids,
            @RequestParam(RequestParamVars.FILS) List<Long> cmp_fils_ids
    ){
    
        if(httpSession.getAttribute(httpSessionVars.CURRENT_USER) == null)return serverResponse;
        
        MNG_COMPOSANT mng_composant = componentRepository.getOne(ID_COMPOSANT);
        
        mng_composant.setCmp_type(MNG_COMPOSANT_TYPE.FONCTION);
        
        List<MNG_COMPOSANT_DATA> cmp_datas = componentDataRepository.findByIds(cmp_datas_ids);
        mng_composant.getCmp_datas().addAll(cmp_datas);
        
        List<MNG_COMPOSANT> cmp_fils = componentRepository.findByComponentIds(cmp_fils_ids);
        mng_composant.getCmp_fils().addAll(cmp_fils);
        
        List<MNG_COMPOSANT> cmp_peres = componentRepository.findByComponentIds(cmp_pere_ids);
        mng_composant.getCmp_parents().addAll(cmp_peres);
        
        componentRepository.save(mng_composant);
        
        serverResponse.setContent(null);
        serverResponse.setMessage("success");
        serverResponse.setSequence("not implemented yet");

        
        return serverResponse;
    }
    
    @PostMapping("/{"+RequestPathVars.ID_COMPOSANT+"}")
    private ServerResponse setFunctionData(
            @PathVariable(RequestPathVars.ID_COMPOSANT) Long ID_COMPOSANT,
            @RequestParam(RequestParamVars.DATAS) List<Long> cmp_datas_ids,
            @RequestParam(RequestParamVars.DATAS) List<Long> cmp_pere_ids,
            @RequestParam(RequestParamVars.FILS) List<Long> cmp_fils_ids
    ){
        if(httpSession.getAttribute(httpSessionVars.CURRENT_USER) == null)return serverResponse;
        
        MNG_COMPOSANT mng_composant = componentRepository.getOne(ID_COMPOSANT);
        
        mng_composant.setCmp_type(MNG_COMPOSANT_TYPE.FONCTION);
        
        List<MNG_COMPOSANT_DATA> cmp_datas = componentDataRepository.findByIds(cmp_datas_ids);
        mng_composant.setCmp_datas(cmp_datas);
        
        List<MNG_COMPOSANT> cmp_fils = componentRepository.findByComponentIds(cmp_fils_ids);
        mng_composant.setCmp_fils(cmp_fils);
        
        List<MNG_COMPOSANT> cmp_peres = componentRepository.findByComponentIds(cmp_pere_ids);
        mng_composant.setCmp_parents(cmp_peres);
        
        componentRepository.save(mng_composant);
        
        serverResponse.setContent(null);
        serverResponse.setMessage("success");
        serverResponse.setSequence("not implemented yet");

        
        return serverResponse;
    }
    
    
    @GetMapping
    private ServerResponse getFunction(
            @PathVariable(RequestPathVars.ID_COMPOSANT) Long ID_COMPOSANT
    ){
        
        if(httpSession.getAttribute(httpSessionVars.CURRENT_USER) == null)return serverResponse;
        
        MNG_COMPOSANT mng_composant = componentRepository.getOne(ID_COMPOSANT);
        
        serverResponse.setContent(mng_composant);
        serverResponse.setMessage("success");
        serverResponse.setSequence("not implemented yet");

        
        return serverResponse;
    }
    
   

}
