/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author VitorCardoso(1161895
 */
public class AccountTest {

    public AccountTest() {
    }

    /**
     * Test of getBalanceLimit method, of class Account.
     */
    @Test
    public void testCreateAccount() {
        System.out.println("Create Account");
        Account instance = null;
        float expResult = 0;
        float result = 0;
        try {
            instance = new Account(-5);
            result = instance.getBalance();
        } catch (IllegalArgumentException e) {
            result = 0;
        }
        assertEquals(expResult, result, 0);
    }
    
    /**
     * Test of getBalanceLimit method, of class Account.
     */
    @Test
    public void testRemoveCredits() {
        System.out.println("Remove Account");
        Account instance = new Account(200);
        instance.removeCredits(20);
        assertEquals(instance.getBalance(), 180, 0);
    }

}
