/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.meals;

import eapli.cafeteria.domain.cafeteriashift.CafeteriaShift;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author Guilherme
 */
@Entity
public class MealExecution implements AggregateRoot<Long>, Serializable{
    
    private static final long serialVersionUID = 2L;

    @Version
    private Long version;
    
     @Override
    public boolean sameAs(Object other) {
         if (!(other instanceof MealExecution)) {
            return false;
        }

        final MealExecution that = (MealExecution) other;
        if (this == that) {
            return true;
        }

        return id().equals(that.id()) && meal.equals(that.meal)
                && cookedQty==that.cookedQty && deliveredQty==that.deliveredQty
                && deliveredQty==that.deliveredQty; 
    }

    @Override
    public Long id() {
      return id;
    }
    
    @Id
    @GeneratedValue
    private Long id;
    
    @OneToOne
    private Meal meal; /*meal that was prepared*/
    
    private int cookedQty; /*number of meals cooked*/
    private int deliveredQty; /*number of meals delivered*/
    private int leftoverQty; /*number of meals wasted*/
    
    /**
     * 
     * @param meal The Meal
     * @param cookedQty Amount of meals cooked
     * @param deliveredQty Delivered Quantity of meals
     * @param leftoverQty  Meals wasted
     */
    public MealExecution(Meal meal, int cookedQty, int deliveredQty, 
            int leftoverQty){
        if(meal==null){
            throw new IllegalArgumentException();
        }
        
        this.meal=meal;
        this.cookedQty=cookedQty;
        this.deliveredQty=deliveredQty;
        this.leftoverQty=leftoverQty;
    }
    
    /**
     * Default Empty Constructor
     */
    protected MealExecution(){
        
    }
    
    /**
     * 
     * @return the meal
     */
    public Meal meal() {
        return this.meal;
    }
    
    /**
     * 
     * @return amount of meals cooked
     */
    public int cookedQty() {
        return this.cookedQty;
    }
    
    /**
     * 
     * @return amount of meals delivered
     */
    public int deliveredQty() {
        return this.deliveredQty;
    }
    
    /**
     * 
     * @return meal leftover quantity
     */
    public int leftoverQty() {
        return this.leftoverQty;
    }
    
    /**
     * 
     * @param qty Nr of wasted meals
     */
    public void changeLeftoverQty(int qty){
        this.leftoverQty=qty;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
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
        final MealExecution other = (MealExecution) obj;
        if (this.cookedQty != other.cookedQty) {
            return false;
        }
        if (this.deliveredQty != other.deliveredQty) {
            return false;
        }
        if (this.leftoverQty != other.leftoverQty) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.meal, other.meal)) {
            return false;
        }
        return true;
    }

  
    
}

