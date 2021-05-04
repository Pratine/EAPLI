/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.dishes;

import eapli.ecafetaria.domain.menu.Menu;
import eapli.framework.application.Controller;

/**
 *
 * @author João Santos <1150639@isep.ipp.pt>
 */
public class ConsultMenuController implements Controller {

    private final ListMenuService listMenuS = new ListMenuService();

    public Iterable<Menu> weekMenu() {
        return this.listMenuS.weekMenu();
    }
}
