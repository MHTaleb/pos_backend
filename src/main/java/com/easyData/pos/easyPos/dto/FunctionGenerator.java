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
 * classe qui represente un generateur de fonction pour un nombre precis elle
 * donera une fonction unique
 *
 * @author taleb
 */
@Component
public class FunctionGenerator {

    @Autowired
    private MathFunction function;

    @Autowired
    private MathRepository mathRepository;

    /**
     * generer la i eme fonction de puis le model de fonction qui est lui meme
     * une fonction mathematique
     *
     * @param i indice de la fonction volu (cette fonction est generer
     * dynamiquement donc elle est meconu )
     * @return la fonction generer au runtime
     */
    public MathFunction getFunction(int i) {

        System.out.println(mathRepository.count());
        MathFunctionModel mathFunctionModel = mathRepository.findAll().get(0);

        function.setModel(mathFunctionModel, i);

        return function;
    }

    /**
     *
     * @return repository vers les paramaitre mathematique
     */
    public MathRepository getMathRepository() {
        return mathRepository;
    }

    /**
     *
     * @return retourne la fonction generer
     */
    public MathFunction getFunction() {
        return function;
    }

}
