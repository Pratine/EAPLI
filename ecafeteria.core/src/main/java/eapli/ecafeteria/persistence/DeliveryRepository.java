package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.pos.Delivery;
import eapli.ecafeteria.domain.pos.DeliveryWorkSession;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author 1161016
 */
public interface DeliveryRepository extends DataRepository<Delivery, DeliveryWorkSession>{
    	Iterable<Delivery> findByDeliveryWorkSession(DeliveryWorkSession dws);

}
