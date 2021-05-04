package eapli.ecafeteria.domain.pos;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;

/**
 *PRECISA DISTO EM CORE...DOMAIN/MEALS/*
 * @author PC
 */
public enum MealType implements ValueObject, Serializable {
    LUNCH, DINNER;
}
