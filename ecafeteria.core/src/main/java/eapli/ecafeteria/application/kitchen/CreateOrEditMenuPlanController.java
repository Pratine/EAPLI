/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.meals.MealPlan;
import eapli.ecafetaria.domain.menu.Menu;
import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Joao Paulo
 */
public class CreateOrEditMenuPlanController  implements  Controller {
    private TransactionalContext TxCtx;
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private final MealPlanRepository mealPlanRepository = PersistenceContext.repositories().mealPlans();
    private final MenuPlanRepository menuPlanRepository = PersistenceContext.repositories().menuPlans(TxCtx);
    
    
    public Iterable<Menu> findAllMenus() {
        return menuRepository.findAll();
    }
    
    private Iterable<MenuPlan> findMenuPlans(){
        return menuPlanRepository.findAll();
    }
    
    public Iterable<MenuPlan> findOpenMenuPlans(){
        List<MenuPlan> plans = (List<MenuPlan>) findMenuPlans();
        List<MenuPlan> plansResult = new ArrayList<>();
        for (MenuPlan plan : plans) {
            if(!plan.isClosed()){
                plansResult.add(plan);
            }
        }
        
        return plansResult;
    }
    
    public MealPlan saveMealPlan(MealPlan mealPlan) throws DataConcurrencyException, DataIntegrityViolationException{
        return mealPlanRepository.save(mealPlan);
    }
    
    public MenuPlan saveMenuPlan(MenuPlan menuPlan) throws DataConcurrencyException, DataIntegrityViolationException{
        return menuPlanRepository.save(menuPlan);
    }
    
    public void setQuantity(MealPlan p,int qt){
        p.setQt(qt);
    }

    
    
}
