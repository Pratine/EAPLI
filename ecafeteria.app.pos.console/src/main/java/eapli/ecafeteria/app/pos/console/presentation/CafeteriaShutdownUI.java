/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.kitchen.CafeteriaShutdownController;
import eapli.ecafeteria.domain.pos.MealType;
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
 * @author Guilherme
 */
public class CafeteriaShutdownUI extends AbstractUI {

    private final CafeteriaShutdownController controller = new CafeteriaShutdownController();
    private  Meal selectedMeal;
    
    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        Calendar date = Calendar.getInstance(); //Hoje
        Calendar mealDate = Calendar.getInstance();
        mealDate.add(Calendar.DATE, 1);
        MealType mt;
        
        //procura refeicoes a partir da data
        List<Meal> mealList = (List<Meal>) controller.listMeals(mealDate);
        if (mealList.isEmpty()) {
            System.out.println("\nTHERE ARE NO MEALS FOR SELECTED DATE\n");
        }

        //Assegura que todas as mealsExecution terao nr de meals desperdicadas
        while (!(mealList.isEmpty())) {
            //seleciona a refeicao
            SelectWidget<Meal> mealSelector = new SelectWidget<>(
                    "SELECT MEAL \n", mealList);
            mealSelector.show();
            selectedMeal = mealSelector.selectedElement();
            if (selectedMeal == null) {
                System.out.println("\nCANCELED.");
                return false;
            }
            //insere a quantidade desperdicada da meal
            int wastedQty = Console.readInteger("\nINSERT NUMBER OF WASTED MEALS");
            if (wastedQty == 0) {
                System.out.println("\nCANCELED\n");
            }

            //confirma
            //By confirming, it saves the information gathered in the database
            System.out.println("\nSAVE?\n1. YES\n0. NO\n");
            final int confirmation = Console.readOption(1, 1, 0);
            if (confirmation == 1) {

                try {
                    if (controller.setLeftoverQuantity(selectedMeal, wastedQty) == null) {
                        System.out.println("\nERROR: DATA WASN'T SAVED");
                        return false;
                    }
                } catch (DataIntegrityViolationException ex) {
                    System.out.println("\nINVALID DATA\n");
                    return false;
                } catch (DataConcurrencyException ex) {
                    System.out.println("\nSIMULTANEOUS ACCESS OF DATA BY MULTIPLE USERS.\n ");
                    return false;
                }

            } else {
                System.out.println("\nCANCELED\n");
            }
            mt = selectedMeal.mealType();
            mealList.remove(selectedMeal); //removes selected meal from MealList
            System.out.println("\nWASTED MEALS REGISTERED!\n");
        }
        //seleciona opcao de shift shutdown
        List<String> options = optionList();
        SelectWidget<String> optionSelector = new SelectWidget<>(
                "Select option", options);
        
        optionSelector.show();
        String selectedOption = optionSelector.selectedElement();
        if (selectedOption == null || selectedOption.equalsIgnoreCase("Back")) {
            System.out.println("\nCanceled.");
            return false;
        }else if(selectedOption.equalsIgnoreCase("Shutdown")){
            try {
                controller.shutdownCafeteria(date, selectedMeal.mealType());
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(CafeteriaShutdownUI.class.getName()).log(Level.SEVERE, 
                        "Database error: Invalid data or simultaneous access of "
                                + "the same data by many users!\n", ex);
            }
            System.out.println("Shift shutdown with success!\n");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Cafeteria Shutdown";
    }

    private List<String> optionList(){
        List<String> options = new ArrayList<>();
        options.add("Shutdown");
        options.add("Back");
        return options;
    }
}
