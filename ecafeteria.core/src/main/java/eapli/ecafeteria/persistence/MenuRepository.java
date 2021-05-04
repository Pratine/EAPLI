/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafetaria.domain.menu.Menu;
import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

/**
 *
 * @author João Santos <1150639@isep.ipp.pt>
 */
public interface MenuRepository extends DataRepository<Menu, Long> {

    Iterable<Menu> thisWeekMenus();
    Menu getMenuPlanByDate(Calendar date);
}
