/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.cafeteria.domain.cafeteriashift.CafeteriaShift;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.persistence.CafeteriaShiftRepository;
import java.util.Calendar;
import javax.persistence.Query;

/**
 *
 * @author Guilherme
 */
public class JpaCafeteriaShiftRepository extends CafeteriaJpaRepositoryBase<CafeteriaShift, Long> implements CafeteriaShiftRepository {


    @Override
    public CafeteriaShift getShift(Calendar date, MealType mealType) {
       Query createQuery = entityManager().createQuery("SELECT cs FROM CafeteriaShift cs WHERE cs.date=:date AND cs.mealType=:mealType");
       createQuery.setParameter("date", date);
       createQuery.setParameter("mealType", mealType);
       return (CafeteriaShift) createQuery.getSingleResult();
    }

    @Override
    public CafeteriaShift findById(Long l) {
       return this.findOne(l).get();
    }

   
    
}
