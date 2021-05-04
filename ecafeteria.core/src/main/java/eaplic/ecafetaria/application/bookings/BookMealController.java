package eaplic.ecafetaria.application.bookings;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafetaria.domain.bookings.BookingBuilder;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafetaria.domain.bookings.CreatedBookingEvent;
import eapli.ecafeteria.domain.cafeteriauser.Profile;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.dto.NutricionalInfoDTO;
import eapli.ecafeteria.dto.ProfileDTO;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1150448
 */
public class BookMealController implements Controller {
    
    private final MealRepository mealsRepository = PersistenceContext.repositories().meals();
    private final BookingRepository bookingsRepository = PersistenceContext.repositories().bookings();
    private final CafeteriaUserService cafetariaUserService = new CafeteriaUserService();
    
    private final CafeteriaUser cafetariaUser;
    private final Integer currentWeekCalorieConsumption;
    private final Integer currentWeekSaltConsumption;
    private Meal selectedMeal;
    
    public BookMealController(){
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);
        this.cafetariaUser = cafetariaUserService.findCafeteriaUserByUsername(
                AuthorizationService.session().authenticatedUser().username()
        ).get();
        
        Calendar dateInit = DateTime.beginningOfWeek(DateTime.currentYear(), DateTime.currentWeekNumber());
        Calendar dateEnd = DateTime.endOfWeek(DateTime.currentYear(), DateTime.currentWeekNumber());
        //Calculate currentWeekCalorieConsumption and currentWeekSaltConsumption
        this.currentWeekCalorieConsumption = bookingsRepository.getCaloricIntakeFromAllDeliveredBookings(dateInit, dateEnd, cafetariaUser);
        this.currentWeekSaltConsumption = bookingsRepository.getSaltIntakeFromAllDeliveredBookings(dateInit, dateEnd, cafetariaUser);
    }
    
    public List<Meal> getMealsByDateAndType(Calendar date, MealType type){
        return mealsRepository.findByDateAndType(date,type);
    }
    
    public void selectMeal(Meal meal){
        selectedMeal = meal;
    }
    
    public NutricionalInfoDTO getSelectedMealNutricionalInfoDTO(){
        return selectedMeal.dish().nutricionalInfo().toDTO();
    }
    
    public ProfileDTO getUserProfileDTO(){
        return cafetariaUser.profile().toDTO();
    }
    
    public Integer getUserCurrentWeekCalorieConsumption(){
        return this.currentWeekSaltConsumption;
    }
    
    public Integer getUserCurrentWeekSaltConsumption(){
        return this.currentWeekSaltConsumption;
    }
    
    public List<Allergen> getUserMealAllergens(){
        List<Allergen> out = new ArrayList<>();
        List<Allergen> userAllergens = cafetariaUser.profile().allergens();
        for(Allergen a : selectedMeal.dish().allergens()){
            if(userAllergens.contains(a))
                out.add(a);
        }
        
        return out;
    }
    
    public boolean isAlreadyBooked(){
         return bookingsRepository.getBookingByMealAndUser(selectedMeal,cafetariaUser) != null;
    }
    
    public boolean canAffordSelectedMeal(){
        return cafetariaUser.getBalance() > selectedMeal.dish().currentPrice().amount();
    }
    
    public Booking registerBooking() throws DataConcurrencyException, DataIntegrityViolationException {
        BookingBuilder bookingBuilder = new BookingBuilder();
        bookingBuilder.withCafeteriaUser(cafetariaUser);
        bookingBuilder.withMeal(selectedMeal);
        Booking booking = bookingBuilder.build();
        
        cafetariaUser.removeCredits((float)selectedMeal.dish().currentPrice().amount());
        
        CreatedBookingEvent c = new CreatedBookingEvent();
        c.notifyObservers();
        return bookingsRepository.save(booking);
        
    }
    
}
