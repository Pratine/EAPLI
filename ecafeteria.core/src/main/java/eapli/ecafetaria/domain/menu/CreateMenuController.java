/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.menu;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.time.DateInterval;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Joao Alves - 1151360
 */
public class CreateMenuController implements Controller {

    private final MenuRepository menuRep = PersistenceContext.repositories().menus();
    private final MealRepository mealRep = PersistenceContext.repositories().meals();

    public void createMenu(String name, DateInterval interval) throws DataConcurrencyException, DataIntegrityViolationException {
        Menu menu = new Menu(name, interval);
        menuRep.save(menu);

    }
    
    public Menu editMenu(Calendar date){
        return menuRep.getMenuPlanByDate(date);
    }
    
    public ArrayList<Meal> getMeals(Calendar date){
        return (ArrayList<Meal>) mealRep.findByDate(date);
    }
}
