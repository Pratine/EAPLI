/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author salva
 */
public class BatchPrinter implements Visitor<Batch> {

    @Override
    public void visit(Batch visitee) {
        StringBuilder sb = new StringBuilder();
        sb.append("Batch: ").append(visitee.toString()).append("\n");
        System.out.println(sb.toString());
    }

}
