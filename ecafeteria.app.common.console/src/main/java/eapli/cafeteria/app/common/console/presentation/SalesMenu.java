/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.app.common.console.presentation;

import eapli.ecafeteria.app.common.console.presentation.sales.CardChargementUI;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.ShowMessageAction;

/**
 *
 * @author VitorCardoso(1161895
 */
public class SalesMenu extends Menu {

    private static final int EXIT_OPTION = 0;

    // SALES
    private static final int REGISTER_MAIL_DELIVERY_OPTION = 1;
    private static final int COUNTER_SALES_OPTION = 2;
    private static final int REGISTER_COMPLAINT_OPTION = 3;
    private static final int CARD_CHARGEMENT_OPTION = 4;

    public SalesMenu() {
        super("Sales >");
        buildSalesMenu();
    }

    private void buildSalesMenu() {
        add(new MenuItem(REGISTER_MAIL_DELIVERY_OPTION, "Register Mail Delivery", new ShowMessageAction("Not implemented yet")));
        add(new MenuItem(COUNTER_SALES_OPTION, "Counter Sales", new ShowMessageAction("Not implemented yet")));
        add(new MenuItem(REGISTER_COMPLAINT_OPTION, "Register Complaint", new ShowMessageAction("Not implemented yet")));
        add(new MenuItem(CARD_CHARGEMENT_OPTION, "Card Chargement", () -> new CardChargementUI().show()));
        add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
    }
}
