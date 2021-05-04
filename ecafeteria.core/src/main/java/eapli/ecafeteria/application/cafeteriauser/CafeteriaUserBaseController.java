/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.Optional;

/**
 *
 * @author mcn
 */
public class CafeteriaUserBaseController implements Controller {

    private final TransactionalContext txCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers(txCtx);

    public CafeteriaUser getUserByNumber(MecanographicNumber mecanographicNumber) {
        Optional<CafeteriaUser> user = cafeteriaUserRepository.findByMecanographicNumber(mecanographicNumber);
        CafeteriaUser userFound = user.get();
        return userFound;
    }
    
    public Money balance() {
        Username u = AuthorizationService.session().authenticatedUser().id();
        MecanographicNumber mecNum = new MecanographicNumber(u.toString());
        CafeteriaUser c = getUserByNumber(mecNum);
        return Money.euros(c.getBalance());
    }
}
