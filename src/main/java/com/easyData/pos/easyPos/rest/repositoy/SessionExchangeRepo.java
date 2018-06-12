/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.rest.repositoy;

import com.easyData.pos.easyPos.rest.model.SessionExchange;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * repository jpa pour la gestoin des session
 * @author taleb
 */
public interface SessionExchangeRepo extends JpaRepository<SessionExchange, Long>{
    
}
