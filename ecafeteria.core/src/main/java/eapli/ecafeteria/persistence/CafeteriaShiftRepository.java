/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.cafeteria.domain.cafeteriashift.CafeteriaShift;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

/**
 *
 * @author Guilherme
 */
public interface CafeteriaShiftRepository extends DataRepository<CafeteriaShift, Long> {
    
    /*Returns CafeteriaShift object of that date/meal*/
    CafeteriaShift getShift(Calendar date, MealType mealType);
    
    CafeteriaShift findById(Long l);
}
