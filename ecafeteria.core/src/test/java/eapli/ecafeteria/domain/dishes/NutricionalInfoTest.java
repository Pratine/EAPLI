/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.dishes;

import eapli.ecafeteria.dto.NutricionalInfoDTO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lights
 */
public class NutricionalInfoTest {
    
    /**
     * Test of toDTO method, of class NutricionalInfo.
     */
    @Test
    public void testToDTO() {
        System.out.println("toDTO");
        NutricionalInfo instance = new NutricionalInfo(1, 2);
        NutricionalInfoDTO expResult = new NutricionalInfoDTO(1, 2);
        NutricionalInfoDTO result = instance.toDTO();
        assertEquals(expResult, result);
    }
    
}
