/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.app.user.console.presentation.consultbooking;

import eapli.framework.actions.Action;

/**
 *
 * @author Gonçalo
 */
public class ConsultBookingForNextDaysAction implements Action {

    @Override
    public boolean execute() {
        return new ConsultBookingForNextDaysUI().show();
    }
    
}
