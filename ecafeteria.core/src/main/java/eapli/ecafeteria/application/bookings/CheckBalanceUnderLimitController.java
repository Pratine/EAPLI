package eapli.ecafeteria.application.bookings;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

/**
 *
 * @author PC
 */
public class CheckBalanceUnderLimitController implements Controller, Observer {

    private final TransactionalContext txCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(txCtx);
    private boolean isBelow = false;

    public boolean checkBalanceUnderLimit(Username username) {
        Optional<CafeteriaUser> userOpt = cafeteriaUserRepository.findByUsername(username);
        CafeteriaUser user;
        try {
            user = userOpt.get();
        } catch (NoSuchElementException ex) {
            return false;
        }

        if (user.getBalance() < user.getBalanceLimit()) {
            return true;
        }
        return false;
    }

    public boolean isBelowValue() {
        return isBelow;
    }

    @Override
    public void update(Observable o, Object arg) {
        //avisar UI
        isBelow = true;
    }
}
