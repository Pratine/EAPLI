/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Francisco
 */
public class ViewBookingsUndeliveredController {
    
    private final BookingRepository repository = PersistenceContext.repositories().bookings();
    
    public int viewBookingsUndelivered() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SALE);
        
        List<Booking> listBooking = this.repository.findAllBookingsUndelivered();
        
        return listBooking.size();
    }
}
