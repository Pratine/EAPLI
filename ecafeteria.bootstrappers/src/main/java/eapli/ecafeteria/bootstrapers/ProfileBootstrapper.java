package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.Profile;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.List;

/**
 *
 * @author 1150448
 */
public class ProfileBootstrapper implements Action {

    final CafeteriaUserRepository cafeteriaUsersRepoUnit = PersistenceContext.repositories().cafeteriaUsers();
        
    @Override
    public boolean execute() {
        
        final List<CafeteriaUser> cafeteriaUserList = (List<CafeteriaUser>) cafeteriaUsersRepoUnit.findAll();
        
        for(CafeteriaUser user : cafeteriaUserList){
            Profile p = user.profile();
            p.addAllergen(Allergen.CELERY);
            p.addAllergen(Allergen.CRUSTACEANS);
            p.addAllergen(Allergen.EGGS);
            register(user, p);
        }
        
        return true;
    }

    private void register(CafeteriaUser user, Profile p) {
        try {
            user.setProfile(p);
            cafeteriaUsersRepoUnit.save(user);
        } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
            System.out.println("Bootstrap: Error creating profiles");
        }
    }
    
}
