/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.app.user.console.presentation.nutritionalProfile;

import eapli.framework.actions.Action;

/**
 *
 * @author Pedro Quinta
 */
public class EditAllergensListAction implements Action{

    @Override
    public boolean execute() {
        return new EditAllergensListUI().show();
    }
    
}
