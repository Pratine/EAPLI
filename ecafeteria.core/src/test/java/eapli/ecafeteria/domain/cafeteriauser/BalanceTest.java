/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author VitorCardoso(1161895
 */
public class BalanceTest {
    
    public BalanceTest() {
    }
    
    @Test
    public void testCreateBalance() {
        System.out.println("CreateBalance");
        Balance instance = null;
        float expResult = 0;
        float result = 0;
        try{
            instance = new Balance();
            result = instance.getBalance();
        }catch(IllegalArgumentException e){
            result = 0;
        }
        assertEquals(expResult, result, 0);
    }
    
}
