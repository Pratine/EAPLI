/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.meals.MealPlan;
import eapli.ecafetaria.domain.menu.Menu;
import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joao Alves - 1151360
 */
public class MealPlanBootstrapper implements Action {

    private final TransactionalContext TxCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final MealPlanRepository mealPlanRepository = PersistenceContext.repositories().mealPlans();    

    @Override
    public boolean execute() {
        final MealRepository mealRep = PersistenceContext.repositories().meals();
        final ArrayList<Meal> meals = (ArrayList<Meal>) mealRep.findAll();
        Calendar date = new GregorianCalendar();
        date.set(2017, 10, 15);
      
        final MenuPlanRepository menuPlanRep = PersistenceContext.repositories().menuPlans(TxCtx);
        MenuPlan plan = menuPlanRep.getMenuPlanByDate(date);
        
        for (Meal meal : meals) {
            if(DateTime.isSameDay(date,meal.date())){
                MealPlan mp = new MealPlan(meal,25);
                try {
                    register(mp);
                } catch (DataConcurrencyException ex) {
                    Logger.getLogger(MealPlanBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
                } catch (DataIntegrityViolationException ex) {
                    Logger.getLogger(MealPlanBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return false;
    }
    
       private void register(MealPlan mP) throws DataConcurrencyException, DataIntegrityViolationException { //inicia as mealPlans 

        TxCtx.beginTransaction();
        mealPlanRepository.save(mP);
        TxCtx.commit();
    }
}
