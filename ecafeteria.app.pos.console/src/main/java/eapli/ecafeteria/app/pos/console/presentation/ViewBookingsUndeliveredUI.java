/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafeteria.application.pos.ViewBookingsUndeliveredController;
import eapli.framework.presentation.console.AbstractUI;
import java.util.List;

/**
 *
 * @author Francisco
 */
public class ViewBookingsUndeliveredUI extends AbstractUI{
    
    private final ViewBookingsUndeliveredController controller = new ViewBookingsUndeliveredController();

    public ViewBookingsUndeliveredUI(){
        
    }
    
    @Override
    protected boolean doShow() {
        System.out.println(headline() + "\n");
        
        int qtd_bookings_undelivered = controller.viewBookingsUndelivered();
        System.out.println("Number of bookings undelivered: " + qtd_bookings_undelivered + "\n");        
        
        return true;
    }

    @Override
    public String headline() {
        return "****BOOKINGS UNDELIVERED****";
    }
}
