/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.menu;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.domain.time.DateInterval;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;

/**
 *
 * @author Joao Paulo Add to class some method by @author João Santos
 * <1150639@isep.ipp.pt>
 *
 */
@Entity
public class Menu implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Embedded
    DateInterval date;

    @OneToMany
    private List<Meal> meals;

    public Menu(String title, DateInterval date) {
        setTitle(title);
        setDate(date);
        this.meals = new ArrayList<>();
    }

    protected Menu() {
    }
    
    public Menu(Menu menu){
        this.title = menu.getTitle() + " - copy";
        this.date = menu.date;
        this.meals = menu.getMeals();
    }

    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title can´t be empty");
        } else {
            this.title = title;
        }
    }

    public boolean valideDate(Calendar beganDate, Calendar endDate) {
        return !(beganDate.after(endDate));
    }

    public String getTitle() {
        return title;
    }

    public DateInterval getDate() {
        return date;
    }

    public void setDate(DateInterval date) {
        if (date == null) {
            throw new IllegalArgumentException("Date can´t be null");
        } else {
            this.date = date;
        }
    }

    public void addMeal(Meal m) {
        if (m != null) {
            meals.add(m);
        }else{
            throw new IllegalArgumentException("Meal can´t be null");
        }
    }
    
      public void removeMeal(Meal m) {
        if (m != null) {
            meals.remove(m);
        }else{
            throw new IllegalArgumentException("Meal can´t be null");
        }
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Menu)) {
            return false;
        }

        final Menu that = (Menu) other;
        if (this == that) {
            return true;
        }
        return id().equals(that.id());
    }

    @Override
    public Long id() {
        return id;
    }

    public List<Meal> getMeals() {
        return meals;
    }
    

    public void setMeals(ArrayList<Meal> meals) {
        this.meals = meals;
    }
    
    @Override
    public String toString(){
        return "Menu:" + this.title;
    }
    
    public void setDate(Calendar sDate,Calendar eDate){
        this.date = new DateInterval(sDate,eDate);
    }
    
    
    
    
}
