/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafetaria.domain.meals.MealPlan;
import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.List;

/**
 *
 * @author esoft
 */
public interface MealPlanRepository extends DataRepository<MealPlan, Long> {
   
    
    List<MealPlan> getMeals(MenuPlan mp);
    
}
