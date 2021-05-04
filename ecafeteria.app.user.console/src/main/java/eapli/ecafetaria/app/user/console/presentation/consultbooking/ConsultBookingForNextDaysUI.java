package eapli.ecafetaria.app.user.console.presentation.consultbooking;

//<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import eaplic.ecafetaria.application.bookings.ConsultBookingForNextDaysController;

/**
 *
 * @author Gonçalo
 */
public class ConsultBookingForNextDaysUI extends AbstractUI {

    private ConsultBookingForNextDaysController theController = new ConsultBookingForNextDaysController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        int nrDays;
        boolean flag = false;
        do {
            nrDays = Console.readInteger("\nInsert a number of days in order to set a limit date. Type '0' to exit.");
            try {
                theController.insertNrOfDays(nrDays);

                if (nrDays == 0) {
                    System.out.println("\nOperation cancelled");
                    return false;
                } else if (nrDays < 0) {
                    flag = false;
                    System.out.println("\nInvalid number");
                } else {
                    flag = true;
                }

            } catch (IllegalArgumentException exc) {
                System.out.println("\nInvalid value.");
            }

        } while (flag != true);
        theController.getLoggedUser();
        System.out.println("Meals in " + nrDays + " days ");
        int size = theController.getBookingListForNextDays().size();
        for (int i = 0; i < size; i++) {
            System.out.println(i + 1 + ") " + theController.getBookingListForNextDays().get(i));
        }
        if (size < 1) {
            System.out.println("You don't have booked meals in that period!");
        }
        int exit;
        exit = Console.readInteger("\nType '0' to exit.");
        return true;
    }

    @Override
    public String headline() {
        return "Consult Booking for the next days";
    }
}
