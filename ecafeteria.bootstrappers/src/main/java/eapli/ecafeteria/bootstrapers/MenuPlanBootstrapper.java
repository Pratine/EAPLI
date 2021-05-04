/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.menu.Menu;
import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1151360 - Joao Alves
 */
public class MenuPlanBootstrapper implements Action {

    private final TransactionalContext TxCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final MenuPlanRepository menuPlanRepository = PersistenceContext.repositories().menuPlans(TxCtx);

    @Override
    public boolean execute() {

        final MenuRepository menuRepoUnit = PersistenceContext.repositories().menus();
        final ArrayList<Menu> menus = (ArrayList<Menu>) menuRepoUnit.findAll(); //Passa meals para um array//Passa meals para um array//Passa meals para um array//Passa meals para um array

        for (Menu m : menus) {
            try {
                register(m);
            } catch (DataIntegrityViolationException ex) {
                Logger.getLogger(MenuPlanBootstrapper.class.getName()).log(Level.SEVERE, "ERROR TRYING TO REGISTER MEAL EXECUTION", ex);
            } catch (DataConcurrencyException ex) {
                Logger.getLogger(MenuPlanBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    private void register(Menu menu) throws DataConcurrencyException, DataIntegrityViolationException { //inicia as meal execution 

        TxCtx.beginTransaction();
        MenuPlan mp = new MenuPlan(menu);
        mp.closeMenuPlan();
        menuPlanRepository.save(mp);
        TxCtx.commit();
    }

}
