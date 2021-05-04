/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain;

import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author VitorCardoso(1161895
 */
@Entity
public class Movements implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private MecanographicNumber mecanographicNumber;
    private double amount;
    private String description;
    private Calendar dateOfTransaction;

    public Movements() {
    }

    public Movements(MecanographicNumber mecanographicNumber, double amount, String description, Calendar dateOfTransaction) throws NegativeAmountException {
        if (mecanographicNumber == null || description == null) {
            throw new IllegalStateException();
        }
        if (amount <= 0) {
            throw new NegativeAmountException();
        }
        this.mecanographicNumber = mecanographicNumber;
        this.amount = amount;
        this.description = description;
        this.dateOfTransaction = dateOfTransaction;
    }

    public static class NegativeAmountException extends Exception {
        public NegativeAmountException() {
            System.out.println("The amount must be a positive number and bigger than 0");
        }
    }
}
