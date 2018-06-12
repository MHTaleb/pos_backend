/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 *
 * ceci est une classe qui nous aide a gerer la cryptologie dans le projet elle
 * contient SHA-256
 *
 * @author taleb
 * @version 1.0 Alpha
 */
public class Crypto {

    /**
     * Methode de cryptage d'un String vers SHA
     *
     * @param text la text a cripter en sha
     * @return le resultat du cryptage
     * @throws java.security.NoSuchAlgorithmException
     */
    public static String getSha(String text) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
        String encoded = Base64.getEncoder().encodeToString(hash);
        return encoded;
    }

}
