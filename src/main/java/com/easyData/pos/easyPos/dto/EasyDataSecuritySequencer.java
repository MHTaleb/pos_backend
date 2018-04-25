/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import com.easyData.pos.easyPos.rest.model.SessionExchange;
import com.easyData.pos.easyPos.rest.repositoy.SessionExchangeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author taleb
 */
@Component
public class EasyDataSecuritySequencer {
    @Autowired
    public FunctionGenerator functionGenerator;
    
    @Autowired
    private SessionExchangeRepo exchangeRepo;

    public EasyDataSecuritySequencer(FunctionGenerator functionGenerator) {
        this.functionGenerator = functionGenerator;
    }

    public FunctionGenerator getFunctionGenerator() {
        return functionGenerator;
    }

    public void setFunctionGenerator(FunctionGenerator functionGenerator) {
        this.functionGenerator = functionGenerator;
    }
    
    
    
    public String nextChallenge(int x, int y) {
        if(exchangeRepo.count()==0){
            final SessionExchange sessionExchange = new SessionExchange();
            sessionExchange.setX(Long.valueOf((int)(Math.random()*56231)%100));
            sessionExchange.setY(Long.valueOf((int)(Math.random()*56831)%100));
            exchangeRepo.save(sessionExchange);
            return ""+functionGenerator.getFunction(1).getValue(1);
        }else{
            SessionExchange exchange= exchangeRepo.findAll().get(0);
            final String resul = ""+functionGenerator.getFunction((int) (exchange.getX()%100)).getValue((int) (exchange.getY()%100));
            exchangeRepo.deleteAll();
            exchange.setX(Long.valueOf((int)(Math.random()*56231)%100));
            exchange.setY(Long.valueOf((int)(Math.random()*56831)%100));
            exchangeRepo.save(exchange);
            return resul;
        }
            
            
    }
    
}
