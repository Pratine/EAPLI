package eapli.ecafeteria.dto;

public class NutricionalInfoDTO {
    
    public int salt;
    public int calories;

    public NutricionalInfoDTO(int calories, int salt) {
        this.calories = calories;
        this.salt = salt;
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
        final NutricionalInfoDTO other = (NutricionalInfoDTO) obj;
        if (this.salt != other.salt) {
            return false;
        }
        if (this.calories != other.calories) {
            return false;
        }
        return true;
    }
    
}
