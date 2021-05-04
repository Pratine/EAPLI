/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.consultMenu;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.menu.Menu;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author João Santos <1150639@isep.ipp.pt>
 */
public class MenuPrinter implements Visitor<Menu> {

    @Override
    public void visit(Menu visitee) {

        System.out.printf("\nMenu title: %s  \nData: %s <-> %s", visitee.getTitle(), visitee.getDate().start().getTime(), visitee.getDate().end().getTime());
        System.out.println("\n");
        for (Meal meal : visitee.getMeals()) {
            System.out.println("Meal Type: " + meal.mealType());
            System.out.println("Dish Name: " + meal.dishName());
            System.out.println("-------------------------");
        }
    }

    @Override
    public void beforeVisiting(Menu visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(Menu visitee) {
        // nothing to do
    }

}
