/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.consultcaloricintake;

import eapli.framework.actions.Action;

/**
 *
 * @author Gonçalo
 */
public class ConsultCaloricIntakeaAction  implements Action {

    @Override
    public boolean execute() {
        return new ConsultCaloricIntakeUI().show();
    }
}
