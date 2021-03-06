/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.converters;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * classe configuration de convertisseur ici on enregistre deux convertisseur :
 * AppDataFormConverter - AppFormConverter ces deux on ete deja creer au
 * paravant
 *
 * je veux creer un convertisseur global grace a jackson api et la reflection ou
 * les generics
 *
 * @author taleb
 */
@Configuration
public class ConvertersConfig extends WebMvcConfigurerAdapter {

    /**
     * methode d enregistrement de convertissuer
     *
     * @param registry registre contenant les convertisseur
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new AppDataFormConverter());
        registry.addConverter(new AppFormConverter());
        super.addFormatters(registry); //To change body of generated methods, choose Tools | Templates.
    }

}
