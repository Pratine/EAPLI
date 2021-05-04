/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.meals.MealExecution;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.persistence.MealExecutionRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Francisco
 */
public class ViewAvailableMealsController {
    
    private final MealRepository meal_repository = PersistenceContext.repositories().meals();
    private final MealExecutionRepository execution_repository = PersistenceContext.repositories().mealsExecution();
    
    public Map<DishType, Integer> viewAvailableMeals() {
        //AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SALE);
        
        Map<DishType, Integer> mealsQuantity = new HashMap<>();
        
        Calendar currentDay = Calendar.getInstance();
        List<Meal> list = (List<Meal>) this.meal_repository.findByDate(currentDay);
        
        for(Meal meal : list){
            DishType dish_type = meal.dish().dishType();
            
            MealExecution mealExecution = this.execution_repository.findByMeal(meal);
            
            if(!mealsQuantity.containsKey(meal.dish().dishType())){                
                mealsQuantity.put(dish_type, mealExecution.leftoverQty());            
            }else{
                mealsQuantity.replace(dish_type, mealsQuantity.get(dish_type) + mealExecution.leftoverQty());
            } 
            
            //mealsQuantity.put(meal, meal.quantity_made());
        }
        return mealsQuantity;
    }
}
