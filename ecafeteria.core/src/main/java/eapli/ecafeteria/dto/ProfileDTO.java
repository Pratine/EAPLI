package eapli.ecafeteria.dto;

import eapli.ecafeteria.domain.cafeteriauser.Profile;

public class ProfileDTO {

    public int maxCaloriesPerMeal;
    public int maxCaloriesPerWeek;
    public int maxSaltPerMeal;
    public int maxSaltPerWeek;

    public ProfileDTO(int maxCaloriesPerMeal, int maxSaltPerMeal,
            int maxCaloriesPerWeek, int maxSaltPerWeek) {
        this.maxCaloriesPerMeal = maxCaloriesPerMeal;
        this.maxCaloriesPerWeek = maxCaloriesPerWeek;
        this.maxSaltPerMeal = maxSaltPerMeal;
        this.maxSaltPerWeek = maxSaltPerWeek;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProfileDTO other = (ProfileDTO) obj;
        if (this.maxCaloriesPerMeal != other.maxCaloriesPerMeal) {
            return false;
        }
        if (this.maxCaloriesPerWeek != other.maxCaloriesPerWeek) {
            return false;
        }
        if (this.maxSaltPerMeal != other.maxSaltPerMeal) {
            return false;
        }
        if (this.maxSaltPerWeek != other.maxSaltPerWeek) {
            return false;
        }
        return true;
    }
    
    
    
}
