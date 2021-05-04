package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.Delivery;
import eapli.ecafeteria.domain.pos.DeliveryWorkSession;
import eapli.ecafeteria.persistence.DeliveryRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author 1161016
 */
class JpaDeliveryRepository extends CafeteriaJpaRepositoryBase<Delivery, DeliveryWorkSession> implements DeliveryRepository {

    @Override
    public List<Delivery> findByDeliveryWorkSession(DeliveryWorkSession dws) {
        final Map<String, Object> params = new HashMap<>();
        params.put("dws", dws);
        return match("e.session=:dws", params);
    }

}
