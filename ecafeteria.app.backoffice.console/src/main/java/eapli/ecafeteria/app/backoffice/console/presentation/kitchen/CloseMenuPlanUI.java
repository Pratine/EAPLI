/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.meals.MealPlan;
import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.ecafeteria.application.kitchen.CloseMenuPlanController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Joao Alves - 1151360
 */
public class CloseMenuPlanUI extends AbstractUI {

    private final CloseMenuPlanController controller = new CloseMenuPlanController();
    private final Calendar date = new GregorianCalendar();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        Calendar date = Console.readCalendar("Introduce a date");
        MenuPlan plan = controller.getMenuPlanByDate(date);
        
        List<MealPlan> mealPlans = controller.getMealPlans(plan);
        for (MealPlan m : mealPlans) {
            System.out.println(m.getMeal().toString());
            System.out.println("Quantity: " + m.getQt());
            System.out.println("--------------");
        }
        
        System.out.println("Do you want to Close the Meal Plan?");
        int answer = Console.readInteger("1 - Yes OR 0 - No");
        
        try {
            controller.updateMenuPlan(plan,answer);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(CloseMenuPlanUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(CloseMenuPlanUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    @Override
    public String headline() {
        return "Close Menu Plan";
    }

}
