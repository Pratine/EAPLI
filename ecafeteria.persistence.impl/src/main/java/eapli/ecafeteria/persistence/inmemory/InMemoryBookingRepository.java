package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author 1150448
 */
public class InMemoryBookingRepository extends InMemoryRepository<Booking, Long>implements BookingRepository {

    @Override
    protected Long newKeyFor(Booking entity) {
        return entity.id();
    }

    @Override
    public Booking findById(Long l) {
        return this.findOne(l).get();
    }

    @Override
    public Iterable<Booking> findAllBookingsByDay(Calendar date) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Booking> checkBooking(CafeteriaUser user, MealType mealType, Date date, Booking.BookingState state) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Booking> findAllBookingsUndelivered() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Iterable<Booking> getBookingListForNextDays(Calendar dateInit, Calendar dateEnd, CafeteriaUser user) {
        return match(e -> e.user().id().equals(user.id()) && e.meal().date().after(dateInit) && e.meal().date().before(dateEnd));
    }

    @Override
    public Booking showNextBookedMealFromUser(CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Booking getBookingByMealAndUser(Meal selectedMeal, CafeteriaUser cafetariaUser) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getCaloricIntakeFromAllDeliveredBookings(Calendar dateInit, Calendar dateEnd, CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Booking> findAllBookingByUser(CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getSaltIntakeFromAllDeliveredBookings(Calendar dateInit, Calendar dateEnd, CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
    
