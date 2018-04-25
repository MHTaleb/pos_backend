/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import com.easyData.pos.easyPos.rest.model.MathFunctionModel;
import com.easyData.pos.easyPos.rest.repositoy.MathRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author taleb
 */
@Component
public class FunctionGenerator {
    
    
    @Autowired
    private MathFunction function;
    
    @Autowired
    private MathRepository mathRepository;        

   
    
    public MathFunction getFunction(int y) {
        
        
        System.out.println(mathRepository.count());
        MathFunctionModel mathFunctionModel = mathRepository.findAll().get(0);
        
        function.setModel(mathFunctionModel,y);
        
        return function;
    }

    public MathRepository getMathRepository() {
        return mathRepository;
    }

    public MathFunction getFunction() {
        return function;
    }
    
    
}
