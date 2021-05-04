package eapli.ecafetaria.domain.bookings;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lights
 */
public class BookingTest {
    
    /**
     * Test of Booking Constructor.
     */
    @Test
    public void testBooking() {
        try{
            Booking instance = new Booking(null, null);
            assertTrue("should have thrown exception", false);
        } catch(Exception e) {
            assertTrue(true);
        }
    }
    
}
