/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafetaria.domain.menu.MenuPlan;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;


/**
 *
 * @author João Alves - 1151360
 */
public interface MenuPlanRepository extends DataRepository<MenuPlan, Long> {
     
    /**
     * Updates a MenuPlan 
     * @param mP - MenuPlan that will be uodated
     */
    void updateMenuPlan (MenuPlan mp);
    
    MenuPlan getMenuPlanByDate(Calendar date);
    
}
