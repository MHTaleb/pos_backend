/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.repositoy;

import com.easyData.pos.easyPos.rest.model.aoth.MNG_PROGRAM;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * repository jpa pour la gestion des program
 * @author taleb
 */
public interface ProgramRepository extends JpaRepository<MNG_PROGRAM, Long>{
    
}
