/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.app.user.console.presentation.nutritionalProfile;

import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.framework.visitor.Visitor;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Pedro Quinta
 */
public class AllergensListPrinter implements Visitor<List<Allergen>> {
    @Override
    public void visit(List<Allergen> visitee) {
        for(int i=0; i< visitee.size(); i++){
           System.out.printf("%d --> %-30s\n", i+1, visitee.get(i).name()); 
            
        }
    }
    
    public int allAllergens(Iterable<Allergen> visitee, List<Allergen> listAll) {
        int cont = 1;
        
        for(Allergen al : visitee){
           System.out.printf("%d --> %-30s\n",cont, al.name());
           listAll.add(al);
           cont++; 
        }
         
        return cont;
    }
    
    
    
}
