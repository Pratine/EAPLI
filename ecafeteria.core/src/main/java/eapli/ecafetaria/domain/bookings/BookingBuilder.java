package eapli.ecafetaria.domain.bookings;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.ddd.DomainFactory;

public class BookingBuilder implements DomainFactory<Booking> {

    private CafeteriaUser user;
    private Meal meal;

    public BookingBuilder() {
    }
    
    public BookingBuilder withCafeteriaUser(CafeteriaUser user) {
        this.user = user;
        return this;
    }

    public BookingBuilder withMeal(Meal refeicao) {
        this.meal = refeicao;
        return this;
    }

    @Override
    public Booking build() {
        return new Booking(user, meal);
    }
    
}
