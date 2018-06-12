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
 * class de convertissement necessaire pour le fonctionement de l api rest dans
 * un communication l envoi de donnees dans http sous format text brute exige l
 * existance de convertisseur ceci a fin de pouvoir mapper la representation
 * text en pojo
 *
 * dans cette classe je me sert de Jackson Api pour convertir le String qui est
 * enfin un JSON a un objet pojo de type ComponentForm si la conversion echoue
 * la methode rest serai interrompu et un message d erreur va etre envoyer au
 * client indiquant que les données envoyer sont mal formatter
 *
 * de preference que le client envoi ses donner grace a un mappeur json comme
 * Jackson api ou un equivalent celon sa plateform et sa gamme de technologie
 *
 * @author taleb
 */
public class AppFormConverter implements Converter<String, ComponentForm> {

    /**
     * methode de conversion
     *
     * @param s json en format String
     * @return object mapping de s en ComponentForm
     */
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
