package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author mcn
 */
public class RegisterPOSController implements Controller {

    private final POSRepository repository = PersistenceContext.repositories().pos();

    public POS registerPOS(Integer number)
            throws DataIntegrityViolationException, DataConcurrencyException {
        //AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SALE);

        final POS pos = new POS(number);
        return this.repository.save(pos);
    }
}
