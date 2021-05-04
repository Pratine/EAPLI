package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.dto.ProfileDTO;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lights
 */
public class ProfileTest {

    /**
     * Test of toDTO method, of class Profile.
     */
    @Test
    public void testToDTO() {
        System.out.println("toDTO");
        Profile instance = new Profile(1, 2, 3, 4);
        ProfileDTO expResult = new ProfileDTO(1,2,3,4);
        ProfileDTO result = instance.toDTO();
        assertEquals(expResult, result);
    }
    
}
