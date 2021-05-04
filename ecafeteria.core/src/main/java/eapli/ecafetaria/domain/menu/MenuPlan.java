/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.menu;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.meals.MealPlan;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Joao Paulo
 */
   enum State {
        IN_PROGRESS,
        CLOSED
    }

@Entity
public class MenuPlan implements Serializable{
    @Id
    @GeneratedValue
    private Long id;
    
    @OneToOne
    private Menu menu;
    
//    private List<MealPlan> mealPlans;

    public Menu menu() {
        return menu;
    }
    
    @OneToMany
    private List<MealPlan> mealPlans;
   
        
    
    @Column (name = "menuPlanState")
    @Enumerated(EnumType.STRING)
    private State state;

    public MenuPlan(Menu menu) {
        this.menu=menu;
        this.state = State.IN_PROGRESS;
        this.mealPlans = new ArrayList<>();
    }
     public MenuPlan() {
        this.state = State.IN_PROGRESS;
        this.mealPlans = new ArrayList<>();
    }
    
//    /**
//     * Add MealPlan to MenuPlan
//     *
//     * @param mealPlan
//     */
    public void addMealPlan(MealPlan mealPlan) {
        if (state == State.IN_PROGRESS) {
            mealPlans.add(mealPlan);
        }
    }

    public void closeMenuPlan() {
        state = State.CLOSED;
    }

//    public ArrayList<MealPlan> menuPlan(){
//        return menuPlan;
//    }

    /**
     * @return the state
     */
    public State getState() {
        return state;
    }
    
    public boolean isClosed(){
        if(state == State.CLOSED){
            return true;
        }else{
            return false;
        }
    }
    
        public List<MealPlan> getMealPlans() {
        return mealPlans;
    }
        
    public String toString(){
        return "Menu Plan: " + id + ";State " + state.toString();
    }

}
