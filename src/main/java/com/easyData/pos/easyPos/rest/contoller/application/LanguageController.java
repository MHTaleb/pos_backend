/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.contoller.application;

import com.easyData.pos.easyPos.GenericController;
import com.easyData.pos.easyPos.dto.ServerResponse;
import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER_LANG;
import com.easyData.pos.easyPos.rest.repositoy.UserLangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(LanguageController.URL)
public class LanguageController extends GenericController{
    
    /**
     *
     */
    public static final String URL = "/lang";
    
    @Autowired
    private UserLangRepository langRepository;
    
    /**
     *
     * @param code
     * @return
     */
    @PostMapping
    protected ServerResponse createLanguage(
            @RequestParam("code") String code
    ){
        System.out.println("adding langue");
        if(!isSessionValid()){
        
            initFailLoginResponse();
            return serverResponse;
        }
        
        
        final MNG_USER_LANG lang = new MNG_USER_LANG();
        lang.setCode(code);
        langRepository.save(lang);
        
        initSuccessResponse(lang);
        return serverResponse;
        
    }
    
    /**
     *
     * @return
     */
    @GetMapping
    protected ServerResponse getAllLangs(){
        try {
            initSuccessResponse(langRepository.findAll());
            return serverResponse;
            
        } catch (Exception e) {
            e.printStackTrace();
        
        }
            initFailResponse("server failed to process query please try later");
            return serverResponse;
    }
    
    /**
     *
     * @param id
     * @return
     */
    @GetMapping(params = "id")
      protected ServerResponse getLanguage(
            @RequestParam("id") Long id
    ){
        System.out.println("updating langue");
        if(!isSessionValid()){
        
            initFailLoginResponse();
            return serverResponse;
        }
        
        MNG_USER_LANG lang = langRepository.findById(id).get();
        
        initSuccessResponse(lang);
        return serverResponse;
        
    }
            
    /**
     *
     * @param id
     * @param code
     * @return
     */
    @PutMapping
    protected ServerResponse updateLanguage(
            @RequestParam("id") Long id,
            @RequestParam("code") String code
    ){
        System.out.println("updating langue");
        if(!isSessionValid()){
        
            initFailLoginResponse();
            return serverResponse;
        }
        
        MNG_USER_LANG lang = langRepository.findById(id).get();
        lang.setCode(code);
        langRepository.save(lang);
        
        initSuccessResponse(lang);
        return serverResponse;
        
    }
      
    /**
     *
     * @param id_langue
     * @return
     */
    @DeleteMapping
     protected ServerResponse deleteLanguage(
            @RequestParam("id_langue") Long id_langue
    ){
        System.out.println("updating langue");
        if(!isSessionValid()){
        
            initFailLoginResponse();
            return serverResponse;
        }
        
        langRepository.deleteById(id_langue);
        
        initSuccessResponse("deleted successfully");
        return serverResponse;
        
    }
}
