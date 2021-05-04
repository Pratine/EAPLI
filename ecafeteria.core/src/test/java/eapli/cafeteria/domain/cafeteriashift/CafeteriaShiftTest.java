/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.domain.cafeteriashift;

import eapli.ecafeteria.domain.pos.MealType;
import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guilherme
 */
public class CafeteriaShiftTest {
    
    public CafeteriaShiftTest() {
    }

    /**
     * Test of sameAs method, of class CafeteriaShift.
     */
    @Test
    public void testSameAs() {
        System.out.println("sameAs");
        CafeteriaShift instance = new CafeteriaShift();
        boolean expResult = true;
        boolean result = instance.sameAs(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of id method, of class CafeteriaShift.
     */
    @Test
    public void testId() {
        System.out.println("id");
        CafeteriaShift instance = new CafeteriaShift();
        Long expResult = null;
        Long result = instance.id();
        assertEquals(expResult, result);
    }

    /**
     * Test of mealType method, of class CafeteriaShift.
     */
    @Test
    public void testMealType() {
        System.out.println("mealType");
        Calendar date = Calendar.getInstance();
        CafeteriaShift instance = new CafeteriaShift(date, MealType.DINNER);
        MealType expResult = MealType.DINNER;
        MealType result = instance.mealType();
        assertEquals(expResult, result);
    }

    /**
     * Test of state method, of class CafeteriaShift.
     */
    @Test
    public void testState() {
        System.out.println("state");
        Calendar date = Calendar.getInstance();
        CafeteriaShift instance = new CafeteriaShift(date, MealType.DINNER);
        String expResult = "OPEN";
        String result = instance.state();
        assertEquals(expResult, result);
    }

    /**
     * Test of shutdownShift method, of class CafeteriaShift.
     */
    @Test
    public void testShutdownShift() {
        System.out.println("shutdownShift");
        CafeteriaShift instance = new CafeteriaShift();
        instance.shutdownShift();
    }


    /**
     * Test of equals method, of class CafeteriaShift.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new CafeteriaShift();
        CafeteriaShift instance = new CafeteriaShift();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
}
