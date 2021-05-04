/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.kitchen.RegisterBatchController;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.BatchRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author maria
 */
public class BatchBootstrapper implements Action {

    @Override
    public boolean execute() {

        final MaterialRepository materialRepo = PersistenceContext.repositories().materials();
        BatchRepository batchRepository = PersistenceContext.repositories().batches();
        Iterable<Material> listaMateriais = materialRepo.findAll();

        RegisterBatchController controller = new RegisterBatchController();
        Iterator<Material> materiaisIterator = listaMateriais.iterator();
        while (materiaisIterator.hasNext()) {
            Material aux = materiaisIterator.next();

            StringBuilder sb = new StringBuilder();
            sb.append("batch-of-").append(aux.id());

            try {
                Batch n = new Batch(sb.toString(), aux);
                batchRepository.save(n);
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(BatchBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERRO");
            }
        }

        return false;
    }
}
