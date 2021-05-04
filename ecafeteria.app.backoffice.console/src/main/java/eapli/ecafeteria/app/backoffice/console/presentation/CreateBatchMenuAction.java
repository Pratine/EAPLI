/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation;

import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.ListMealsByBatchUI;
import eapli.framework.actions.Action;

/**
 *
 * @author Guilherme
 */
public class CreateBatchMenuAction implements Action {

    public CreateBatchMenuAction() {
    }

    @Override
    public boolean execute() {
        return new ListMealsByBatchUI().show();
    }
    
}
