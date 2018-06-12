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
 * Classe sequenceur factory qui donne les foncationalité du sequenceur
 * directement a l utilisateur dans cette classe je gere la problematique de
 * changer la fonction apres chaque appel via les paramaitre d envoi que l
 * utilisateur fourni
 *
 * @author taleb
 */
@Component
public class EasyDataSecuritySequencer {

    /**
     * generateur de fonction qui va se servir des paramaitre aleatoir de la
     * table math_fonction_model
     */
    @Autowired
    public FunctionGenerator functionGenerator;

    @Autowired
    private SessionExchangeRepo exchangeRepo;

    /**
     * constructeur avec paramaitre
     *
     * @param functionGenerator
     */
    public EasyDataSecuritySequencer(FunctionGenerator functionGenerator) {
        this.functionGenerator = functionGenerator;
    }

    /**
     * lire le generateur de fonction cournat
     *
     * @return generateur de fonction
     */
    public FunctionGenerator getFunctionGenerator() {
        return functionGenerator;
    }

    /**
     * mettre le generateur de fonction
     *
     * @param functionGenerator le nouvea generateur de fonction
     */
    public void setFunctionGenerator(FunctionGenerator functionGenerator) {
        this.functionGenerator = functionGenerator;
    }

    /**
     * ceci est la methode qui genere la sequence selon le nombre d echange
     * client serveur envoi reception une fois le challenge generé un mechanisme
     * de mutation commance la methode va preparé de nouveaux paramaitre de
     * generation de fonction generatrice ceci est un modele mathematique ou j
     * ai un ensemble de paramaitre generé aleatoirement ces paramaitre sert a
     * generé un fonction qui va elle meme generé un ensemble de fonction une
     * fois la fonction generatrice disponible on lui demande de donner une
     * sertaine fonction on passe a cette fonction un paramaitre elle donera le
     * chalenge
     *
     * @param x numero du message client envoi
     * @param y numero de message serveur reception
     * @return le challenge
     */
    public String nextChallenge(int x, int y) {
        if (exchangeRepo.count() == 0) {
            final SessionExchange sessionExchange = new SessionExchange();
            sessionExchange.setX(Long.valueOf((int) (Math.random() * 56231) % 100));
            sessionExchange.setY(Long.valueOf((int) (Math.random() * 56831) % 100));
            exchangeRepo.save(sessionExchange);
            return "" + functionGenerator.getFunction(1).getValue(1);
        } else {
            SessionExchange exchange = exchangeRepo.findAll().get(0);
            final String resul = "" + functionGenerator.getFunction((int) (exchange.getX() % 100)).getValue((int) (exchange.getY() % 100));
            exchangeRepo.deleteAll();
            exchange.setX(Long.valueOf((int) (Math.random() * 56231) % 100));
            exchange.setY(Long.valueOf((int) (Math.random() * 56831) % 100));
            exchangeRepo.save(exchange);
            return resul;
        }

    }

}
