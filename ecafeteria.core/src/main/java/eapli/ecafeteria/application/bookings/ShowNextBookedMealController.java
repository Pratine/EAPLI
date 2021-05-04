/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.bookings;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author pedro
 */
public class ShowNextBookedMealController implements Controller {
    
    private final CafeteriaUserRepository cafetariaUserRepository = PersistenceContext.repositories().cafeteriaUsers();
    
    private CafeteriaUser user;
    
    private final BookingRepository bookingRepository = PersistenceContext.repositories().bookings();
    
    public Booking showNextBookedMeal(){
        return bookingRepository.showNextBookedMealFromUser(user);
    }
    
    public void getLoggedUser() {
        user = cafetariaUserRepository.findByUsername(AuthorizationService.session().authenticatedUser().username()).get();
    }
}