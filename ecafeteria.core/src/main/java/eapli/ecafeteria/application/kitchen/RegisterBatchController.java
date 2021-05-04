/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.BatchRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author maria
 */
public class RegisterBatchController implements Controller {

    BatchRepository batchRepository = PersistenceContext.repositories().batches();
    MaterialRepository materialRepository = PersistenceContext.repositories().materials();
    MealRepository mealRepository = PersistenceContext.repositories().meals();

    /* 1 - selecionar o dia da refeicao
    2 - escolher uma refeiçao desse dia de uma lista */
    public Iterable<Meal> findByDate(Calendar date) {
        return mealRepository.findByDate(date);
    }

    /*escolher o material de uma lista*/
    public Iterable<Material> getAvaliableMaterials() {
        return materialRepository.findAll();
    }

    /*3.1 - adicionar um material novo
		- new Material (codigo, descricao)*/
    public Material newMaterial(String acronym, String description)
            throws DataConcurrencyException, DataIntegrityViolationException {
        Material n = new Material(acronym, description);

        return materialRepository.save(n);
    }

    /* adicionar um novo Lote
     */
    public Batch newBatch(String acronym, Material ingredient, Meal meal)
            throws DataConcurrencyException, DataIntegrityViolationException {
        Batch n = new Batch(acronym, ingredient, meal);
        return batchRepository.save(n);
    }

    public Meal saveMeal(Meal m)
            throws DataConcurrencyException, DataIntegrityViolationException {
        return mealRepository.save(m);
    }
}
