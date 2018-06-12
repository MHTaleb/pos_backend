/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.repositoy;

import com.easyData.pos.easyPos.rest.model.aoth.MNG_PACK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * repository qui gere les pack
 * @author taleb
 */
@Repository
public interface UserPackRepository extends JpaRepository<MNG_PACK, Long>{
    
    /**
     * retourne une liste des pack qui on l id dans la list des id passé en parametre 
     * @param ids ids des pack desiré
     * @return la liste des pack desiré
     */
    public List<MNG_PACK> findAllByIds(@Param("ids") final List<Long> ids);
    
}
