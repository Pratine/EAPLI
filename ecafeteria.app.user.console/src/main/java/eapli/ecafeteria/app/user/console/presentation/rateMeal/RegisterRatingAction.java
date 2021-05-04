/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.rateMeal;

import eapli.framework.actions.Action;

/**
 *
 * @author Utilizador
 */
public class RegisterRatingAction implements Action {

    @Override
    public boolean execute() {
        return new RateMealUI().show();
    }
    
}
