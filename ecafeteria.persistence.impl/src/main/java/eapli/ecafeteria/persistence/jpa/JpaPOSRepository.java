package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.POSRepository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author 1161016
 */
class JpaPOSRepository extends CafeteriaJpaRepositoryBase<POS, Integer> implements POSRepository {

    @Override
    public Optional<POS> findByNumber(Integer number) {
        final Map<String, Object> params = new HashMap<>();
        params.put("POSnumber", number);
        return matchOne("e.number=:POSnumber", params);
    }

}
