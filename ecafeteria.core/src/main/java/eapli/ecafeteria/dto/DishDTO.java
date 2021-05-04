/**
 *
 */
package eapli.ecafeteria.dto;

import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.framework.dto.DTO;
import java.util.List;

/**
 * a pure DTO for dishes
 *
 * @author SOU03408
 *
 */
@SuppressWarnings("squid:ClassVariableVisibilityCheck")
public class DishDTO implements DTO {

    public DishDTO(String dishTypeAcronym, String dishTypeDescription, String name2,
            Integer calories2, Integer salt2, double amount, String currency2, boolean active2, List<Allergen> allergens) {
        this.dishTypeAcronym = dishTypeAcronym;
        this.dishTypeDescription = dishTypeDescription;
        name = name2;
        calories = calories2;
        salt = salt2;
        price = amount;
        this.currency = currency2;
        this.active = active2;
        this.allergens = allergens;
    }
    
    public List<Allergen> allergens;
    public String dishTypeAcronym;
    public String dishTypeDescription;

    public String name;

    public int calories;
    public int salt;

    public double price;
    public String currency;

    public boolean active;
}
