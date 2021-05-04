/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.menu.Menu;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.time.DateInterval;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Santos <1150639@isep.ipp.pt>
 */
public class MenuBootstrapper implements Action {

    @Override
    public boolean execute() {

        final MealRepository mealRepoUnit = PersistenceContext.repositories().meals();
        final ArrayList<Meal> meals = (ArrayList<Meal>) mealRepoUnit.findAll();

        Calendar date1 = DateTime.newCalendar(2018, 5, 14);
        Calendar date2 = DateTime.newCalendar(2018, 5, 18);
        Menu menu1 = new Menu("Menu week nº22", new DateInterval(date1, date2));

        menu1.addMeal(meals.get(0));
        menu1.addMeal(meals.get(1));
        menu1.addMeal(meals.get(2));
        menu1.addMeal(meals.get(3));
        menu1.addMeal(meals.get(4));
        menu1.addMeal(meals.get(5));
        menu1.addMeal(meals.get(6));
        menu1.addMeal(meals.get(7));
        menu1.addMeal(meals.get(8));
        menu1.addMeal(meals.get(9));

        Calendar date3 = DateTime.newCalendar(2018, 5, 21);
        Calendar date4 = DateTime.newCalendar(2018, 5, 25);
        Menu menu2 = new Menu("Menu week nº23", new DateInterval(date3, date4));

        menu2.addMeal(meals.get(10));
        menu2.addMeal(meals.get(11));
        menu2.addMeal(meals.get(12));
        menu2.addMeal(meals.get(13));
        menu2.addMeal(meals.get(14));
        menu2.addMeal(meals.get(15));
        menu2.addMeal(meals.get(16));
        menu2.addMeal(meals.get(17));
        menu2.addMeal(meals.get(18));
        menu2.addMeal(meals.get(19));

        Calendar date5 = DateTime.newCalendar(2018, 5, 28);
        Calendar date6 = DateTime.newCalendar(2018, 6, 1);
        Menu menu3 = new Menu("Menu week nº24", new DateInterval(date5, date6));

        menu3.addMeal(meals.get(10));
        menu3.addMeal(meals.get(11));

        register(menu1);
        register(menu2);
        register(menu3);

        return false;
    }

    private void register(Menu menu) {
        try {
            MenuRepository menuRepository = PersistenceContext.repositories().menus();
            menuRepository.save(menu);

        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            System.out.println("Bootstrap: Error creating menus");
        }
    }

}
