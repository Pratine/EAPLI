/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.pos;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Utilizador
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"meal_id", "user_number"}))
public class Complaint implements Serializable, AggregateRoot<Long> {

    @Version
    private Long version;
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private CafeteriaUser user;
    @OneToOne
    private Meal meal;
    @Embedded
    private Message msg;

    public Complaint(Meal meal, Message msg, CafeteriaUser user) {
        if (meal == null || msg == null || user == null) {
            throw new IllegalArgumentException();
        }
        this.meal = meal;
        this.user = user;
        this.msg = msg;
    }

    public Complaint(Message msg, CafeteriaUser user) {
        if (msg == null || user == null) {
            throw new IllegalArgumentException();
        }
        this.meal = null;
        this.user = user;
        this.msg = msg;
    }

    public Complaint(Meal meal, Message msg) {
        if (meal == null || msg == null) {
            throw new IllegalArgumentException();
        }
        this.meal = meal;
        this.user = null;
        this.msg = msg;
    }

    public Complaint(Message msg) {
        if (msg == null) {
            throw new IllegalArgumentException();
        }
        this.meal = null;
        this.user = null;
        this.msg = msg;
    }

    public Complaint() {

    }

    public void addMeal(Meal meal) {
        this.meal = meal;
    }

    public void addUser(CafeteriaUser user) {
        this.user = user;
    }

    public Message message() {
        return this.msg;
    }

    public void setMessage(Message msg) {
        this.msg = msg;
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
