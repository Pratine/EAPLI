/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.kitchen.RegisterBatchController;
import eapli.ecafeteria.application.kitchen.RegisterMealsMadeController;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class RegisterBatchUI extends AbstractUI {

    private final RegisterBatchController controller = new RegisterBatchController();

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

        SelectWidget<Meal> mealSelector = new SelectWidget<>(
                "Select meal", mealList);
        mealSelector.show();
        Meal selectedMeal = mealSelector.selectedElement();
        if (selectedMeal == null) {
            System.out.println("\nCanceled.");
            return false;
        }
        //escolhe material
        List<Material> materialList = (List<Material>) controller.getAvaliableMaterials();
        if (mealList.isEmpty()) {
            System.out.println("There are no materials");
            try {
                //ou adiciona
                addMaterial();
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(RegisterBatchUI.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro!");
            }
            return false;
        }

        SelectWidget<Material> materialSelector = new SelectWidget<>(
                "Select material", materialList);
        materialSelector.show();
        Material selectedMaterial = materialSelector.selectedElement();
        if (selectedMaterial == null) {
            System.out.println("\nAdicionar outro..");
            try {
                //ou adiciona
                addMaterial();
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(RegisterBatchUI.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Erro!");
            }

        }

        //adiciona lotes
        //guarda
        System.out.println("Add new batch");
        String acr = Console.readLine("Insert batch's acronym");
        try {

            controller.newBatch(acr, selectedMaterial, selectedMeal);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(RegisterBatchUI.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO");
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

        System.out.println("\nBatch Registered!\n");

        return false;
    }

    @Override
    public String headline() {
        return "Register Batch";
    }

    private void addMaterial() throws DataConcurrencyException, DataIntegrityViolationException {
        System.out.println("Add new material");
        String acr = Console.readLine("Insert material's acronym");
        String desc = Console.readLine("Insert material's description");
        controller.newMaterial(acr, desc);

    }

}
