/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafetaria.domain.meals.MealPlan;
import eapli.ecafetaria.domain.menu.Menu;
import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

/**
 *
 * @author 1151360 - Joao Alves
 */
public class JpaMenuPlanRepository extends CafeteriaJpaRepositoryBase<MenuPlan, Long> implements MenuPlanRepository {

    @Override
    public void updateMenuPlan(MenuPlan mp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MenuPlan getMenuPlanByDate(Calendar date) {
        Query query = entityManager().createQuery("SELECT m FROM MenuPlan m", MenuPlan.class);
        List<MenuPlan> menuPlans = query.getResultList();
        
        
        for (MenuPlan mp : menuPlans) {
            if (DateTime.isAfter(date, mp.menu().getDate().start())
                    && DateTime.isBefore(date, mp.menu().getDate().end())) {
                return mp;
            }

            if (DateTime.isSameDay(date, mp.menu().getDate().start())
                    || DateTime.isSameDay(date, mp.menu().getDate().end())) {
                return mp;
            }
        }
        return null;
    }


}

