/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.bookings;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.UserSession;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.Profile;
import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Pedro Quinta
 */
public class EditProfileController implements Controller{
    
    private final TransactionalContext TxCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final CafeteriaUserRepository repository = PersistenceContext.repositories().cafeteriaUsers(TxCtx);

    public Profile getActualNutritionalProfile() {
        
        CafeteriaUser cafeteriaUser = repository.findByUsername(AuthorizationService.session().authenticatedUser().username()).get();

        return cafeteriaUser.profile();
    }

    public CafeteriaUser changeNutritionalProfile(int maxCaloriesPerMeal, int maxCaloriesPerWeek, int maxSaltPerMeal, 
            int maxSaltPerWeek) throws DataConcurrencyException, DataIntegrityViolationException {

        TxCtx.beginTransaction();
        CafeteriaUser cafeteriaUser = repository.findByUsername(AuthorizationService.session().authenticatedUser().username()).get();

        cafeteriaUser.changeProfile(maxCaloriesPerMeal, maxSaltPerMeal, maxCaloriesPerWeek, maxSaltPerWeek);
        repository.save(cafeteriaUser);
        TxCtx.commit();
        return repository.save(cafeteriaUser);
    }
    
    public List<Allergen> getUserAllergens() {
        CafeteriaUser cafeUser = repository.findByUsername(AuthorizationService.session().authenticatedUser().username()).get();
        return cafeUser.profile().allergens();
    }
    
    public CafeteriaUser addNewAllergen(Allergen allergen) throws DataConcurrencyException, DataIntegrityViolationException {
       TxCtx.beginTransaction();
       CafeteriaUser cafeUser = repository.findByUsername(AuthorizationService.session().authenticatedUser().username()).get();
       List<Allergen> listAller = cafeUser.profile().allergens();
        for(Allergen userAll : listAller){
            if (userAll.equals(allergen)){
                System.out.println("The user already had the allergen");
                TxCtx.commit();
                return null;
            }
        } 
        listAller.add(allergen);
        cafeUser.profile().changeAllergensList(listAller); 
        repository.save(cafeUser);
        TxCtx.commit();
        return repository.save(cafeUser);
    }

    public CafeteriaUser deleteAllergen(Allergen userAllergen) throws DataConcurrencyException, DataIntegrityViolationException {
       TxCtx.beginTransaction();
       CafeteriaUser cafeUser = repository.findByUsername(AuthorizationService.session().authenticatedUser().username()).get();
       List<Allergen> listAller = cafeUser.profile().allergens();
        for(Allergen userAll : listAller){
            if (userAll.equals(userAllergen)){
//                return listAller.remove(userAllergen);
                  listAller.remove(userAllergen);
                  cafeUser.profile().changeAllergensList(listAller);
                  repository.save(cafeUser);
//                  TxCtx.commit();
//                  return repository.save(cafeUser);
            }
        } 
        //System.out.println("The user hadn't the allergen");
        TxCtx.commit();
        return cafeUser;
    }
}
