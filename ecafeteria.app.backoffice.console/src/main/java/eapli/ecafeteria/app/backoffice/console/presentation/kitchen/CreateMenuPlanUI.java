/*
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.meals.MealPlan;
import eapli.ecafeteria.application.kitchen.CreateOrEditMenuPlanController;
import eapli.framework.application.Controller;
import eapli.ecafetaria.domain.menu.Menu;
import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.List;

/**
 *
 * @author Joao Paulo
 */
public class CreateMenuPlanUI extends AbstractUI  {

    private final CreateOrEditMenuPlanController controller = new CreateOrEditMenuPlanController();

    protected Controller controller() {
        return this.controller;
    }

    protected boolean doShow() {
        List<eapli.ecafetaria.domain.menu.Menu> menuList = (List<eapli.ecafetaria.domain.menu.Menu>) controller.findAllMenus();
        if (menuList.isEmpty()) {
            System.out.println("There are no menus");
            return false;
        }
       SelectWidget<eapli.ecafetaria.domain.menu.Menu> menuSelector = new SelectWidget<>(
                "Select menu", menuList);
        menuSelector.show();
        eapli.ecafetaria.domain.menu.Menu selectedMenu = menuSelector.selectedElement();
         if(selectedMenu == null){
                return false;
            }
        List<Meal> list = selectedMenu.getMeals();
        //criar MenuPlan
        MenuPlan menuP = new MenuPlan();
        MealPlan mealP = null;
        if(list.isEmpty()){
            System.out.println("\nThis Menu doesnt contain  meals!!! ");
            return false;
        }
        for (Meal meal : list) {
            int qtd = Console.readInteger("\nInsert number for meal:" + meal.toString());
            try {   
                if (qtd < 0) {
                    System.out.println("\nNot a valid number, set to 0(zero)");
                    qtd=0;
                }
                mealP = new MealPlan(meal,qtd);
                //adicionar a MenuPlan
                try {
                    mealP = controller.saveMealPlan(mealP);
                    if (mealP == null) {
                        System.out.println("\nERROR: the data wasn't saved");
                        return false;
                    }
                    menuP.addMealPlan(mealP);

                } catch (DataIntegrityViolationException ex) {
                    System.out.println("\nInvalid Data");
                    return false;
                } catch (DataConcurrencyException ex) {
                    System.out.println("\nSimultaneous access of the same data by many users. ");
                    return false;
                }
                
            } catch (final IllegalArgumentException ex) {
                System.out.println("\nThe number of meals you've inserted is not valid");
            }

        }
        //guardar
        System.out.println("\nSave?\n1. Yes\n0. No");
        final int confirmation = Console.readOption(1, 1, 0);
        if (confirmation == 1) {
            try {
                
                if (controller.saveMenuPlan(menuP) == null) {
                    System.out.println("\nERROR: the data wasn't saved");
                    return false;
                }

            } catch (DataIntegrityViolationException ex) {
                System.out.println("\nInvalid Data");
                return false;
            } catch (DataConcurrencyException ex) {
                System.out.println("\nSimultaneous access of the same data by many users. ");
                return false;
            }
        } else {
            System.out.println("\nCanceled.");
        }
        System.out.println("\nCreation ended.");
        return false;
    }

    @Override
    public String headline() {
        return "Menu Plan";
    }

}
