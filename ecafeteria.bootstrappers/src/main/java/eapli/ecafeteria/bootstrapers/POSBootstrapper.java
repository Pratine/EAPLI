
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.pos.RegisterPOSController;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import org.jboss.logging.Logger;

/**
 *
 * @author PC
 */
public class POSBootstrapper implements Action {

    @Override
    public boolean execute() {
        buildPOS(1);
        buildPOS(2);
        buildPOS(3);
        buildPOS(4);
        return true;
    }
    
    private void buildPOS(int nCaixa){
        final RegisterPOSController controller = new RegisterPOSController();
        try{
            controller.registerPOS(nCaixa);
        }catch(DataIntegrityViolationException | DataConcurrencyException e){
            //ignorng exception. assuming it is just a primary key violation
            //due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName()).info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}
