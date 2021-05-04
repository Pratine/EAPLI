/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.sales;

import eapli.ecafeteria.domain.Movements;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MovementsRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.Calendar;
import java.util.Optional;

/**
 *
 * @author VitorCardoso(1161895
 */
public class CardChargementController implements Controller {

    private final TransactionalContext txCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(txCtx);
    private final MovementsRepository movementsUserRepository = PersistenceContext.repositories().cardMovements(txCtx);

    public CafeteriaUser getUserByNumber(MecanographicNumber mecanographicNumber) {
        Optional<CafeteriaUser> user = cafeteriaUserRepository.findByMecanographicNumber(mecanographicNumber);
        CafeteriaUser userFound = user.get();
        return userFound;
    }

    public boolean cardChargement(CafeteriaUser cUser, float amount, Calendar date) 
            throws Movements.NegativeAmountException, DataConcurrencyException, DataIntegrityViolationException {
        txCtx.beginTransaction();
        Movements newMovement = new Movements(cUser.mecanographicNumber(), amount, "CardCharge", date);
        movementsUserRepository.save(newMovement);
        cUser.addCredits(amount);
        txCtx.commit();
        return true;
    }
}
