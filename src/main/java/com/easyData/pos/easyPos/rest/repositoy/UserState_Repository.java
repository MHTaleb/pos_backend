/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.repositoy;

import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER_STATE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author taleb
 */
@Repository
public interface UserState_Repository extends JpaRepository<MNG_USER_STATE, Long>{
    
}
