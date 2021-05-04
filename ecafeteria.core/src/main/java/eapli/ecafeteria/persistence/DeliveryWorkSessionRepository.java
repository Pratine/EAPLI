package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.pos.DeliveryWorkSession;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author PC
 */
public interface DeliveryWorkSessionRepository extends DataRepository<DeliveryWorkSession, Integer> {
    //Optional<DeliveryWorkSession> findById(Integer id);
}
