/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eaplic.ecafetaria.application.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Gonçalo
 */
public class ConsultCaloricIntakeController implements Controller {

    private final BookingRepository bookingsRepository = PersistenceContext.repositories().bookings();
    private final CafeteriaUserRepository cafetariaUserRepository = PersistenceContext.repositories().cafeteriaUsers();
    private Calendar dateInit;
    private Calendar dateEnd;    
    private CafeteriaUser user;

    public ConsultCaloricIntakeController() {

    }

    public boolean insertDateInit(Calendar date) {
        if (date.getTime().compareTo(new Date()) <= 0) {
            dateInit = date;
            return true;
        }
        return false;
    }
    
    public boolean insertDateEnd(Calendar date) {
        if (date != null) {
            dateEnd = date;
            return true;
        }
        return false;
    }


    public void getLoggedUser() {
        user = cafetariaUserRepository.findByUsername(AuthorizationService.session().authenticatedUser().username()).get();
    }

    public int getCaloricIntakeFromAllDeliveredBooking() {   
        return bookingsRepository.getCaloricIntakeFromAllDeliveredBookings(dateInit, dateEnd, user);
    }
    
}
