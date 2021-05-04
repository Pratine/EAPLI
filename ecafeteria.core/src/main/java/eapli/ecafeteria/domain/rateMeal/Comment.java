/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.rateMeal;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Utilizador
 */
@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String comment;
    @OneToOne
    private Rating rating;

    public Comment(String comment, Rating rating) {
        setComment(comment);
        setRating(rating);
    }

    public Comment() {
    }

    /**
     * @param comment the comment to set
     */
    private void setComment(String comment) {
        if (comment == null) {
            System.out.println("invalid comment");
        } else {
            this.comment = comment;
        }
    }

    /**
     * @param rating the rating to set
     */
    private void setRating(Rating rating) {
        if (rating == null) {
            System.out.println("invalid rating");
        } else {
            this.rating = rating;
        }
    }

}
