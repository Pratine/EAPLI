/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafetaria.domain.meals.MealPlan;
import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.time.DateInterval;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Joao Alves
 */
public class CloseMenuPlanController implements Controller {

    private final TransactionalContext TxCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final MenuPlanRepository menuPlanRep = PersistenceContext.repositories().menuPlans(TxCtx);
    private final MealPlanRepository mealPlanRep = PersistenceContext.repositories().mealPlans();

    public MenuPlan getMenuPlanByDate(Calendar date) {
        return menuPlanRep.getMenuPlanByDate(date);
    }

    public void updateMenuPlan(MenuPlan mp, int answer) throws DataConcurrencyException, DataIntegrityViolationException {
        if (answer == 1) {
            mp.closeMenuPlan();
            menuPlanRep.save(mp);
        }
    }

    public List<MealPlan> getMealPlans(MenuPlan mp) {
        List<MealPlan> mealPlans = mealPlanRep.getMeals(mp);
        return mealPlans;
    }
}
