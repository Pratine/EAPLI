package eapli.ecafetaria.domain.meals;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.framework.domain.Designation;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Joao Paulo
 */
@Entity
public class Meal implements AggregateRoot<Long>, Serializable {

    @Version
    private Long version;

    /*Quantity of meald acctualy made*/
    private int quantity_made;
    private static final float priceBooking = 3;

    @Override
    public boolean sameAs(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Meal other = (Meal) obj;
        if (!Objects.equals(this.dish, other.dish)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (this.mealType != other.mealType) {
            return false;
        }
        return true;
    }

    @Override
    public Long id() {
        return id;
    }

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Dish dish;

    @Temporal(TemporalType.DATE)
    private Calendar date;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private ArrayList<Batch> batches;

    public Meal(Dish dish, Calendar date, MealType mealType) {
        if (dish == null || date == null) {
            throw new IllegalArgumentException();
        }
        this.dish = dish;
        this.date = date;
        this.mealType = mealType;
        this.quantity_made = 0;
        this.batches = new ArrayList<>();
    }

    public Designation dishName() {
        return dish.name();
    }

    public Meal() {
    }

    public Calendar date() {
        return date;
    }

    public MealType mealType() {
        return mealType;
    }

    public Dish dish() {
        return dish;
    }

    public void setQuantity_made(int quantity_made) {
        if (quantity_made < 0) {
            System.out.println("Quantidade inválida.");
        } else {
            this.quantity_made = quantity_made;
        }
    }

    public int quantity_made() {
        return quantity_made;
    }

    @Override
    public String toString() {
        String data = String.format("%d-%d-%d",
                date.get(Calendar.DAY_OF_MONTH),
                date.get(Calendar.MONTH) + 1,
                date.get(Calendar.YEAR));
        return "Meal Type: " + mealType + " | Dish : " + dishName()
                + " | Date: " + data + " ";
    }

    public float price() {
        return priceBooking;
    }

    public ArrayList<Batch> batches() {
        return batches;
    }

    public boolean addBatch(Batch b) {
        return batches.add(b);
    }

}
