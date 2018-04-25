/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import com.easyData.pos.easyPos.rest.model.MathFunctionModel;
import org.springframework.stereotype.Component;

/**
 *
 * @author taleb
 */
@Component
public class MathFunction {

    private int a,b,c,d;
    
    protected void setModel(MathFunctionModel mathFunctionModel,int y) {
        a = mathFunctionModel.getA().intValue()*y*y*y;
        b = mathFunctionModel.getB().intValue()*y*y;
        c = mathFunctionModel.getC().intValue()*y*2*4*y;
        d = mathFunctionModel.getD().intValue()*y*4*y*2+1;
    }
    
    public Long getValue(int x ){
        return  new Long(a*x*x+b*x+(a+c-b*x+b+x)+x*a-c+(a+c+b+x)+-d);
    }
    
}
