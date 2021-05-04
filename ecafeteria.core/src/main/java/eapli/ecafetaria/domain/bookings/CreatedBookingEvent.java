
package eapli.ecafetaria.domain.bookings;

import eapli.ecafeteria.domain.cafeteriauser.BalanceLimitCheckWatchdog;
import java.util.Observable;

/**
 *
 * @author PC
 */
public class CreatedBookingEvent extends Observable {
    //observable
    //assinala o watchdog
    public CreatedBookingEvent(){
        super();
        super.addObserver(BalanceLimitCheckWatchdog.getInstance());
        super.setChanged();
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    
}
