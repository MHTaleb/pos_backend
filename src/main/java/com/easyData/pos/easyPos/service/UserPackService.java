/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.service;

import com.easyData.pos.easyPos.rest.model.aoth.MNG_PACK;
import com.easyData.pos.easyPos.rest.model.component.MNG_COMPOSANT;
import com.easyData.pos.easyPos.rest.repositoy.ComponentRepository;
import com.easyData.pos.easyPos.rest.repositoy.UserPackRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author taleb
 */
@Service
public class UserPackService {
    
    @Autowired 
    private UserPackRepository packRepository;
    
    @Autowired
    private ComponentRepository componentRepository;
    
    public MNG_PACK createPack(final String packName,final List<Long> mng_composants_ids){
        MNG_PACK pack = new MNG_PACK();
        pack.setPackName(packName);
        List<MNG_COMPOSANT> composants = componentRepository.findByComponentIds(mng_composants_ids);
        pack.setMng_composants(composants);
        packRepository.save(pack);
        return pack ;        
    }
    
    
    
    public MNG_PACK editPack(final Long id,final String packName,final List<Long> mng_composants_ids){
        MNG_PACK pack = packRepository.findById(id).get();
        pack.setPackName(packName);
        List<MNG_COMPOSANT> composants = componentRepository.findByComponentIds(mng_composants_ids);
        pack.setMng_composants(composants);
        packRepository.save(pack);
        return pack;
    }
    
    public List<MNG_PACK> getAllPacks(){
        return packRepository.findAll();
    }
    
    public MNG_PACK getPack(final Long id){
        return packRepository.findById(id).get();
    }
  
    public void remove(Long id ){
        packRepository.deleteById(id);
    }
    
}
