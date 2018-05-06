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
import java.util.Optional;
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
    
    public static final String URL = "/lang";
    
    @Autowired
    private UserLangRepository langRepository;
    
    @PostMapping
    private ServerResponse createLanguage(
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
    
    @GetMapping
    private ServerResponse getAllLangs(){
        try {
            initSuccessResponse(langRepository.findAll());
            return serverResponse;
            
        } catch (Exception e) {
            e.printStackTrace();
        
        }
            initFailResponse("server failed to process query please try later");
            return serverResponse;
    }
    
    
    @GetMapping("/{lang_id}")
      private ServerResponse getLanguage(
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
            
    @PutMapping
    private ServerResponse updateLanguage(
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
      
    
    @DeleteMapping
     private ServerResponse deleteLanguage(
            @RequestParam("id") Long id
    ){
        System.out.println("updating langue");
        if(!isSessionValid()){
        
            initFailLoginResponse();
            return serverResponse;
        }
        
        langRepository.deleteById(id);
        
        initSuccessResponse("deleted successfully");
        return serverResponse;
        
    }
}
