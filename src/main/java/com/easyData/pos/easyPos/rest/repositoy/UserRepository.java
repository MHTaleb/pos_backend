/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.repositoy;

import com.easyData.pos.easyPos.rest.model.MNG_USER;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * password to sha
 * 
 * @author taleb
 */
@Repository
public interface UserRepository extends JpaRepository<MNG_USER, Long> {
    public List<MNG_USER> doConnect(@Param("us_username") final String us_username,@Param("us_pwdusr") final String us_pwdusr );
    
}
