/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import java.io.Serializable;
import java.util.Objects;
import java.util.Observable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author VitorCardoso(1161895
 */
@Entity
public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_BALANCE_LIMITE = 5;

    @Id
    @GeneratedValue
    private int id;

    @OneToOne(cascade = {CascadeType.ALL})
    private Balance balance;

    private int balanceLimit;

    public Account(Balance balance) {
        this.balance = balance;
        balanceLimit = DEFAULT_BALANCE_LIMITE;
    }

    public Account(float balance) {
        if (balance >= 0) {
            this.balance = new Balance(balance);
            balanceLimit = DEFAULT_BALANCE_LIMITE;
        } else {
            throw new IllegalArgumentException("No negative numbers!");
        }
    }

    protected Account() {

    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.balance);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Account balance: " + balance + "\n";
    }

    public float getBalance() {
        return this.balance.getBalance();
    }

    public boolean addCredits(float credits) {
        if (credits > 0) {
            float oldBalance = getBalance();
            this.balance = new Balance(oldBalance + credits);
            return true;
        }
        return false;
    }
    
    public boolean removeCredits(float credits) {
        if (credits > 0) {
            float oldBalance = getBalance();
            this.balance = new Balance(oldBalance - credits);
            return true;
        }
        return false;
    }

    public void setBalanceLimit(int newBalanceLimit) {
        this.balanceLimit = newBalanceLimit;
    }

    public int getBalanceLimit() {
        return balanceLimit;
    }
}
