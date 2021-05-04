/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.meals.MealExecution;
import eapli.ecafeteria.persistence.MealExecutionRepository;
import javax.persistence.Query;

/**
 *
 * @author Guilherme
 */
public class JpaMealsExecutionRepository extends CafeteriaJpaRepositoryBase<MealExecution, Long> implements MealExecutionRepository {

    @Override
    public MealExecution findById(Long l) {
         return this.findOne(l).get();
    }

    @Override
    public MealExecution findByMeal(Meal meal) {
       Query createQuery = entityManager().createQuery("SELECT m FROM MealExecution m WHERE m.meal=:meal");
       createQuery.setParameter("meal", meal);
       return (MealExecution) createQuery.getSingleResult();
    }

    
}