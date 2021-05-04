package eapli.ecafeteria.app.pos.console.presentation;

import eapli.cafeteria.app.common.console.presentation.authz.LogoutAction;
import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.pos.ClosePOSController;
import eapli.ecafeteria.domain.pos.DeliveryWorkSession;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1161016
 */
public class ClosePOSUI extends AbstractUI {

    private final ClosePOSController controller = new ClosePOSController();

    protected Controller controller() {
        return this.controller;
    }

    public ClosePOSUI() {

    }

    @Override
    protected boolean doShow() {
        int ans = -1;
        ArrayList<DeliveryWorkSession> list = controller.openedPOSsSessions();

        while (ans > list.size() || ans < 0) {

            System.out.println("POS's:");

            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + "- " + list.get(i).pos().number());
            }

            System.out.println("0 - exit");

            ans = Console.readInteger("What POS do you wish to close?");

            int idx = ans - 1;
            if (ans == 0) {
                break;
            } else if (idx < list.size() && idx >= 0) {
                try {
                    controller.closePOS(list.get(idx));
                    System.out.println("Closed POS: " + list.get(idx).pos().number());
                    
                    /*mostrar sumario dos pratos entregues*/
                    System.out.println("Entregas:");
                    Map<Meal,Integer> delivered = controller.deliveredMeals(list.get(idx));
                    for(Entry<Meal,Integer>entry : delivered.entrySet()){
                        System.out.println("Meal: "+entry.getKey().dishName()+" - "+entry.getValue()+" entregas.");
                    }                    
                    
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    Logger.getLogger(ClosePOSUI.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
            } else {
                System.out.println("error - invalid input!");
                return false;
            }

        }
        if(!/*! ->enquanto a funcionalidade não esta implementada, para darmos logout*/new LogoutAction().execute()){
            System.out.println("LOGGOUT SUCCESSFUL!");
            return true;
        }else{
            System.out.println("Error logging out!");
            //return false;
        }
        return false;

    }

    @Override
    public String headline() {
        return "Close POS";
    }

}
