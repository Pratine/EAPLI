/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.domain.meals;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Guilherme
 */
public class MealExecutionTest {
    
    public MealExecutionTest() {
    }

    /**
     * Test of sameAs method, of class MealExecution.
     */
    @Test
    public void testSameAs() {
        System.out.println("sameAs");
        MealExecution instance = new MealExecution();
        boolean expResult = true;
        boolean result = instance.sameAs(instance);
        assertEquals(expResult, result);
    }

    /**
     * Test of cookedQty method, of class MealExecution.
     */
    @Test
    public void testCookedQty() {
        System.out.println("cookedQty");
        MealExecution instance = new MealExecution();
        int expResult = 0;
        int result = instance.cookedQty();
        assertEquals(expResult, result);
    }

    /**
     * Test of deliveredQty method, of class MealExecution.
     */
    @Test
    public void testDeliveredQty() {
        System.out.println("deliveredQty");
        MealExecution instance = new MealExecution();
        int expResult = 0;
        int result = instance.deliveredQty();
        assertEquals(expResult, result);
    }

    /**
     * Test of leftoverQty method, of class MealExecution.
     */
    @Test
    public void testLeftoverQty() {
        System.out.println("leftoverQty");
        MealExecution instance = new MealExecution();
        int expResult = 0;
        int result = instance.leftoverQty();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeLeftoverQty method, of class MealExecution.
     */
    @Test
    public void testChangeLeftoverQty() {
        System.out.println("changeLeftoverQty");
        int qty = 20;
        MealExecution instance = new MealExecution();
        instance.changeLeftoverQty(qty);
    }

    /**
     * Test of equals method, of class MealExecution.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new MealExecution();
        MealExecution instance = new MealExecution();
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
}
