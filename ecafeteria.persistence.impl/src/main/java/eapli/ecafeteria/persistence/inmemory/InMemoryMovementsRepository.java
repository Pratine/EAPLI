/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.Movements;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.MovementsRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Optional;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *
 * @author VitorCardoso(1161895
 */
public class InMemoryMovementsRepository extends InMemoryRepository<Movements, MecanographicNumber>
        implements MovementsRepository {

    @Override
    public Optional<Movements> findByMecanographicNumber(MecanographicNumber number) {
        return Optional.of(data().get(number));
    }

    @Override
    protected MecanographicNumber newKeyFor(Movements entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forEach(Consumer<? super Movements> cnsmr) {
        super.forEach(cnsmr); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Spliterator<Movements> spliterator() {
        return super.spliterator(); //To change body of generated methods, choose Tools | Templates.
    }

}
