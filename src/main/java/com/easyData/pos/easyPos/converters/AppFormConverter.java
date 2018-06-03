/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.converters;

import com.easyData.pos.easyPos.dto.component.ComponentForm;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.core.convert.converter.Converter;

/**
 *  ComponentForm
 * @author taleb
 */
public class AppFormConverter implements Converter<String, ComponentForm>{

    @Override
    public ComponentForm convert(String s) {
        System.out.println(s);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(s, ComponentForm.class);
        } catch (IOException ex) {
            Logger.getLogger(AppDataFormConverter.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("handled here");
        return new ComponentForm();
    }
    
}