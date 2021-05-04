/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.domain.cafeteriashift;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Version;


/**
 *
 * @author Guilherme
 */
@Entity
public class CafeteriaShift implements AggregateRoot<Long>, Serializable{
    
    private static final long serialVersionUID = 2L;

    @Version
    private Long version;

    
    @Override
    public boolean sameAs(Object other) {
         if (!(other instanceof CafeteriaShift)) {
            return false;
        }

        final CafeteriaShift that = (CafeteriaShift) other;
        if (this == that) {
            return true;
        }

        return id().equals(that.id()) && state.equals(that.state)
                && mealType.equals(that.mealType) && date.equals(that.date); 
    }

    @Override
    public Long id() {
        return id;
    }
    
    @Id
    @GeneratedValue
    private Long id;

    private enum State {
        OPEN,
        CLOSED
    };
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar date;
    
    private MealType mealType;
    private State state;

    public CafeteriaShift(Calendar date, MealType mealType) {
        if (date == null) {
            System.out.println("Invalid date!\n");
            throw new IllegalArgumentException();
        }
        
        if( mealType == null){
            System.out.println("Invalid meal type!\n");

            throw new IllegalArgumentException();
        }
        
        this.date = date;
        this.mealType = mealType;
        this.state = State.OPEN;
    }
    /**
     * Default Empty Constructor
     */
     protected CafeteriaShift() {
        
    }
    
    /**
     * 
     * @return the meal type
     */
    public MealType mealType() {
        return this.mealType;
    }
    
    /**
     * 
     * @return the shift state
     */
    public String state() {
        return String.valueOf(this.state);
    }
    
    /**
     * Shutdown the shift
     */
    public void shutdownShift() {
        state = State.CLOSED;
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
        final CafeteriaShift other = (CafeteriaShift) obj;
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (this.mealType != other.mealType) {
            return false;
        }
        if (this.state != other.state) {
            return false;
        }
        return true;
    }
    
    
}
