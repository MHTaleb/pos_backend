/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.repositoy;

import com.easyData.pos.easyPos.rest.model.aoth.MNG_PACK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author taleb
 */
@Repository
public interface UserPackRepository extends JpaRepository<MNG_PACK, Long>{
    
}
