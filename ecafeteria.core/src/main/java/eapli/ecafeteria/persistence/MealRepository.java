package eapli.ecafeteria.persistence;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author 1150448
 */
public interface MealRepository extends DataRepository<Meal, Long> {
    
    Meal findById(Long l);
    
    Iterable<Meal> findByDate(Calendar date);

    public List<Meal> findByDateAndType(Calendar date, MealType type);
    
    List<Meal> findMeals();
}
