/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafeteria.application.kitchen.ShowBookingsByDayController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.util.Console;
import java.util.Calendar;

/**
 *
 * @author Pedro Quinta
 */
public class ShowBookingsByDayUI extends AbstractUI{
    
    private final ShowBookingsByDayController theController = new ShowBookingsByDayController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
       final Calendar date = Console.readCalendar("The day");

       ListWidget<Booking> listWidgetBookings = listWidgetBookings 
               = new ListWidget<>("Bookings:", theController.bookingsByDay(date), new BookingPrinter());
       listWidgetBookings.show();
       Console.waitForKey("Press any key to exit");
       return false;
    }

    @Override
    public String headline() {
        return "Show Bookings By Day";
    }
    
}
