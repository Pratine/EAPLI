package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.pos.Delivery;
import eapli.ecafeteria.domain.pos.DeliveryWorkSession;

import eapli.ecafeteria.persistence.DeliveryRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 * author 1161016
 */
public class InMemoryDeliveryRepository extends InMemoryRepository<Delivery, DeliveryWorkSession> implements DeliveryRepository {

    @Override
    public Iterable<Delivery> findByDeliveryWorkSession(DeliveryWorkSession dws) {
        return match(e -> e.deliveryWorkSession().equals(dws));
    }

    @Override
    protected DeliveryWorkSession newKeyFor(Delivery entity) {
        return entity.deliveryWorkSession();
    }

   

}
