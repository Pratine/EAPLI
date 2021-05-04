
package eapli.ecafetaria.domain.bookings;

import eapli.ecafeteria.domain.cafeteriauser.BalanceLimitCheckWatchdog;
import java.util.Observable;

/**
 *
 * @author PC
 */
public class BalanceUnderLimitEvent extends Observable {
    //observable
    //assinala o watchdog
    public BalanceUnderLimitEvent(){
        super();
        super.addObserver(BalanceLimitCheckWatchdog.getInstance().controller());
        super.setChanged();
    }

    @Override
    public void notifyObservers() {
        super.notifyObservers();
    }

    
}
