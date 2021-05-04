/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.menu.CreateMenuController;
import eapli.framework.application.Controller;
import eapli.framework.domain.time.DateInterval;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import eapli.ecafetaria.domain.menu.Menu;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joao - 1151360
 */
public class CreateMenuUI extends AbstractUI {

    private final CreateMenuController controller = new CreateMenuController();
    private Calendar beg = new GregorianCalendar();
    private Calendar end = new GregorianCalendar();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        System.out.println("Do you want to Edit or Create a new Menu?");
        int answer = Console.readInteger("0 - Create OR 1 - Edit");

        if (answer == 0) {
            try {
                create();
            } catch (DataConcurrencyException ex) {
                Logger.getLogger(CreateMenuUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataIntegrityViolationException ex) {
                Logger.getLogger(CreateMenuUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            edit();
        }

        return false;
    }

    @Override
    public String headline() {
        return "Create Edit Menu";
    }

    private void create() throws DataConcurrencyException, DataIntegrityViolationException {
        String name = Console.readLine("Choose a Menu name!");
        beg = Console.readCalendar("Introduce the beginning date");
        end = Console.readCalendar("Introduce the end date");
        DateInterval interval = new DateInterval(beg, end);
        controller.createMenu(name, interval);

    }

    private void edit() {
        Calendar date = Console.readCalendar("Introduce a date");
        Menu m = controller.editMenu(date);

        for (Meal meal : m.getMeals()) {
            System.out.println(meal.toString());
            System.out.println("--------------");
        }
        System.out.println("Do you want to add one meal to the menu or remove a meal from the Menu?");
        int answer = Console.readInteger("0 - Add Meal OR 1 - Remove Meal");

        if (answer == 0) {
            add();
        } else {
            delete();
        }

    }

    private void add() {
        Calendar date = Console.readCalendar("Introduce a date of the meal you want to add");
        ArrayList<Meal> meals = controller.getMeals(date);
        for (Meal meal : meals) {
            System.out.println(meal.toString());
            System.out.println("--------------");
        }

    }

    private void delete() {
    }

}
