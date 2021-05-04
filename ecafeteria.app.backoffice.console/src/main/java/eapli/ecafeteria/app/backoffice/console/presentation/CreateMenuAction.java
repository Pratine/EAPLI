/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation;

import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.CloseMenuPlanUI;
import eapli.framework.actions.Action;

/**
 *
 * @author Joao Alves - 1151360
 */
public class CreateMenuAction implements Action {

    @Override
    public boolean execute() {
         return new CloseMenuPlanUI().show();
    }
    
}
