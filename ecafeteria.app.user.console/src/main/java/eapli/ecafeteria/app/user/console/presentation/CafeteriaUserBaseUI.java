/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.bookings.CheckBalanceUnderLimitController;
import eapli.ecafeteria.application.bookings.ShowNextBookedMealController;
import eapli.framework.presentation.console.AbstractUI;

/**
 *
 * @author mcn
 */
public abstract class CafeteriaUserBaseUI extends AbstractUI {

    protected abstract CafeteriaUserBaseController controller();

    private final ShowNextBookedMealController theController = new ShowNextBookedMealController();
    private final CheckBalanceUnderLimitController balanceLimitController = new CheckBalanceUnderLimitController();
    public String showBalance() {
        String limits = "";
        if(balanceLimitController.checkBalanceUnderLimit(AuthorizationService.session().authenticatedUser().id())){
            limits = " - BALANCE BELOW LIMIT VALUE";
        }
        return "CURRENT BALANCE OF YOUR USERCARD: " + controller().balance().toString() + "" +limits;
    }

    public String showNextBookedMeal() {
        theController.getLoggedUser();
        Booking nextBooking = theController.showNextBookedMeal();
        return "NEXT MEAL BOOKED: " + (nextBooking != null ? nextBooking.toString() : "YOU DON'T HAVE MORE BOOKINGS");
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   " + showBalance() + " | " + showNextBookedMeal();
    }

    @Override
    protected void drawFormTitle(String title) {
        // drawFormBorder();
        final String titleBorder = BORDER.substring(0, 2) + " " + title;
        System.out.println(titleBorder);
        drawFormBorder();
    }
}
