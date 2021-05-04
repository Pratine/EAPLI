/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafetaria.domain.menu.Menu;
import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author João Santos <1150639@isep.ipp.pt>
 */
public class JpaMenuRepository extends CafeteriaJpaRepositoryBase<Menu, Long> implements MenuRepository {

    @Override
    public Iterable<Menu> thisWeekMenus() {
        List<Menu> thisWeekMenus = new ArrayList();

        
        int noOfDays = 14;
        Calendar cal = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_YEAR, noOfDays);
        for (Menu menu : this.findAll()) {
            if (menu.getDate().start().getTime().after(cal2.getTime()) && menu.getDate().start().getTime().before(cal.getTime())) {
                thisWeekMenus.add(menu);
            }
        }
        return thisWeekMenus;
    }

    @Override
    public Menu getMenuPlanByDate(Calendar date) {
        Query query = entityManager().createQuery("SELECT m FROM Menu m", Menu.class);
        List<Menu> menus = query.getResultList();         
        
        for (Menu menu : menus) {
            if (DateTime.isAfter(date, menu.getDate().start())
                    && DateTime.isBefore(date, menu.getDate().end())) {
                return menu;
            }

            if (DateTime.isSameDay(date, menu.getDate().start())
                    || DateTime.isSameDay(date, menu.getDate().end())) {
                return menu;
            }
        }
        return null;
    }
    
}
