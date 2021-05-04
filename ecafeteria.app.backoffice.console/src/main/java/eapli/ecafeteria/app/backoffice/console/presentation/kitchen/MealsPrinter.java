
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.framework.visitor.Visitor;
import java.util.Calendar;

/**
 *
 * @author salva
 */
class MealsPrinter implements Visitor<Meal> {

    @Override
    public void visit(Meal visitee) {
        String data = String.format("%d-%d-%d",
                visitee.date().get(Calendar.DAY_OF_MONTH),
                visitee.date().get(Calendar.MONTH) + 1,
                visitee.date().get(Calendar.YEAR));
        StringBuilder sb = new StringBuilder();
        sb.append("Meal ID: ").append(visitee.id()).append("\n");
        sb.append("Dish: ").append(visitee.dish().toString()).append("\n");
        sb.append("Date: ").append(data).append("\n");
        sb.append("MealType: ").append(visitee.mealType()).append("\n");
        System.out.println(sb.toString());
    }

}
