
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.Strings;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author maria
 */
@Entity
public class Batch implements AggregateRoot<Long>, Serializable {

    @Id
    @GeneratedValue
    private Long batchId;

    @Version
    private Long version;

    /*Batch code*/
    @Column
    private String code;

    @OneToOne
    private Material ingredient;

    @OneToOne
    private Meal meal;

    protected Batch() {
        //ORM
    }

    public Batch(String code, Material ingredient, Meal meal) {
        if (Strings.isNullOrEmpty(code)) {
            throw new IllegalArgumentException();
        }
        this.code = code;
        this.ingredient = ingredient;
        this.meal = meal;
    }

    public Batch(String code, Material ingredient) {
        this.code = code;
        this.ingredient = ingredient;
    }
    
    


    @Override
    public boolean sameAs(Object other) {
        return this.equals(other);
    }

    @Override
    public boolean is(Long otherId) {
        return id().equals(batchId);
    }

    @Override
    public Long id() {
        return this.batchId;
    }

    public String getCode() {
        return code;
    }

    public Material getIngredient() {
        return ingredient;
    }
    
    

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("BATCH:").append("\n");
        sb.append("Acronym: ").append(code).append("\n");
        sb.append("Ingredient: ").append(ingredient.toString()).append("\n");
        sb.append("Meal: ").append(meal.toString());

        return sb.toString();
    }
}
