/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.authz;

import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.DeactivationCauseType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.Console;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fernando
 */
public class DeactivateUserController implements Controller {

    private final UserRepository userRepository = PersistenceContext.repositories().users();

    public Iterable<SystemUser> activeUsers() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        return this.userRepository.findAllActive();
    }

    public void setDeactivationCause(SystemUser user,String cause) {
        user.setCause(cause);
    }    
    
    public void setDeactivateComment(SystemUser user) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        String comment = Console.readLine("Comment:");
        if(comment.isEmpty()){
            comment = null;
        }
        user.setDeactivationComment(comment); 
    }
    
    public SystemUser deactivateUser(SystemUser user) throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);
        
        user.deactivate(DateTime.now());
        return this.userRepository.save(user);
    }
}
