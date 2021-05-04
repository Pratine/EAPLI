/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.persistence.BatchRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.ArrayList;

/**
 *
 * @author Guilherme
 */
public class BatchSearchController implements Controller {

    private final MealRepository mealRepository = PersistenceContext.repositories().meals();
    private final BatchRepository batchRepository = PersistenceContext.repositories().batches();
    
  

    /*Returns meals associated with that batch (lote)*/
    public Iterable<Meal> listMealsByBatch(Batch batch) {
        Iterable<Meal> meals = mealRepository.findAll();
        ArrayList<Meal> mealsReturnList = new ArrayList<>();
        for (Meal m : meals) {
   /*         for(Batch b : m.batches()){
                if (b.equals(batch)) {
                    mealsReturnList.add(m);
                }
            }
            */
        }
        return mealsReturnList;
    }
    
     public Iterable<Batch> allBatches() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return this.batchRepository.findAll();
    }
   
}
