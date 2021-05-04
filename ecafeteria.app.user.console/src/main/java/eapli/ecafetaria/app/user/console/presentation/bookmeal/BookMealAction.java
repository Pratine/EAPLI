package eapli.ecafetaria.app.user.console.presentation.bookmeal;

import eapli.framework.actions.Action;

/**
 *
 * @author 1150448
 */
public class BookMealAction implements Action {
    
    @Override
    public boolean execute() {
        return new BookMealUI().show();
    }
    
}
