package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.dishes.Allergen;
import eapli.ecafeteria.dto.ProfileDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Profile implements Serializable {

    private static final long serialVersionUID = 1817L;

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "Profile_Allergen")
    @Enumerated(EnumType.STRING)
    @Column(name = "Allergen")
    private List<Allergen> allergens;

    private int maxCaloriesPerMeal;

    private int maxCaloriesPerWeek;

    private int maxSaltPerMeal; //milligrams

    private int maxSaltPerWeek; //milligrams

    public List<Allergen> allergens() {
        return allergens;
    }

    public Profile() {
        this.allergens = new ArrayList<>();
        this.maxSaltPerWeek = 10;
        this.maxSaltPerMeal = 150;
        this.maxCaloriesPerMeal = 160;
        this.maxCaloriesPerWeek = 17500; //2500 per day
    }

    public Profile(Integer maxCaloriesPerMeal, Integer maxSaltPerMeal,
            Integer maxCaloriesPerWeek, Integer maxSaltPerWeek) {
        this.allergens = new ArrayList<>();
        this.maxSaltPerWeek = maxSaltPerWeek;
        this.maxSaltPerMeal = maxSaltPerMeal;
        this.maxCaloriesPerMeal = maxCaloriesPerMeal;
        this.maxCaloriesPerWeek = maxCaloriesPerWeek; //2500 per day
    }

    public Profile changeProfile(Integer maxCaloriesPerMeal, Integer maxSaltPerMeal, Integer maxCaloriesPerWeek, Integer maxSaltPerWeek) {
        if (maxCaloriesPerMeal == null || maxCaloriesPerMeal <= 0) {
            throw new IllegalStateException("Max calories per meal can't be negative or equal 0");
        }
        if (maxSaltPerMeal == null || maxSaltPerMeal < 0) {
            throw new IllegalStateException("Max salt per salt can't be negative");
        }
        if (maxCaloriesPerWeek == null || maxCaloriesPerWeek <= 0) {
            throw new IllegalStateException("Max calories per meal can't be negative or equal 0");
        }
        if (maxSaltPerWeek == null || maxSaltPerWeek < 0) {
            throw new IllegalStateException("Max salt per salt can't be negative");
        }
        this.maxCaloriesPerMeal = maxCaloriesPerMeal;
        this.maxSaltPerMeal = maxSaltPerMeal;
        this.maxCaloriesPerWeek = maxCaloriesPerWeek;
        this.maxSaltPerWeek = maxSaltPerWeek;
        return this;
    }

    public boolean addAllergen(Allergen allergen) {
        return allergens.add(allergen);
    }

    public boolean changeAllergensList(List<Allergen> allergenList) {
        allergens = allergenList;
        return true;
    }

    public Integer maxCaloriesPerMeal() {
        return maxCaloriesPerMeal;
    }

    public Integer maxSaltPerMeal() {
        return maxSaltPerMeal;
    }

    public Integer maxCaloriesPerWeek() {
        return maxCaloriesPerWeek;
    }

    public Integer maxSaltPerWeek() {
        return maxSaltPerWeek;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public ProfileDTO toDTO(){
        return new ProfileDTO(maxCaloriesPerMeal, maxSaltPerMeal, maxCaloriesPerWeek, maxSaltPerWeek);
    }

}
