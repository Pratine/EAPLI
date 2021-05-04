package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafetaria.domain.bookings.BalanceUnderLimitEvent;
import eapli.ecafeteria.application.bookings.CheckBalanceUnderLimitController;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author PC
 */
public class BalanceLimitCheckWatchdog implements Observer {
    
    private final CheckBalanceUnderLimitController checkBalanceController= new CheckBalanceUnderLimitController();
    private static BalanceLimitCheckWatchdog blcw = new BalanceLimitCheckWatchdog();
    
    private BalanceLimitCheckWatchdog() {
    }
    
    public static BalanceLimitCheckWatchdog getInstance() {
        return blcw;
    }
    
    public CheckBalanceUnderLimitController controller(){
        return checkBalanceController;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(checkBalanceController.checkBalanceUnderLimit(eapli.ecafeteria.application.authz.AuthorizationService.session().authenticatedUser().id())){
            //avisar ui
            //criar evento de estar under limit, dar .setchanged a esse evento, e a ui "apanha" esse evento - BalanceUnderLimitEvent
            BalanceUnderLimitEvent bule = new BalanceUnderLimitEvent();
            bule.notifyObservers();
            
        }
        //ignora se balance nao estiver abaixo do limite
    }
    
    
    
}
