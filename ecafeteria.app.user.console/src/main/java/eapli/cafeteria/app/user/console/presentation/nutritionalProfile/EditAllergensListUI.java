/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.app.user.console.presentation.nutritionalProfile;

import eapli.ecafeteria.application.bookings.EditProfileController;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro Quinta
 */
public class EditAllergensListUI extends AbstractUI{
    
    private final EditProfileController controller = new EditProfileController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        
        System.out.println("User Allergens:");
        //print user allergens
        new AllergensListPrinter().visit(controller.getUserAllergens());
        
        System.out.println("-------");

        System.out.println("1. Add Allergen");
        System.out.println("2. Delete Allergen");
        System.out.println("0. Exit");
        
        final int option = Console.readOption(1, 2, 0);
        System.out.println("");
        
        List<Allergen> listAll = new ArrayList<>(); //list to copy the allergens type
        int size = 0;
        
        try {
            switch (option) {
                case 1:
                    
                    size = new AllergensListPrinter().allAllergens(Allergen.allergens(), listAll);
                    System.out.println("Choose the allergen you want to add:");
                    
                    final int numAddAllergen = Console.readOption(1,size,0);
     
                    if(numAddAllergen == 0){
                        break;
                    }
                    CafeteriaUser var = controller.addNewAllergen(listAll.get(numAddAllergen - 1));
                    if (var == null) {
                        System.out.println("Selected allergen is already on the list!");
                    }else{
                        System.out.println("Allergen added!!");
                    }
                    
                    

                    break;
                case 2:
                    new AllergensListPrinter().visit(controller.getUserAllergens());
                    //System.out.println("Choose the allergen you want to add:");
                
                    final int numAllergen = Console.readOption(1,controller.getUserAllergens().size(),0);
                    
                    if(numAllergen != 0){
                        controller.deleteAllergen(controller.getUserAllergens().remove(numAllergen-1));
                    }     
                    
                    break;
                case 0:
                    break;
                default:
                    System.out.println("No valid option selected");
                    break;
            }
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(EditAllergensListUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(EditAllergensListUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public String headline() {
         return "Edit Allergens List:";
    }
    
}
