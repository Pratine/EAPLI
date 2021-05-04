/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.meals.MealExecution;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Guilherme
 */
public interface MealExecutionRepository extends DataRepository<MealExecution, Long> {
    
    MealExecution findById(Long l);
    
    MealExecution findByMeal(Meal meal);
    
}
