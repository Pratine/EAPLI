/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.kitchen.RegisterMealsMadeController;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class RegisterMealsMadeUI extends AbstractUI {

    private final RegisterMealsMadeController controller = new RegisterMealsMadeController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        final Calendar date = Console.readCalendar("The day");

        //procura as refeicoes a partir da data
        List<Meal> mealList = (List<Meal>) controller.findByDate(date);
        if (mealList.isEmpty()) {
            System.out.println("There are no meals for the selected date");
            return false;
        }
        //seleciona a refeicao

        SelectWidget<Meal> mealSelector = new SelectWidget<>(
                "Select meal", mealList);
        mealSelector.show();
        Meal selectedMeal = mealSelector.selectedElement();
        if (selectedMeal == null) {
            System.out.println("\nCanceled.");
            return false;
        }
        //insere a quantidade de pratos
        int qtd = Console.readInteger("\nInsert number of meals actually made");
        try {
            controller.setQuantityMade(selectedMeal, qtd);
            if (qtd == 0) {
                System.out.println("\nCanceled");
                return false;
            }
        } catch (final IllegalArgumentException ex) {
            System.out.println("\nThe number of meals you've inserted is not valid");
        }
        //confirma
        //By confirming, it saves the information gathered in the database
        System.out.println("\nSave?\n1. Yes\n0. No");
        final int confirmation = Console.readOption(1, 1, 0);
        if (confirmation == 1) {
            try {
                if (controller.saveMeal(selectedMeal) == null) {
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
            return false;
        }

        System.out.println("\nAmount of meals actually made registered!\n");
        return false;
    }

    @Override
    public String headline() {
        return "Register Meals Actually Made";
    }

}
