/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.persistence.BatchRepository;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maria
 */
public class JpaBatchRepository extends CafeteriaJpaRepositoryBase<Batch, Long> implements BatchRepository {

    @Override
    public Iterable<Batch> all() {

        List<Batch> batches = new ArrayList<>();
        for (Batch d : findAll()) {
            batches.add(d);
        }
        return batches;
    }
}
