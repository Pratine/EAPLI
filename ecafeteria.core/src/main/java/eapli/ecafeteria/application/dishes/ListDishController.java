package eapli.ecafeteria.application.dishes;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.application.Controller;

/**
 * Created on 29/03/2016.
 */
public class ListDishController implements Controller {

    private final ListDishService svc = new ListDishService();

    public Iterable<Dish> allDishes() {
        return this.svc.allDishes();
    }
}
