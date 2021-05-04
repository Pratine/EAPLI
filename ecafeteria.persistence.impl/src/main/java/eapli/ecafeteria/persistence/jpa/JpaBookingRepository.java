package eapli.ecafeteria.persistence.jpa;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import static java.lang.Math.toIntExact;

public class JpaBookingRepository extends CafeteriaJpaRepositoryBase<Booking, Long> implements BookingRepository {

    @Override
    public Booking findById(Long l) {
        return this.findOne(l).get();
    }

    @Override
    public Iterable<Booking> findAllBookingsByDay(Calendar date) {
        Query createQuery = entityManager().createQuery("SELECT e FROM Booking e, Meal m WHERE e.meal=m and m.date=:date");
        createQuery.setParameter("date", date);
        return createQuery.getResultList();
    }

    @Override
    public List<Booking> findAllBookingsUndelivered() {
        Query query = entityManager().createQuery("SELECT e FROM Booking e WHERE e.bookingState=:state");
        query.setParameter("state", Booking.BookingState.Undelivered);
        return query.getResultList();
    }

    @Override
    public List<Booking> checkBooking(CafeteriaUser user, MealType mealType, Date date, Booking.BookingState state) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date2 = df.format(date);
        String mealtype2 = mealType.name();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String id = user.id().getNumber();
        Query query = entityManager().createQuery("SELECT  b FROM Booking b, Meal m WHERE b.meal.id=m.id and b.meal.mealType=:mealtype1 and b.meal.date=:date1 and b.bookingState=:state and b.user.mecanographicNumber.number=:userid");
        query.setParameter("mealtype1", mealType);
        query.setParameter("date1", calendar);
        query.setParameter("state", state);
        query.setParameter("userid", id);
        return query.getResultList();
//        return (Booking) query.getSingleResult();
    }

    @Override
    public Iterable<Booking> getBookingListForNextDays(Calendar dateInit, Calendar dateEnd, CafeteriaUser user) {
        final String id = user.id().getNumber();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String d1 = df.format(dateInit.getTime());
        String d2 = df.format(dateEnd.getTime());
        Query b = entityManager().createQuery("SELECT b FROM Booking b, Meal m Where b.user.mecanographicNumber.number=:id AND b.meal.id=m.id AND b.meal.date>=:d1 AND b.meal.date<=:d2 AND b.bookingState='Reserved'");

        b.setParameter("d1", dateInit);
        b.setParameter("d2", dateEnd);
        b.setParameter("id", id);
        Iterable<Booking> it = b.getResultList();
        List<Booking> bookings = new ArrayList<>();
        for (Booking booking : it) {
            bookings.add(booking);
        }
        return bookings;
    }

    @Override
    public Booking showNextBookedMealFromUser(CafeteriaUser user) {
        final String id = user.id().getNumber();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String d1 = df.format(c.getTime());

        Query createQuery = entityManager().createQuery("SELECT b FROM Booking b, Meal m Where b.user.mecanographicNumber.number=:id AND b.meal.id=m.id AND b.bookingState='Reserved' AND b.meal.date>=:d1 ORDER BY m.date ");
        createQuery.setParameter("id", id);
        createQuery.setParameter("d1", c);
        return createQuery.getResultList().size() > 0 ? (Booking) createQuery.getResultList().get(0) : null;
    }

    @Override
    public Booking getBookingByMealAndUser(Meal meal, CafeteriaUser cafetariaUser) {
        Query b = entityManager().createQuery("SELECT b FROM Booking b Where b.meal=:meal AND b.user=:user");
        b.setParameter("meal", meal);
        b.setParameter("user", cafetariaUser);
        return b.getResultList().size() > 0 ? (Booking) b.getResultList().get(0) : null;
    }

    @Override
    public int getCaloricIntakeFromAllDeliveredBookings(Calendar dateInit, Calendar dateEnd, CafeteriaUser user) {
        try {
            final String id = user.id().getNumber();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String d1 = df.format(dateInit.getTime());
            String d2 = df.format(dateEnd.getTime());
            Query b = entityManager().createQuery("SELECT SUM(b.meal.dish.nutricionalInfo.calories) FROM Booking b, Meal m, Dish d Where b.user.mecanographicNumber.number=:id AND b.meal.id=m.id AND b.meal.dish.name=d.name AND b.meal.date>=:d1 AND b.meal.date<=:d2 AND b.bookingState='Delivered'");

            b.setParameter("d1", dateInit);
            b.setParameter("d2", dateEnd);
            b.setParameter("id", id);

            return toIntExact((long) b.getSingleResult());
        } catch (Exception exp) {
            return 0;
        }
    }
    
    
    @Override
    public int getSaltIntakeFromAllDeliveredBookings(Calendar dateInit, Calendar dateEnd, CafeteriaUser user) {
        try {
            final String id = user.id().getNumber();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String d1 = df.format(dateInit.getTime());
            String d2 = df.format(dateEnd.getTime());
            Query b = entityManager().createQuery("SELECT SUM(b.meal.dish.nutricionalInfo.salt) FROM Booking b, Meal m, Dish d Where b.user.mecanographicNumber.number=:id AND b.meal.id=m.id AND b.meal.dish.name=d.name AND b.meal.date>=:d1 AND b.meal.date<=:d2 AND b.bookingState='Delivered'");

            b.setParameter("d1", dateInit);
            b.setParameter("d2", dateEnd);
            b.setParameter("id", id);

            return toIntExact((long) b.getSingleResult());
        } catch (Exception exp) {
            return 0;
        }
    }

    //João Santos (1150639)
    @Override
    public List<Booking> findAllBookingByUser(CafeteriaUser user) {
        Query b = entityManager().createQuery("SELECT b FROM Booking b Where b.user=:user");
        b.setParameter("user", user);
        Iterable<Booking> it = b.getResultList();
        List<Booking> bookings = new ArrayList<>();
        for (Booking booking : it) {
            Date cal = Calendar.getInstance().getTime();
            if (booking.meal().date().getTime().after(cal) && booking.state().equals( Booking.BookingState.Reserved)){
                bookings.add(booking);
            }
        }
        return bookings;
    }

}
