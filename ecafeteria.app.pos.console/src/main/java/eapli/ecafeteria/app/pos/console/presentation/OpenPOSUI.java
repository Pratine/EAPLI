package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.pos.OpenPOSController;
import eapli.ecafeteria.domain.pos.DeliveryWorkSession;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class OpenPOSUI extends AbstractUI {

    private final OpenPOSController controller = new OpenPOSController();

    protected Controller controller() {
        return this.controller;
    }

    public OpenPOSUI() {

    }

    
    /**
     * 
     * @return true when wants to leave | false when not leaves
     */
    @Override
    protected boolean doShow() {
            Integer POSnumber = Console.readInteger("What POS do you want to open? ");
            Calendar workTime = Console.readCalendar("At what time will you work? (dd-MM-yyyy HH:mm:ss) (leave empty for current time)", "dd-MM-yyyy HH:mm:ss");
            try {
                DeliveryWorkSession currentSession = controller.openPOS(POSnumber, workTime);
                if (currentSession == null) {
                    System.out.println("Invalid hour or POS number! Try again");
                    return false; //try again
                } else {
                    //success
                    System.out.println("POS number "+POSnumber+" was opened successfully.\nSession started @"+workTime.getTime());
                    return false; //stay in menu
                }
            } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
                Logger.getLogger(OpenPOSUI.class.getName()).log(Level.SEVERE, null, ex);
                return true;
            }
    }

    @Override
    public String headline() {
        return "Open POS";
    }

}
