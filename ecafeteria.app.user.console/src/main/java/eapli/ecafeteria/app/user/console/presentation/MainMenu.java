/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation;

import eapli.cafeteria.app.common.console.presentation.MyUserMenu;
import eapli.cafeteria.app.user.console.presentation.nutritionalProfile.EditAllergensListAction;
import eapli.cafeteria.app.user.console.presentation.nutritionalProfile.EditProfileAction;
import eapli.ecafeteria.app.user.console.presentation.rateMeal.RegisterRatingAction;
import eapli.ecafetaria.app.user.console.presentation.bookmeal.BookMealAction;
import eapli.ecafetaria.app.user.console.presentation.consultbooking.ConsultBookingForNextDaysAction;
import eapli.ecafeteria.app.user.console.presentation.CancelamentoReservas.CancelamentoReservasAction;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;
import eapli.ecafeteria.app.user.console.presentation.consultMenu.ConsultMenuAction;
import eapli.ecafeteria.app.user.console.presentation.consultcaloricintake.ConsultCaloricIntakeaAction;
import ecafeteria.app.user.console.presentation.balanceLimit.setBalanceLimitAction;

import eapli.ecafeteria.application.bookings.CheckBalanceUnderLimitController;
/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends CafeteriaUserBaseUI {

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int BOOKINGS_OPTION = 2;
    private static final int ACCOUNT_OPTION = 3;
    private static final int SETTINGS_OPTION = 4;
    private static final int RATING_OPTION = 5;

    // BOOKINGS MENU
    private static final int LIST_MENUS_OPTION = 1;
    private static final int BOOK_A_MEAL_OPTION = 2;
    private static final int CONSULT_BOOKING_NEXT_DAYS_OPTION = 3;
    private static final int CONSULT_MENU = 4;
    private static final int CONSULT_CALORIC_INTAKE_OPTION = 5;
    private static final int CANCEL_BOOKING_OPTION = 6;

    // ACCOUNT MENU
    private static final int LIST_MOVEMENTS_OPTION = 1;
    private static final int EDIT_NUTRITIONAL_PROFILE = 2;
    private static final int EDIT_ALLERGENS_LIST = 3;

    // SETTINGS
    private static final int SET_USER_ALERT_LIMIT_OPTION = 1;

    //RATINGS
    private static final int REGISTER_RATING_OPTION = 1;
    
    //balanceLimit
    private final String alertMessage = "=ALERT=============\nBalance below limit value!\n===================";
    private final CheckBalanceUnderLimitController balanceLimitController = new CheckBalanceUnderLimitController();
    
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
        final MenuRenderer renderer = new VerticalMenuRenderer(menu);
        return renderer.show();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();
        
        if(balanceLimitController.isBelowValue()){
            System.out.println(alertMessage);
            //mainMenu.add(new MenuItem(CONFIRM_OPTION,"OK",new ShowMessageAction("Careful with the money.")));
        }

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu bookingsMenu = buildBookingsMenu();
        mainMenu.add(new SubMenu(BOOKINGS_OPTION, bookingsMenu, new ShowVerticalSubMenuAction(bookingsMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu accountMenu = buildAccountMenu();
        mainMenu.add(new SubMenu(ACCOUNT_OPTION, accountMenu, new ShowVerticalSubMenuAction(accountMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu settingsMenu = buildAdminSettingsMenu();
        mainMenu.add(new SubMenu(SETTINGS_OPTION, settingsMenu, new ShowVerticalSubMenuAction(settingsMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu ratingMenu = buildRatingMenu();
        mainMenu.add(new SubMenu(RATING_OPTION, ratingMenu, new ShowVerticalSubMenuAction(ratingMenu)));

        mainMenu.add(VerticalSeparator.separator());

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }

    private Menu buildAccountMenu() { 
        final Menu menu = new Menu("Account");
        menu.add(new MenuItem(LIST_MOVEMENTS_OPTION, "List movements", new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(EDIT_NUTRITIONAL_PROFILE, "Edit nutritional profile", new EditProfileAction()));
        menu.add(new MenuItem(EDIT_ALLERGENS_LIST, "Edit allergens list", new EditAllergensListAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }

    private Menu buildBookingsMenu() {
        final Menu menu = new Menu("Bookings");
        menu.add(new MenuItem(LIST_MENUS_OPTION, "List menus", new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(BOOK_A_MEAL_OPTION, "Book a meal", new BookMealAction()));
        menu.add(new MenuItem(CONSULT_BOOKING_NEXT_DAYS_OPTION, "Consult booking for the next days", new ConsultBookingForNextDaysAction()));
        menu.add(new MenuItem(CONSULT_MENU, "Consult menu", new ConsultMenuAction()));
        menu.add(new MenuItem(CONSULT_CALORIC_INTAKE_OPTION, "Consult caloric intake in the selected period", new ConsultCaloricIntakeaAction()));
        menu.add(new MenuItem(CANCEL_BOOKING_OPTION, "Cancel booking", new CancelamentoReservasAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings");

        menu.add(new MenuItem(SET_USER_ALERT_LIMIT_OPTION, "Set users' alert limit",
                new setBalanceLimitAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildRatingMenu() {
        final Menu menu = new Menu("Ratings");
        menu.add(new MenuItem(REGISTER_RATING_OPTION, "Register of a meal rating", new RegisterRatingAction()));
        return menu;
    }
    
    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }
}
