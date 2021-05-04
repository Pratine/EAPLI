package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.ddd.DomainFactory;

/**
 * A factory for User entities.
 *
 * This class demonstrates the use of the factory (DDD) pattern using a fluent
 * interface. it acts as a Builder (GoF).
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public class CafeteriaUserBuilder implements DomainFactory<CafeteriaUser> {

	private SystemUser systemUser;
	private MecanographicNumber mecanographicNumber;

	public CafeteriaUserBuilder withSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
		return this;
	}

	public CafeteriaUserBuilder withMecanographicNumber(MecanographicNumber mecanographicNumber) {
		this.mecanographicNumber = mecanographicNumber;
		return this;
	}

	public CafeteriaUserBuilder withMecanographicNumber(String mecanographicNumber) {
		this.mecanographicNumber = new MecanographicNumber(mecanographicNumber);
		return this;
	}

	@Override
	public CafeteriaUser build() {
		// since the factory knows that all the parts are needed it could throw
		// an exception. however, we will leave that to the constructor
		return new CafeteriaUser(this.systemUser, this.mecanographicNumber);
	}
}
