/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.kitchen.RegisterBatchController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.persistence.BatchRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author maria
 */
public class MealBootstrapper implements Action {

    @Override
    public boolean execute() {
        final DishRepository dishRepoUnit = PersistenceContext.repositories().dishes();
        final ArrayList<Dish> dishes = (ArrayList<Dish>) dishRepoUnit.allDishes();

        for (Dish d : dishes) {
            register(MealType.LUNCH, d, "25-05-2018");
            register(MealType.DINNER, d, "25-05-2018");
        }
        Calendar nextDay = DateTime.tomorrow();
        String nextDayString = String.format("%d-%d-%d",
                nextDay.get(Calendar.DAY_OF_MONTH),
                nextDay.get(Calendar.MONTH) + 1,
                nextDay.get(Calendar.YEAR));

        for (Dish d : dishes) {
            register(MealType.LUNCH, d, nextDayString);
            register(MealType.LUNCH, d, "26-05-2018");
            
            register(MealType.DINNER, d, "23-05-2018");
        }

        register(MealType.DINNER, dishes.get(0), "16-10-2017");
        register(MealType.LUNCH, dishes.get(1), "27-05-2018");
        register(MealType.LUNCH, dishes.get(1), "27-05-2018");
        
        // REGISTO COM BATCHES ----------------------------
        final BatchRepository batchRepo = PersistenceContext.repositories().batches();
        final ArrayList<Batch> batches = (ArrayList<Batch>) batchRepo.all();

        for (Batch b : batches) {
            registerWithBatch(MealType.LUNCH, dishes.get(0), "16-10-2017", b);
            registerWithBatch(MealType.LUNCH, dishes.get(1), "16-10-2017", b);
            registerWithBatch(MealType.DINNER, dishes.get(0), "27-05-2018", b);
        }

        return false;
    }

    private void register(MealType mealType, Dish dish, String date) {

        try {
            Calendar data = DateTime.parseDate(date);
            Meal m = new Meal(dish, data, mealType);

            MealRepository mealRepository = PersistenceContext.repositories().meals();
            mealRepository.save(m);
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            System.out.println("Bootstrap: Error creating meals");
        }
    }

    private void registerWithBatch(MealType mealType, Dish dish, String date, Batch b) {

        try {
            Calendar data = DateTime.parseDate(date);
            Meal m = new Meal(dish, data, mealType);
            RegisterBatchController controller = new RegisterBatchController();
            controller.newBatch(b.getCode(), b.getIngredient(), m);
            controller.saveMeal(m);

        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            System.out.println("Bootstrap: Error creating meal with batch");
        }
    }

}
