package ecafeteria.app.user.console.presentation.balanceLimit;

import eapli.ecafeteria.application.cafeteriauser.setBalanceLimitController;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author 1161016
 */
public class setBalanceLimitUI extends AbstractUI{

    private final setBalanceLimitController controller = new setBalanceLimitController();

    protected Controller controller() {
        return this.controller;
    }


    @Override
    protected boolean doShow() {
        int ans = -1;
        while (ans < 0) {
            ans = Console.readInteger("Qual o limite do saldo a partir do qual quer ser avisado?");
        }
        if(controller.setBalanceLimit(eapli.ecafeteria.application.authz.AuthorizationService.session().authenticatedUser().id(), ans)){
            System.out.println("Valor limite atualizado com sucesso!");
        }else{
            System.out.println("Erro ao atualizar o valor limite");
        }
        return true;
    }

    @Override
    public String headline() {
        return "set Balance Limit";
    }
}
