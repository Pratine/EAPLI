package eapli.ecafeteria.persistence;

import java.util.Optional;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface DishRepository extends DataRepository<Dish, Designation> {

    Optional<Dish> findByName(Designation name);

    Iterable<Dish> allDishes();
}
