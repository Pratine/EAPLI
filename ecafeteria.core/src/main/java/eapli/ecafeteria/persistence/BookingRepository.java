package eapli.ecafeteria.persistence;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 1150448
 */
public interface BookingRepository extends DataRepository<Booking, Long> {
    
    Booking findById(Long l);
    
    Iterable<Booking> findAllBookingsByDay(Calendar date);
    
    List<Booking> checkBooking(CafeteriaUser user,MealType mealType,Date date,Booking.BookingState state);
    
    List<Booking> findAllBookingsUndelivered();
    
    Iterable<Booking> getBookingListForNextDays(Calendar dateInit, Calendar dateEnd, CafeteriaUser user);

    Booking getBookingByMealAndUser(Meal selectedMeal, CafeteriaUser cafetariaUser);
    
    List<Booking> findAllBookingByUser(CafeteriaUser user); //João Santos (1150639)
    
    Booking showNextBookedMealFromUser(CafeteriaUser user);
    
    int getSaltIntakeFromAllDeliveredBookings(Calendar dateInit, Calendar dateEnd, CafeteriaUser user);
    
    int getCaloricIntakeFromAllDeliveredBookings(Calendar dateInit, Calendar dateEnd, CafeteriaUser user);
    
}
