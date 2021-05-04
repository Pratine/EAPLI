/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafetaria.domain.meals.MealPlan;
import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.ecafeteria.persistence.MealPlanRepository;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Joao Alves - 1151360
 */
public class JpaMealPlanRepository extends CafeteriaJpaRepositoryBase<MealPlan, Long> implements MealPlanRepository{

    @Override
    public List<MealPlan> getMeals(MenuPlan mp) {
        Query query = entityManager().createQuery("SELECT m FROM MealPlan m", MealPlan.class);
        List<MealPlan> meals = query.getResultList();
       
        return meals;
    }    
}