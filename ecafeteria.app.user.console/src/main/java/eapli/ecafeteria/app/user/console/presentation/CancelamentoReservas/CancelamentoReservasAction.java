/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eapli.ecafeteria.app.user.console.presentation.CancelamentoReservas;

import eapli.framework.actions.Action;

/**
 *
 * @author João Santos <1150639@isep.ipp.pt>
 */
public class CancelamentoReservasAction implements Action{

    @Override
    public boolean execute() {
        return new CancelamentoReservasUI().show();
    }

}
