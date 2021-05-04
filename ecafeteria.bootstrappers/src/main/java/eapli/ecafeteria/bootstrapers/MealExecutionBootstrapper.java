/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.meals.MealExecution;
import eapli.ecafeteria.persistence.MealExecutionRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Guilherme
 */
public class MealExecutionBootstrapper implements Action {

    @Override
    public boolean execute() {
        final MealRepository mealRepoUnit = PersistenceContext.repositories().meals(); //vai buscar as meals
        final ArrayList<Meal> meals = (ArrayList<Meal>) mealRepoUnit.findAll(); //Passa meals para um array

        for (Meal m : meals) { 
            try {
                register(m);
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(MealExecutionBootstrapper.class.getName()).log(Level.SEVERE, "ERROR TRYING TO REGISTER MEAL EXECUTION", ex);
            }
        }
        return false;
    }

    private void register(Meal meal) throws DataConcurrencyException, DataIntegrityViolationException { //inicia as meal execution 
        MealExecution mealExecution = new MealExecution(meal,0,0,0);
        MealExecutionRepository mealExecutionRepository = PersistenceContext.repositories().mealsExecution();
        mealExecutionRepository.save(mealExecution);
       
    }
    
}
