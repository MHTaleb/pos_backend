/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.repositoy;

import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER_TYPE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author taleb
 */
@Repository
public interface UserType_Repository extends JpaRepository<MNG_USER_TYPE, Long> {
    
}
