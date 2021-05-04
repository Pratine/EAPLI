package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.List;

/**
 *
 * @author lights
 */
public class AccountBootstrapper extends UsersBootstrapperBase implements Action  {

    final CafeteriaUserRepository cafeteriaUsersRepoUnit = PersistenceContext.repositories().cafeteriaUsers();
        
    @Override
    public boolean execute() {
        
        final List<CafeteriaUser> cafeteriaUserList = (List<CafeteriaUser>) cafeteriaUsersRepoUnit.findAll();
        
        CafeteriaUser user = cafeteriaUsersRepoUnit.findByUsername(new Username("900331")).get();
        user.addCredits(200);
        try {
            cafeteriaUsersRepoUnit.save(user);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Bootstrap: Error adding credits");
            return false;
        }
        return true;
    }
    
}
