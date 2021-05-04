/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.authz;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Henrique Moura Costa
 */
public class DeactivateUserListPrinter implements Visitor<SystemUser> {

    @Override
    public void visit(SystemUser visitee) {
            System.out.printf("%-10s%-30s%-30s%-4s", visitee.id(), visitee.name().firstName(),visitee.name().lastName(),String.valueOf(visitee.isActive()));
    }

    @Override
    public void beforeVisiting(SystemUser visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(SystemUser visitee) {
        // nothing to do
    }
} 
    
