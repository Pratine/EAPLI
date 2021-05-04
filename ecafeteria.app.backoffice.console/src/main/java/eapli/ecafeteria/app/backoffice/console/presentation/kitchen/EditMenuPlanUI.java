/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.meals.MealPlan;
import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.ecafeteria.application.kitchen.CreateOrEditMenuPlanController;
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
public class EditMenuPlanUI extends AbstractUI {

    private final CreateOrEditMenuPlanController controller = new CreateOrEditMenuPlanController();

    @Override
    protected boolean doShow() {
        List<MenuPlan> plans = (List<MenuPlan>) controller.findOpenMenuPlans();

        if (plans.isEmpty()) {
            System.out.println("There are no Menu Plans in progress ");
        } else {

            SelectWidget<MenuPlan> menuSelector = new SelectWidget<>(
                    "Select menu plan", plans);
            menuSelector.show();
            MenuPlan selectedMenu = menuSelector.selectedElement();
            if(selectedMenu == null){
                return false;
            }
            List<MealPlan> list = selectedMenu.getMealPlans();

            if (list.isEmpty()) {
                System.out.println("\nThis Menu Plan doesnt contain  meals!!! ");
                return false;
            }

            for (MealPlan mealPlan : list) {
                System.out.println("\nDo you want to change the quantity(" + mealPlan.getQt()
                        + ") for meal: " + mealPlan.getMeal() + ")?\n1. Yes\n0. No");
                final int confirmation = Console.readOption(1, 1, 0);
                if (confirmation == 1) {
                    int qtd = Console.readInteger("\nInsert number of meals:");
                    try {
                        if (qtd < 0) {
                            System.out.println("\nCanceled");
                            return false;
                        }
                        mealPlan.setQt(qtd);

                    } catch (final IllegalArgumentException ex) {
                        System.out.println("\nThe number of meals you've inserted is not valid");
                    }
                    System.out.println("\nSave?\n1. Yes\n0. No");
                    final int confirmation2 = Console.readOption(1, 1, 0);
                    if (confirmation2 == 1) {
                        try {
                            mealPlan = controller.saveMealPlan(mealPlan);
                            if (mealPlan == null) {
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
                }
            }

        }
        return false;
    }

    @Override
    public String headline() {
        return "Edit Menu Plan";
    }

}
