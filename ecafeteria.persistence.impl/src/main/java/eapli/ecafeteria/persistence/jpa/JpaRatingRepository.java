/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.rateMeal.Rating;
import eapli.ecafeteria.persistence.MealRatingRepository;

/**
 *
 * @author Utilizador
 */
public class JpaRatingRepository extends CafeteriaJpaRepositoryBase<Rating, Long> implements MealRatingRepository {
    
}
