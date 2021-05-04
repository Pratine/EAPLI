/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafetaria.domain.menu.Menu;
import eapli.ecafeteria.application.kitchen.CopyMenuController;
import eapli.framework.application.Controller;
import eapli.framework.domain.time.DateInterval;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Joao Paulo
 */
public class CopyMenuUI extends AbstractUI  {
    
    private final CopyMenuController controller = new CopyMenuController();

    protected Controller controller() {
        return this.controller;
    }
    
    @Override
    protected boolean doShow() {
        
        
        List<eapli.ecafetaria.domain.menu.Menu> menuList = (List<eapli.ecafetaria.domain.menu.Menu>) controller.findAllMenus();
        if (menuList.isEmpty()) {
            System.out.println("There are no menus");
            return false;
        }
        SelectWidget<eapli.ecafetaria.domain.menu.Menu> menuSelector = new SelectWidget<>(
                "Select menu", menuList);
        menuSelector.show();
        eapli.ecafetaria.domain.menu.Menu selectedMenu = menuSelector.selectedElement();
        if (selectedMenu == null) {
            return false;
        }
        
                //guardar
        System.out.println("\nDo you want to copy this Menu: ´" + selectedMenu.toString() +"?\n1. Yes\n0. No");
        final int confirmation = Console.readOption(1, 1, 0);
        if (confirmation == 1) {
            try {
                Calendar startDate = Console.readCalendar("Choose start date (dd-mm-yyyy)");
                Calendar endDate = Console.readCalendar("Choose end date (dd-mm-yyyy)");
                if(endDate.before(startDate)){
                    System.out.println("Date order not valid\n");
                    return false;
                }
                Menu newMenu = controller.copyMenu(selectedMenu);
                DateInterval dt = new DateInterval(startDate,endDate);
                
                if(!controller.isDateFree(dt)){
                    System.out.println("Invalid - There is another Menu that matches this date\n");
                    return false;
                }
                controller.setDate(newMenu, startDate, endDate);
                if (controller.saveMenu(newMenu) == null) {
                    System.out.println("\nERROR: the data wasn't saved");
                    return false;
                }else{
                    System.out.println("Menu saved with success\n");
                }

            } catch (DataIntegrityViolationException ex) {
                System.out.println("\nInvalid Data");
                return false;
            } catch (DataConcurrencyException ex) {
                System.out.println("\nSimultaneous access of the same data by many users. ");
                return false;
            }
        } else {
            System.out.println("\nCanceled.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Copy Menu";
    }
    
}
