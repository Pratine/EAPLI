/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.consultcaloricintake;

import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import eaplic.ecafetaria.application.bookings.ConsultCaloricIntakeController;
import java.util.Calendar;

/**
 *
 * @author Gonçalo
 */
public class ConsultCaloricIntakeUI extends AbstractUI {

    private ConsultCaloricIntakeController theController = new ConsultCaloricIntakeController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        boolean flag = false;
        do {
            final Calendar dateInit = Console.readCalendar("Insert the initial date (yyyy/MM/DD)", "yyyy/MM/dd");
            final Calendar dateEnd = Console.readCalendar("Insert the end date (yyyy/MM/DD)", "yyyy/MM/dd");
            boolean b1 = theController.insertDateInit(dateInit);
            boolean b2 = theController.insertDateEnd(dateEnd);
            try {

                if (dateInit == null || dateEnd == null) {
                    System.out.println("\nOperation cancelled");
                    return false;
                } else if (b1 == false || b2 == false) {
                    flag = false;
                    System.out.println("Error! Initial date has to be later than today\n");
                } else if (dateInit.getTime().compareTo(dateEnd.getTime()) == 1) {
                    flag = false;
                    System.out.println("Error! End date greater than initial date\n");
                } else {
                    flag = true;
                    String date1 = String.format("%d-%d-%d", dateInit.get(Calendar.DAY_OF_MONTH), dateInit.get(Calendar.MONTH) + 1, dateInit.get(Calendar.YEAR));
                    String date2 = String.format("%d-%d-%d", dateEnd.get(Calendar.DAY_OF_MONTH), dateEnd.get(Calendar.MONTH) + 1, dateEnd.get(Calendar.YEAR));
                    System.out.println("\nCaloric intake between " + date1 + " and " + date2);
                }

            } catch (IllegalArgumentException exc) {
                System.out.println("\nInvalid value.");
            }

        } while (flag
                != true);

        theController.getLoggedUser();

        System.out.println("Total calories consumed: " + theController.getCaloricIntakeFromAllDeliveredBooking());

        int exit;
        exit = Console.readInteger("\nType '0' to exit.");

        return true;
    }

    @Override
    public String headline() {
        return "Consult Caloric Intake in the selected period";
    }

}
