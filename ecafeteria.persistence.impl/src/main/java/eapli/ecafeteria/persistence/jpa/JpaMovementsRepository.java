/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.Movements;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.MovementsRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author VitorCardoso(1161895
 */
public class JpaMovementsRepository extends JpaAutoTxRepository<Movements, MecanographicNumber> implements MovementsRepository {

    public JpaMovementsRepository(TransactionalContext autoTx) {
        super(autoTx);
    }
    
    @Override
    public Optional<Movements> findByMecanographicNumber(MecanographicNumber number) {
        Map<String, Object> params = new HashMap<>();
        params.put("number", number);
        return matchOne("e.mecanographicNumber=:number", params);
    }

}
