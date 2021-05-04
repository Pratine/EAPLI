/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.rateMeal;

import eapli.cafeteria.application.ratings.RateMealController;
import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.domain.rateMeal.Rating;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Date;

/**
 *
 * @author Utilizador
 */
public class RateMealUI extends AbstractUI {

    private final RateMealController controller = new RateMealController();

    @Override
    protected boolean doShow() {
        final CafeteriaUser user = controller.getUser();
        if (user != null) {
            controller.setUser(user);
        } else {
            System.out.println("\n User not recognized");
        }
        final Date mealDate = Console.readDate("insert the meal date (YYYY/MM/DD)");
        MealType mealType2 = null;
        String typeMeal;
        do {
            typeMeal = Console.readLine("type of meal(lunch/dinner):");
            if (typeMeal.equalsIgnoreCase("lunch")) {
                mealType2 = MealType.LUNCH;
                break;
            } else if (typeMeal.equalsIgnoreCase("dinner")) {
                mealType2 = MealType.DINNER;
                break;
            }
        } while (!typeMeal.equalsIgnoreCase("lunch") || !typeMeal.equalsIgnoreCase("dinner"));
       
        if (controller.checkBooking(mealDate, mealType2)) {
           
            Booking booking = controller.chooseBooking( controller.getBookings());
              int rating;
        do {
            rating = Console.readInteger("Please insert a value rating [1-5]:");
        } while (rating < 1 || rating > 5);

            final int flag = Console.readInteger("To insert comment press 1.\nTo exit press 0\n");

            if (flag == 1) {
                final String comment = Console.readLine("Describe what you have experienced:");
                try {
                    controller.registerComment(comment, controller.registerRating(booking.meal(), rating));
                    System.out.println("Rating saved!\n Thanks for your time!\n");
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    System.out.println("\n\nThere is already a rating for this meal!");
                }
            } else {
                try {
                    controller.registerRating(booking.meal(), rating);
                    System.out.println("Rating saved!\n Thanks for your time!");
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    System.out.println("\nThere is already a rating for this meal!");
                }
            }

        }
        return false;

    }

    @Override
    public String headline() {
        return "REGISTERING THE RATING OF A MEAL";
    }

}
