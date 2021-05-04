/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.kitchen.BatchSearchController;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.List;

/**
 *
 * @author Guilherme
 */
public class ListMealsByBatchUI extends AbstractUI {

    private final BatchSearchController controller = new BatchSearchController();
    private Batch selectedBatch;

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {

        //procura e lista todos os batches
        List<Batch> batchList = (List<Batch>) controller.allBatches();
        if (batchList.isEmpty()) {
            System.out.println("\nTHERE ARE NO BATCHES\n");
        }

        int want = 1; //Wants to see meals used by batch

        do {
            SelectWidget<Batch> batchSelector = new SelectWidget<>(
                    "SELECT BATCH \n", batchList);
            batchSelector.show();
            selectedBatch = batchSelector.selectedElement();
            if (selectedBatch == null) {
                System.out.println("\nCANCELED.");
                return false;
            }

            List<Meal> mealsUsed = (List<Meal>) controller.listMealsByBatch(selectedBatch);
            if (mealsUsed.isEmpty()) {
                System.out.println("No meals with that batch!");
            } else {
                System.out.println("Meals used by batch: \n " + selectedBatch.toString() + "\n");   //Information display
                for (Meal m : mealsUsed) {
                    System.out.println(m.toString() + "\n");
                }
            }

            batchList.remove(selectedBatch);
            System.out.println("\n CONTINUE?\n1. YES\n0. NO\n"); //1 -> Wants to see more 0 -> Out
            want = Console.readOption(1, 1, 0);
        } while (want == 1 && (!batchList.isEmpty()));
        return false;
    }

    @Override
    public String headline() {
        return "LIST MEALS BY BATCH";
    }

}
