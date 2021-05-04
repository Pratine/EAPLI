/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Henrique Moura Costa
 */
@Entity
public class DeactivationCause implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;
    
    private DeactivationCauseType cause;
    
    public DeactivationCause(DeactivationCauseType cause){
        this.cause=cause;
    }
    
    public DeactivationCause(){
        //for ORM
    }
}
