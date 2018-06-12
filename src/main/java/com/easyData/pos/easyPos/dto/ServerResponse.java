/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import org.springframework.stereotype.Component;

/**
 * class qui represente le conteneur d une reponse du serveur il envloppe le
 * protocol http et nous permet de definir nos propre regle et protocole en haut
 * du http
 *
 * <br>
 * la reponse du serveur est sous le schema :
 * "message";"contenuPOJO";"handshake"
 *
 * @author taleb
 * @param <T> T le type de class de reponse
 */
@Component
public class ServerResponse<T> {

    private String message; // message protocol en dehor du http

    private T content; // objet contenu a envoyer

    private String sequence; //sequence du handshake 

    /**
     * lire le message du protocol sa peut etre success failure done
     *
     * vous pouvez definir d autre message specifique celon notre besoin
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * mettre le message du protocol sa peut etre success failure done
     *
     * vous pouvez definir d autre message specifique celon notre besoin
     *
     * @param Message le message protocol a envoyer
     */
    public void setMessage(String Message) {
        this.message = Message;
    }

    /**
     * lire le contenu de la reponse du serveur
     *
     * @return POJO du contenu à envoyer par le serveur
     */
    public T getContent() {
        return content;
    }

    /**
     * mettre le contenu de la reponse du serveur
     *
     * @param content POJO du nouveau contenu à envoyer par le serveur
     */
    public void setContent(T content) {
        this.content = content;
    }

    /**
     * lire handshake sequence
     *
     * @return handshake
     */
    public String getSequence() {
        return sequence;
    }

    /**
     * mettre handshake sequence
     *
     * @param sequence handshake
     */
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

}
