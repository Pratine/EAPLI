/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.cafeteria.domain.cafeteriashift.CafeteriaShift;
import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.meals.MealExecution;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.persistence.CafeteriaShiftRepository;
import eapli.ecafeteria.persistence.MealExecutionRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;


/**
 *
 * @author Guilherme
 */
public class CafeteriaShutdownController implements Controller{
   
        private final MealRepository mealRepository = PersistenceContext.repositories().meals();
        private final MealExecutionRepository mealExecutionRepository = PersistenceContext.repositories().mealsExecution();
        private final CafeteriaShiftRepository cafeteriaShiftRepository = PersistenceContext.repositories().cafeteriaShifts();
              
        public Iterable<Meal> listMeals(Calendar date){
           return mealRepository.findByDate(date);
        }
    
        public MealExecution setLeftoverQuantity(Meal meal,int qty) throws DataConcurrencyException, DataIntegrityViolationException{
            MealExecution me = mealExecutionRepository.findByMeal(meal);
            me.changeLeftoverQty(qty);
            return mealExecutionRepository.save(me);
        }
        
        public void shutdownCafeteria(Calendar date,MealType mealType) throws DataConcurrencyException, DataIntegrityViolationException{
            CafeteriaShift cafeShift = cafeteriaShiftRepository.getShift(date, mealType);
            if(cafeShift==null){
                System.out.println("\nCouldn't find Cafeteria Shfit with that date/meal\n");
                return;
            }
            cafeShift.shutdownShift();
            CafeteriaShift cs = cafeteriaShiftRepository.save(cafeShift);
            if(cs==null){
                System.out.println("\nCOULDNT SAVE CAFETERIA SHIFT\n");
            }
        }
        
        

        
}

