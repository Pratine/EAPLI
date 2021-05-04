/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Calendar;

/**
 *
 * @author Pedro Quinta
 */
public class ShowBookingsByDayController implements Controller {
    
    private final BookingRepository bookingRepository = PersistenceContext.repositories().bookings();
    
    public Iterable<Booking> bookingsByDay(Calendar date) {
        return bookingRepository.findAllBookingsByDay(date);
    }
    
}
