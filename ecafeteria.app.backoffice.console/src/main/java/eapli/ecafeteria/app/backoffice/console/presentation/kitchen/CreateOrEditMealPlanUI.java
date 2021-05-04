/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
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
public class CreateOrEditMealPlanUI extends AbstractUI  {

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
        
        List<Meal> list = selectedMenu.getMeals();
        
        for (Meal meal : list) {
            int qtd = Console.readInteger("\nInsert number for meal:" + meal.toString());
            try {
                
                if (qtd == 0) {
                    System.out.println("\nCanceled");
                    return false;
                }
            } catch (final IllegalArgumentException ex) {
                System.out.println("\nThe number of meals you've inserted is not valid");
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Menu Plan";
    }

}
