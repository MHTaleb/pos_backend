/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.easyData.pos.easyPos.dto;

import org.springframework.stereotype.Component;

/**
 *
 * @author taleb
 * @param <T>
 */
@Component
public class ServerResponse<T> {
    
    private String message;
    
    private T content;
   
    private String sequence;

    public String getMessage() {
        return message;
    }

    public void setMessage(String Message) {
        this.message = Message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

   
    
    
    
}
