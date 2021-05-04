package eapli.ecafeteria.persistence.inmemory;

import java.util.Optional;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 * Created by MCN on 29/03/2016.
 */
public class InMemoryPOSRepository extends InMemoryRepository<POS, Integer> implements POSRepository {

     @Override
    public Optional<POS> findByNumber(Integer number) {
        return matchOne(e -> e.number()==number);
    }

    @Override
    protected Integer newKeyFor(POS entity) {
        return entity.id();
    }

}
