/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.pos;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;

/**
 *
 * @author Utilizador
 */
public class Message implements Serializable,AggregateRoot<Long> {
//  

    private String message;

    public Message(String message) {
        this.message = message;

    }

    public Message() {

    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long id() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
