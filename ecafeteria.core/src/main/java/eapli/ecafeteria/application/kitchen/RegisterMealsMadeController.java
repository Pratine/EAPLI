/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author maria
 */
public class RegisterMealsMadeController implements Controller {

    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
   
    
    public Iterable<Meal> findByDate(Calendar date) {
        return mealRepository.findByDate(date);
    }
    
    public void setQuantityMade(Meal meal, int quantity) throws IllegalArgumentException {
        if (quantity < 0) {
            throw new IllegalArgumentException();
        }
        meal.setQuantity_made(quantity);
    }

     public Meal saveMeal(Meal meal) throws DataIntegrityViolationException, DataConcurrencyException {
        return mealRepository.save(meal);
    }
}
