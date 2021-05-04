/**
 *
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.domain.kitchen.Material;
import eapli.framework.visitor.Visitor;

/**
 * @author Paulo Gandra Sousa
 *
 */
class MaterialPrinter implements Visitor<Material> {

    @Override
    public void visit(Material visitee) {
        System.out.printf("%-10s%-30s", visitee.id(), visitee.description());
    }

    @Override
    public void beforeVisiting(Material visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(Material visitee) {
        // nothing to do
    }
}
