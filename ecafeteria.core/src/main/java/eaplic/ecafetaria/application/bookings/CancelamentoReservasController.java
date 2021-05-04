/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eaplic.ecafetaria.application.bookings;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.Movements;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MovementsRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author João Santos <1150639@isep.ipp.pt>
 */
public class CancelamentoReservasController implements Controller {

    private final TransactionalContext txCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(txCtx);
    private final MovementsRepository movementsUserRepository = PersistenceContext.repositories().cardMovements(txCtx);
    private final CafeteriaUserRepository cafetariaUserRepository = PersistenceContext.repositories().cafeteriaUsers();
    private final CafeteriaUser user = cafetariaUserRepository.findByUsername(AuthorizationService.session().authenticatedUser().username()).get();

    public CafeteriaUser getUserByNumber() {
        Optional<CafeteriaUser> user2 = cafeteriaUserRepository.findByMecanographicNumber(user.mecanographicNumber());
        CafeteriaUser userFound = user2.get();
        return userFound;
    }

    public void cardChargement(Booking r, CafeteriaUser cUser, float amount, Calendar date)
            throws Movements.NegativeAmountException, DataConcurrencyException, DataIntegrityViolationException {
        Date dateobj = new Date();
        if (r.meal().mealType() == MealType.LUNCH) {
            if (dateobj.getHours() > 10 && r.meal().date().getTime().getDay() == dateobj.getDay() && r.meal().date().getTime().getMonth() == dateobj.getMonth()) {
                txCtx.beginTransaction();
                Movements newMovement = new Movements(cUser.mecanographicNumber(), (amount / 2), "CanceledBooking", date);
                movementsUserRepository.save(newMovement);
                cUser.addCredits(amount / 2);
                txCtx.commit();
                return;
            }
        }
        if (r.meal().mealType() == MealType.DINNER) {
            if (dateobj.getHours() > 16 && r.meal().date().getTime() == dateobj && r.meal().date().getTime().getMonth() == dateobj.getMonth()) {
                txCtx.beginTransaction();
                Movements newMovement = new Movements(cUser.mecanographicNumber(), (amount / 2), "CanceledBooking", date);
                movementsUserRepository.save(newMovement);
                cUser.addCredits(amount / 2);
                txCtx.commit();
                return;
            }
        }
        txCtx.beginTransaction();
        Movements newMovement = new Movements(cUser.mecanographicNumber(), amount, "CanceledBooking", date);
        movementsUserRepository.save(newMovement);
        cUser.addCredits(amount);
        txCtx.commit();
    }

    public List<Booking> bookingsByUser() {
        return (List<Booking>) PersistenceContext.repositories().bookings().findAllBookingByUser(user);
    }

    public void setBooking(Booking r) {
        r.changeState(Booking.BookingState.Canceled);
    }

    public void cancelBooking(Booking r) throws DataConcurrencyException, DataIntegrityViolationException {
        r.bookingCanceled();
        PersistenceContext.repositories().bookings().save(r);
    }
}
