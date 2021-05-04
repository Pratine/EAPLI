package eapli.ecafeteria.persistence.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.framework.domain.Designation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaDishRepository extends CafeteriaJpaRepositoryBase<Dish, Designation> implements DishRepository {

    @Override
    public Optional<Dish> findByName(Designation name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return matchOne("e.name=:name", params);
    }

    @Override
    public Iterable<Dish> allDishes() {
        List<Dish> dishes = new ArrayList<>();
        for (Dish d : findAll()) {

            dishes.add(d);

        }
        return dishes;
    }
}
