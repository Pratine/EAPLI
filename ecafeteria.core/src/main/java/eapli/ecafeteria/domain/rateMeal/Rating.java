/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.rateMeal;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.pos.MealType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Utilizador
 */
@Entity
    @Table(uniqueConstraints = @UniqueConstraint(columnNames={"meal_id"}))
public class Rating implements Serializable {

    @Version
    private Long version;
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    private CafeteriaUser user;

    private int rating;
    @OneToOne
    private Meal meal;

//    @Temporal(TemporalType.DATE)
//    private Date date;
//    @Enumerated(EnumType.STRING)
//    private MealType mealType;

//    public Rating(CafeteriaUser user, Date date, MealType mealType, int rating) {
//        setDate(date);
//        setMealType(mealType);
//        setRating(rating);
//        setUser(user);
//    }
    
    public Rating(CafeteriaUser user,Meal meal,int rating){
        this.meal=meal;
        setUser(user);
        setRating(rating);
            }

    public Rating() {

    }

    /**
     * @param user the user to set
     */
    private void setUser(CafeteriaUser user) {
        if (user == null) {
            System.out.println("invalid user");
        } else {
            this.user = user;
        }

    }

    /**
     * @param rating the rating to set
     */
    private void setRating(int rating) {
        if (rating < 1 || rating > 5) {
            System.out.println("invalid number inserted");
        } else {

            this.rating = rating;
        }
    }

//    /**
//     * @param date the date to set
//     */
//    private void setDate(Date date) {
//        if (date == null) {
//            System.out.println("not a valid date");
//        } else {
//            this.date = date;
//        }
//    }
//
//    /**
//     * @param mealType the mealType to set
//     */
//    private void setMealType(MealType mealType) {
//        if (mealType == null) {
//            System.out.println("error with the meal");
//        } else {
//            this.mealType = mealType;
//        }
//    }

}
