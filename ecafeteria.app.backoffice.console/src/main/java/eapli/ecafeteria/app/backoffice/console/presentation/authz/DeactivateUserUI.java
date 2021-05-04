/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.authz;

import java.util.logging.Level;
import java.util.logging.Logger;

import eapli.ecafeteria.application.authz.DeactivateUserController;
import eapli.ecafeteria.domain.authz.DeactivationCauseType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Fernando
 */
@SuppressWarnings("squid:S106")
public class DeactivateUserUI extends AbstractUI {

    private final DeactivateUserController theController = new DeactivateUserController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<SystemUser> iterable = this.theController.activeUsers();
        
        if (!iterable.iterator().hasNext()) {
            System.out.println("There is no registered User");
        } else {
            
        final SelectWidget<SystemUser> selector = new SelectWidget<>("Users:", iterable, new DeactivateUserListPrinter());
        selector.show();
        
        final SystemUser theUser = selector.selectedElement();        
        
            if (theUser == null) {
                System.out.println("No valid user selected");
            } else {
                try {
                    //Cause selector -------------------------------------------
                    System.out.println("Choose cause:");
                    DeactivationCauseType[] causeList = DeactivationCauseType.values();
                    for(int i=1;i<=causeList.length;i++){
                        System.out.println(i+". "+causeList[i-1]);
                    }
                    System.out.println("0. Exit");
                    final int causeSelected = Console.readInteger("Select an option:");
                    if(causeSelected==0){
                        return true;
                    } else {
                        this.theController.setDeactivationCause(theUser,causeList[causeSelected-1].toString());
                    }
                    //
                    System.out.println("Insert deactivation comment (press <ENTER> to skip):\n");
                    this.theController.setDeactivateComment(theUser);
                    //
                    this.theController.deactivateUser(theUser);
                    System.out.println("User deactivated.");
                } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
                    Logger.getLogger(DeactivateUserUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Deactivate User";
    }
}
