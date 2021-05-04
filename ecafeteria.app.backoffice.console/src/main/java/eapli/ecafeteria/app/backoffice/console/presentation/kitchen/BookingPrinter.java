/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Pedro Quinta
 */
public class BookingPrinter implements Visitor<Booking> {

    @Override
    public void visit(Booking visitee) {
        System.out.printf("%-30s%-25s%-10s\n", visitee.bookingDate().getTime(), 
                visitee.meal().dishName(), visitee.user().id());   
    }
    
}
