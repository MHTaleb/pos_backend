/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.repositoy;

import com.easyData.pos.easyPos.rest.model.aoth.MNG_USER_LANG;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * repository jpa pour la gestion des langue
 * @author taleb
 */
@Repository
public interface UserLangRepository extends JpaRepository<MNG_USER_LANG, Long>{
    
}
