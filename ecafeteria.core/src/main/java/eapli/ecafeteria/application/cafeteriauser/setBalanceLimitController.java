package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 *
 * @author 1161016
 */
public class setBalanceLimitController implements Controller {

    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers();

    public boolean setBalanceLimit(Username username, int balanceLimit) {
        Optional<CafeteriaUser> userOpt = cafeteriaUserRepository.findByUsername(username);
        CafeteriaUser user;
        try {
           user = userOpt.get();
        } catch (NoSuchElementException ex) {
            return false;
        }
        
        try {
            user.setBalanceLimit(balanceLimit);
            cafeteriaUserRepository.save(user);
            return true;
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            return false;
        }
    }
}
