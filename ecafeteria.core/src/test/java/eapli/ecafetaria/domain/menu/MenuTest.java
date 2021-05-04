
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.menu;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.framework.domain.time.DateInterval;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author João Santos <1150639@isep.ipp.pt>
 */
public class MenuTest {

    //Calendar date1 = DateTime.newCalendar(2018, 5, 13);
    
    private final String tittle = "Menu1";
    Calendar date1 = DateTime.newCalendar(2018, 4, 14);
    Calendar date2 = DateTime.newCalendar(2018, 4, 18);
    private Menu menu1;
    private Meal meal = new Meal();
    private final DateInterval date = new DateInterval(date1);

    public MenuTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        menu1 = new Menu("Menu Test", new DateInterval(date1, date2));
        menu1.addMeal(meal);
    }

    @After
    public void tearDown() {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMenuTittleCantBeEmpty() {
        System.out.println("must have an tittle on the menu");
        Menu instance = new Menu("", date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMenuTittleCantBeNull() {
        System.out.println("must have an tittle on the menu");
        Menu instance = new Menu(null, date);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDateCantBeNull() {
        System.out.println("must have an valide date on the menu");
        Menu instance = new Menu(tittle, null);
    }

//    @Test(expected = IllegalArgumentException.class)
//    public void testAddMeal() {
//        System.out.println("meal to add can´t be null");
//        Menu instance = new Menu(tittle, date);
//        instance.addMeal(null);
//    }
    
     /**
     * Test of setMeals method, of class Menu.
     */
    @Test
    public void testCopyMenu() {
        System.out.println("Copy Menu Test");
        
        Menu newMenu = new Menu(menu1);
        String expResult = "Menu Test - copy";
        String result = newMenu.getTitle();
        
        assertEquals(expResult, result);
        System.out.println("Test Copy 1/3 success");
        
        Calendar start = menu1.getDate().start();
        Calendar start2 = newMenu.getDate().start();
        
        boolean expResult2 = start.equals(start2);
        boolean result2 = true;
        
        assertEquals(expResult2, result2);
        System.out.println("Test Copy 2/3 success");
        
        Calendar end = menu1.getDate().end();
        Calendar end2 = newMenu.getDate().end();
        
        boolean expResult3 = end.equals(end2);
        boolean result3 = true;
        
        assertEquals(expResult3, result3);
        System.out.println("Test Copy 3/3 success");
        
        int size = menu1.getMeals().size();
        int size2 = newMenu.getMeals().size();
        
        assertEquals(size, size2);
        System.out.println("Test Array");
        
        
    }

}
