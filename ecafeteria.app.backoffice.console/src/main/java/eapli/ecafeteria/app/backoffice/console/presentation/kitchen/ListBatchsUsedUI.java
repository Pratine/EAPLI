/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.kitchen.ListBatchsUsedController;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.List;

/**
 *
 * @author salva
 */
public class ListBatchsUsedUI extends AbstractUI {

    private final ListBatchsUsedController theController = new ListBatchsUsedController();

    protected Controller controller() {
        return this.theController;
    }

    protected Long choseMeal() {
        System.out.println("Insert meal id: ");
        return Long.parseLong(System.console().readLine());
    }

    @Override
    protected boolean doShow() {
        List<Meal> lst = theController.all();

        if (lst.isEmpty() || lst == null) {
            System.out.println("There are no meals");
            return false;
        }
        SelectWidget<Meal> mealSelect = new SelectWidget<>("Select Meal", lst);
        mealSelect.show();

        Meal selectedMeal = mealSelect.selectedElement();

        List<Batch> batchList = selectedMeal.batches();
        if (batchList.isEmpty()) {
            System.out.println("There are no batchs for in this meals");
            return false;
        }
        System.out.println(batchList);
        
        return true;
    }

    @Override
    public String headline() {
        return "List Batchs";
    }
}
