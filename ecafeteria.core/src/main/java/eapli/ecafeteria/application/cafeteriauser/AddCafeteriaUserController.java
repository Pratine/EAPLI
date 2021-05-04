/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.SystemUserBuilder;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUserBuilder;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author salva
 */
public class AddCafeteriaUserController {

    private final CafeteriaUserRepository cafeteriaUserRepository = PersistenceContext.repositories().cafeteriaUsers();

    public CafeteriaUser addCafeteriaUser(final String username, final String password, final String firstName,
            final String lastName, final String email, final String mecanographicNumber)
            throws DataIntegrityViolationException, DataConcurrencyException {
        
        final CafeteriaUserBuilder cafeteriaUserBuilder = new CafeteriaUserBuilder();

        cafeteriaUserBuilder.withMecanographicNumber(mecanographicNumber).
                withSystemUser(createSystemUser(username, password, firstName, lastName, email));
        return this.cafeteriaUserRepository.save(cafeteriaUserBuilder.build());
    }

    private SystemUser createSystemUser(final String username, final String password, final String firstName,
            final String lastName, final String email) {
        final SystemUserBuilder builder = new SystemUserBuilder();
        return builder.withUsername(username).withEmail(email).withPassword(password).
                withFirstName(firstName).withLastName(lastName).
                withRole(RoleType.CAFETERIA_USER).withCreatedOn(Calendar.getInstance()).build();
    }
}
