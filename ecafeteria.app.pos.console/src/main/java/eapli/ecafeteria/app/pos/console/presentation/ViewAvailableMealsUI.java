/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.pos.ViewAvailableMealsController;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Map;

/**
 *
 * @author Francisco
 */
public class ViewAvailableMealsUI extends AbstractUI{
    
    private final ViewAvailableMealsController controller = new ViewAvailableMealsController();
    
    public ViewAvailableMealsUI(){
        
    }

    @Override
    protected boolean doShow() {
        System.out.println(headline() + "\n");
        
        Map<DishType, Integer> mealsQuantity = controller.viewAvailableMeals();
        
        if(mealsQuantity.isEmpty()){
            System.out.println("There are no available meals.\n");
            return false;
        }
        
        for (DishType dish_type : mealsQuantity.keySet()) {
            System.out.println(dish_type.description() + "\nAvailable quantity: " + mealsQuantity.get(dish_type) + "\n");
        }
        
        return true;
    }

    @Override
    public String headline() {
        return "****AVAILABLE MEAL QUANTITY****";
    }
    
    
}
