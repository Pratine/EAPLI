package eapli.ecafeteria.persistence.jpa;

import java.util.Optional;

import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;

/**
 * Created by MCN on 29/03/2016.
 */
class JpaDishTypeRepository extends CafeteriaJpaRepositoryBase<DishType, Long> implements DishTypeRepository {

	@Override
	public Iterable<DishType> activeDishTypes() {
		return match("e.active=true");
	}

	@Override
	public Optional<DishType> findByAcronym(String acronym) {
		return matchOne("e.acronym=:acronym", "acronym", acronym);
	}
}
