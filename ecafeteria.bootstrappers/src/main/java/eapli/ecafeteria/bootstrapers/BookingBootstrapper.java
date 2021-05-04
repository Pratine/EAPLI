/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;

/**
 *
 * @author Pedro Quinta
 */
public class BookingBootstrapper implements Action {

    final private BookingRepository bookingRepository = PersistenceContext.repositories().bookings();

    @Override
    public boolean execute() {
        final MealRepository mealRepository = PersistenceContext.repositories().meals();
        final CafeteriaUserRepository userRepository = PersistenceContext.repositories().cafeteriaUsers();

        //temporary fix
        final CafeteriaUser cafeteriaUser900330 = userRepository.findByUsername(new Username("900330")).get();
        final CafeteriaUser cafeteriaUser900331 = userRepository.findByUsername(new Username("900331")).get();
        final Iterator<Meal> meals = mealRepository.findAll().iterator();

        Booking booking;
        Meal auxMeal;

        //Add bookings to all meals of 900330 and meals of 23-05-2018 to 900331
        while(meals.hasNext()) {
            auxMeal = meals.next();
            
            if(!DateTime.isSameDay(auxMeal.date(), DateTime.parseDate("27-05-2018"))){
                booking = new Booking(cafeteriaUser900330, auxMeal);
                try {
                    bookingRepository.save(booking);
                } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
                    System.out.println("Erro");
                }
            }
            
            if(DateTime.isSameDay(auxMeal.date(), DateTime.parseDate("23-05-2018"))){
                booking = new Booking(cafeteriaUser900331, auxMeal);
                booking.deliveryBooking();
                try {
                    bookingRepository.save(booking);
                } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
                    System.out.println("Erro");
                }
            }
        }
        

        return false;
    }

}
