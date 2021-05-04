package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.pos.POS;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author PC
 */
public interface POSRepository extends DataRepository<POS, Integer>{
    	Optional<POS> findByNumber(Integer number);

}
