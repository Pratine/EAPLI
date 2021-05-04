/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.Movements;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author VitorCardoso(1161895
 */
public interface MovementsRepository extends DataRepository<Movements, MecanographicNumber> {

    Optional<Movements> findByMecanographicNumber(MecanographicNumber number);
    
}
