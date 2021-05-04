/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.cafeteria.app.common.console.presentation.MyUserMenu;
import eapli.cafeteria.app.common.console.presentation.SalesMenu;
import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.HorizontalMenuRenderer;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;

    private static final int OPEN_POS_OPTION = 2;

    private static final int CLOSE_POS_OPTION = 3;

    private static final int VIEW_BOOKINGS_UNDELIVERED_OPTION = 4;

    private static final int SALES_OPTION = 5;

    private static final int VIEW_AVAILABLE_MEALS = 6;
    
    private static final int CAFETERIA_SHUTDOWN = 7;
    
    private static final int REGISTER_COMPLAINT=8;

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu);
        } else {
            renderer = new VerticalMenuRenderer(menu);
        }
        return renderer.show();
    }

    @Override
    public String headline() {
        new ViewAvailableMealsUI().doShow();
        new ViewBookingsUndeliveredUI().doShow();
        return "eCafeteria POS [@" + AuthorizationService.session().authenticatedUser().id() + "]";
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        if (AuthorizationService.session().authenticatedUser().isAuthorizedTo(ActionRight.SALE)) {
            // TODO
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        mainMenu.add(new MenuItem(OPEN_POS_OPTION, "Open POS", () -> new OpenPOSUI().show()));

        mainMenu.add(new MenuItem(CLOSE_POS_OPTION, "Close POS", () -> new ClosePOSUI().show()));

        //mainMenu.add(new MenuItem(VIEW_BOOKINGS_UNDELIVERED_OPTION, "View Bookings Undelivered", () -> new ViewBookingsUndeliveredUI().show()));

        final Menu salesMenu = new SalesMenu();
        mainMenu.add(new SubMenu(SALES_OPTION, salesMenu, new ShowVerticalSubMenuAction(salesMenu)));

        //mainMenu.add(new MenuItem(VIEW_AVAILABLE_MEALS, "View Available Meals", () -> new ViewAvailableMealsUI().show()));
        
        mainMenu.add(new MenuItem(CAFETERIA_SHUTDOWN, "Shutdown Cafeteria Shift", () -> new CafeteriaShutdownUI().show()));

        mainMenu.add(new MenuItem(REGISTER_COMPLAINT,"Register Complaint",() -> new registerComplaintUI().show()));
       
        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }
}
