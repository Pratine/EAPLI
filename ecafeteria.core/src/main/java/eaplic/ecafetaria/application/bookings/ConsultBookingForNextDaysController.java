//<<<<<<< HEAD
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eaplic.ecafetaria.application.bookings;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Gonçalo
 */
public class ConsultBookingForNextDaysController implements Controller {

    private final BookingRepository bookingsRepository = PersistenceContext.repositories().bookings();
    private final CafeteriaUserRepository cafetariaUserRepository = PersistenceContext.repositories().cafeteriaUsers();
    private int nrDays;
    private CafeteriaUser user;

    public ConsultBookingForNextDaysController() {

    }

    public boolean insertNrOfDays(int days) {
        if (days >= 0) {
            nrDays = days;
            return true;
        }
        return false;
    }

    public void getLoggedUser() {
        user = cafetariaUserRepository.findByUsername(AuthorizationService.session().authenticatedUser().username()).get();
    }

    public List<Booking> getBookingListForNextDays() {
        Calendar dateInit = Calendar.getInstance();
        Calendar dateEnd = Calendar.getInstance();
        dateEnd.add(Calendar.DATE, nrDays);
        
        List<Booking> list = (List)bookingsRepository.getBookingListForNextDays(dateInit, dateEnd, user);
        return list;
    }
}
