package ecafeteria.app.user.console.presentation.balanceLimit;

import eapli.framework.actions.Action;

/**
 *
 * @author 1161016
 */
public class setBalanceLimitAction implements Action {

    @Override
    public boolean execute() {
        return new setBalanceLimitUI().show();
    }
}
