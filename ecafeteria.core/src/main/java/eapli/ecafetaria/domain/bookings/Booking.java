package eapli.ecafetaria.domain.bookings;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

/**
 *
 * @author 1150448
 */
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"meal_id", "user_number"}))
public class Booking implements AggregateRoot<Long>, Serializable {

    @Version
    private Long version;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    private CafeteriaUser user;

    @ManyToOne(cascade = CascadeType.MERGE)
    private Meal meal;

    //state is a reserved sql keyword
    @Enumerated(EnumType.STRING)
    private BookingState bookingState;

    public enum BookingState {
        Reserved, Delivered, Canceled, Undelivered
    }

    protected Booking() {
        //for orm only
    }

    public Booking(CafeteriaUser user, Meal meal) {
        if (user == null || meal == null) {
            throw new IllegalArgumentException();
        }
        setUser(user);
        setMeal(meal);
        bookingState = BookingState.Reserved;
    }

    public CafeteriaUser user() {
        return user;
    }

    public Meal meal() {
        return meal;
    }

    /**
     * Change the state of the booking
     *
     * @param newState a new state of the booking
     * @return true if state is changed with success
     */
    public boolean changeState(BookingState newState) {
        this.bookingState = newState;
        return true;
    }

    public BookingState state() {
        return this.bookingState;
    }

    @Override
    public String toString() {
        return "Booking{ user = " + user.id() + ", meal = " + meal + '}';
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Booking)) {
            return false;
        }

        final Booking that = (Booking) other;
        if (this == that) {
            return true;
        }
        return this.id.equals(that.id);
    }

    @Override
    public Long id() {
        return id;
    }

    public Calendar bookingDate() {
        return meal.date();
    }

    public boolean deliveryBooking() {
        if (bookingState == BookingState.Reserved) {
            bookingState = BookingState.Delivered;
            return true;
        }
        return false;
    }

    //João Santos (1150639)
    public boolean bookingUndelivered() {
        if (bookingState == BookingState.Reserved) {
            bookingState = BookingState.Undelivered;
            return true;
        }
        return false;
    }

    //João Santos (1150639)
    public boolean bookingCanceled() {
        if (bookingState == BookingState.Reserved) {
            bookingState = BookingState.Canceled;
            return true;
        }
        return false;
    }

    public void setUser(CafeteriaUser user) {
        this.user = user;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }
    
}
