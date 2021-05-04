/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.menu;

import eapli.framework.domain.time.DateInterval;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author esoft
 */
public class MenuPlanTest {
    
    public MenuPlanTest() {
    }

    /**
     * Test of menu method, of class MenuPlan.
     */
    @Test
    public void testMenu() {
        System.out.println("menu");
        Menu menu = new Menu();
        MenuPlan instance = new MenuPlan(menu);        
        Menu expResult = instance.menu();
        Menu result = menu;
        assertEquals(expResult, menu);
    }

    /**
     * Test of closeMenuPlan method, of class MenuPlan.
     */
    @Test
    public void testCloseMenuPlan() {
        System.out.println("closeMenuPlan");
        MenuPlan instance = new MenuPlan();
        instance.closeMenuPlan();
        boolean expResult = true;
        boolean result = false;
        if(instance.getState() == State.CLOSED){
            result = true;
        }        
        assertEquals(result, expResult);
        
    }
    
}
