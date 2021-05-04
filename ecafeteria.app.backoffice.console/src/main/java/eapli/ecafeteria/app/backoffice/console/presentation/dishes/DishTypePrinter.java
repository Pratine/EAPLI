/**
 *
 */
package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.domain.dishes.DishType;
import eapli.framework.visitor.Visitor;

/**
 * @author Paulo Gandra Sousa
 *
 */
class DishTypePrinter implements Visitor<DishType> {

    @Override
    public void visit(DishType visitee) {
        System.out.printf("%-10s%-30s%-4s", visitee.id(), visitee.description(), String.valueOf(visitee.isActive()));
    }

    @Override
    public void beforeVisiting(DishType visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(DishType visitee) {
        // nothing to do
    }
}
