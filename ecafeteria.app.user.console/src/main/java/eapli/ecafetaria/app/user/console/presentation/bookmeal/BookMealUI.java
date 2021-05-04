package eapli.ecafetaria.app.user.console.presentation.bookmeal;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.cafeteriauser.Profile;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.dto.NutricionalInfoDTO;
import eapli.ecafeteria.dto.ProfileDTO;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import eapli.framework.util.DateTime;
import eaplic.ecafetaria.application.bookings.BookMealController;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author 1150448
 */
public class BookMealUI extends AbstractUI {

    private final BookMealController theController = new BookMealController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Calendar mealDate;

        do {
            String inputDate = Console.readLine("\nIntroduce the meal date with the format 'dd-mm-yyyy' : ");
            mealDate = Calendar.getInstance();

            try {
                mealDate.setTime(sdf.parse(inputDate));
                
                
                if(!DateTime.isAfterToday(mealDate)){
                    mealDate = null;
                    System.out.println("You can't book meals dated previously to tommorow");
                }
            } catch (ParseException ex) {
                mealDate = null;
                System.out.println("\nERROR: Date introduced in invalid format.");
            }
            
        } while (mealDate == null);

        MealType mealType = null;

        do {
            String typeMeal = Console.readLine("\nIntroduce the type of meal (lunch or dinner): ");

            if (typeMeal.trim().equalsIgnoreCase("lunch")) {
                mealType = MealType.LUNCH;
            } else if (typeMeal.trim().equalsIgnoreCase("dinner")) {
                mealType = MealType.DINNER;
            } else {
                System.out.println("\nERROR: Invalid Meal Type.");
            }
        } while (mealType == null);

        List<Meal> mealList = theController.getMealsByDateAndType(mealDate, mealType);

        if (mealList.isEmpty()) {
            System.out.println("\nERROR: No meals available of the inserted type and date.\n");
            return false;
        }

        SelectWidget<Meal> mealsWidget = new SelectWidget<>("\nSelect meal: ", mealList);
        mealsWidget.show();

        // Checks if user selected the option to exit
        if (mealsWidget.selectedOption() == 0) {
            return false;
        }
        
        theController.selectMeal(mealsWidget.selectedElement());
        
        if(theController.isAlreadyBooked()){
            System.out.println("\nERROR: You have already booked this meal.\n");
            return false;
        }
        
        if(!theController.canAffordSelectedMeal()){
            System.out.println("\nERROR: You dont have enough balance to book this meal\n");
            return false;
        }
        
        ProfileDTO profileDTO = theController.getUserProfileDTO();
        Integer weeklyConsumedCalories = theController.getUserCurrentWeekCalorieConsumption();
        Integer weeklyConsumedSalt = theController.getUserCurrentWeekSaltConsumption();
        NutricionalInfoDTO nutricionalInfoDTO = theController.getSelectedMealNutricionalInfoDTO();
        
        System.out.println("\nNutritional Info:");
        if(nutricionalInfoDTO.calories > profileDTO.maxCaloriesPerMeal) {
            System.out.printf("\t->Calories: %d g\n",nutricionalInfoDTO.calories);
        } else{
            System.out.printf("\t->Calories: %d  g\n\t->WARNING: %s\n",nutricionalInfoDTO.calories,"MAX CALORIES PER MEAL WILL SURPASS");
        }
        if(nutricionalInfoDTO.salt > profileDTO.maxSaltPerMeal ) {
            System.out.printf("\t->Salt: %d  mg\n\n",nutricionalInfoDTO.salt);
        } else{
            System.out.printf("\t->Salt: %d  mg \n\t->WARNING: %s\n",nutricionalInfoDTO.salt,"MAX SALT PER MEAL WILL SURPASS ");
        }
        
        Integer caloriesAfterConsumption = weeklyConsumedCalories+nutricionalInfoDTO.calories;
        Integer saltAfterConsumption = weeklyConsumedSalt+nutricionalInfoDTO.salt;
        
        System.out.println("\nWeekly consumption:");
        if(caloriesAfterConsumption > profileDTO.maxCaloriesPerWeek) {
            System.out.printf("\t->Calories: %d + %d = %d g\n",weeklyConsumedCalories,nutricionalInfoDTO.calories,caloriesAfterConsumption);
        } else{
            System.out.printf("\t->Calories: %d + %d = %d g \n\t->WARNING: %s\n",weeklyConsumedCalories,nutricionalInfoDTO.calories,caloriesAfterConsumption,"MAX CALORIES PER WEEK WILL SURPASS");
        }
        if(saltAfterConsumption > profileDTO.maxSaltPerWeek) {
            System.out.printf("\t->Salt: %d + %d = %d mg\n\n",weeklyConsumedSalt,nutricionalInfoDTO.salt,saltAfterConsumption);
        } else{
            System.out.printf("\t->Salt: %d + %d = %d mg \n\t->WARNING: %s\n",weeklyConsumedSalt,nutricionalInfoDTO.salt,saltAfterConsumption,"MAX CALORIES SALT WEEK WILL SURPASS ");
        }
        
        List<Allergen> userMealAllergens = theController.getUserMealAllergens();
        boolean hasConfirmation = false;
        if (userMealAllergens != null) {
            System.out.println("\nWARNING");
            System.out.println("You are allergic to the following allergens in the booked meal");
            for (Allergen a : userMealAllergens) {
                System.out.println(a);
            }
        }
        
        String answer;
        do {
            answer = Console.readLine("\nAre you sure you want to book it (Y or N)?");
            hasConfirmation = answer.equalsIgnoreCase("y");
        } while (!answer.equalsIgnoreCase("n") && !answer.equalsIgnoreCase("y"));
        
        if (!hasConfirmation) {
            System.out.println("Booking operation canceled");
            return false;
        }
        
        try {
            theController.registerBooking();
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            System.out.println("Error saving booking to database");
        }
        
        System.out.println("Meal Booked Successfully");
        
        return true;
    }

    @Override
    public String headline() {
        return "========================================";
    }

}
