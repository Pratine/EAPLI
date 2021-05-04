/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.app.user.console.presentation.nutritionalProfile;

import eapli.ecafeteria.application.bookings.EditProfileController;
import eapli.ecafeteria.domain.cafeteriauser.Profile;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Quinta
 */
public class EditProfileUI extends AbstractUI {
    
    private final EditProfileController controller = new EditProfileController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        final Profile nutritional = controller.getActualNutritionalProfile();

        if (nutritional != null) {
            System.out.println("Current nutritional information:\n" + nutritional.toString());
        } else {
            System.out.println("There are no saved values for nutritional profile!\n");
        }

        final int maxCaloriesPerMeal = Console.readInteger("New value to maximum calories per meal:");
        final int maxSaltPerMeal = Console.readInteger("New value to maximum salt per meal:");
        final int maxCaloriesPerWeek = Console.readInteger("New value to maximum calories per week:");
        final int maxSaltPerWeek = Console.readInteger("New value to maximum salt per week:");

        try {
            controller.changeNutritionalProfile(maxCaloriesPerMeal, maxCaloriesPerWeek, maxSaltPerMeal, maxSaltPerWeek);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(EditProfileUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public String headline() {
        return "Edit Nutritional Profile:";
    }
    
}
