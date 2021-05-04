/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.meals;

import eapli.ecafetaria.domain.menu.MenuPlan;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Joao Paulo
 */
@Entity
public class MealPlan implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Meal meal;
    private int qt;//quantity of dishes per meal

//    @OneToOne
//    private MenuPlan mP;

    public MealPlan(Meal meal, int qt) {
        this.meal = meal;
        this.qt = qt;
    }

    public MealPlan() {

    }

    public Meal meal() {
        return getMeal();
    }

    /**
     * @return the meal
     */
    public Meal getMeal() {
        return meal;
    }

    /**
     * @return the qt
     */
    public int getQt() {
        return qt;
    }

    /**
     * @param qt the qt to set
     */
    public void setQt(int qt) {
        this.qt = qt;
    }
    
    
}
