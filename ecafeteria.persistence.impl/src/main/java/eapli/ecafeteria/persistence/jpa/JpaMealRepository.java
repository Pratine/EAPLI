package eapli.ecafeteria.persistence.jpa;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.persistence.MealRepository;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author maria
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository {

    @Override
    public Meal findById(Long l) {
        return this.findOne(l).get();
    }
    
    @Override
    public List<Meal> findByDate(Calendar date) {
        Query query = entityManager().createQuery("SELECT m FROM Meal m WHERE m.date=:date", Meal.class);
        query.setParameter("date", date);
        return query.getResultList();
    }
    
    @Override
    public List<Meal> findByDateAndType(Calendar date, MealType mealType) {
        Query query = entityManager().createQuery("SELECT m FROM Meal m WHERE m.date=:date AND m.mealType = :mealType", Meal.class);
        query.setParameter("date", date);
        query.setParameter("mealType", mealType);
        
        return query.getResultList();
    }

    @Override
    public List<Meal> findMeals() {
        return entityManagerFactory().createEntityManager().createQuery("SELECT m FROM Meal m", Meal.class ).getResultList();
    }

}