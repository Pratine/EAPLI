package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.pos.DeliveryWorkSession;
import eapli.ecafeteria.persistence.DeliveryWorkSessionRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 * Created by MCN on 29/03/2016.
 */
public class InMemoryDeliveryWorkSessionRepository extends InMemoryRepository<DeliveryWorkSession, Integer> implements DeliveryWorkSessionRepository {

    @Override
    protected Integer newKeyFor(DeliveryWorkSession entity) {
        return entity.id();
    }

}
