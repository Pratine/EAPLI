package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 * A Cafeteria User.
 *
 * This class represents cafeteria users. It follows a DDD approach where User
 * is the root entity of the Cafeteria User Aggregate and all of its properties
 * are instances of value objects. A Cafeteria User references a System User
 *
 * This approach may seem a little more complex than just having String or
 * native type attributes but provides for real semantic of the domain and
 * follows the Single Responsibility Pattern
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 *
 */
@Entity
public class CafeteriaUser implements AggregateRoot<MecanographicNumber>, Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @EmbeddedId
    private MecanographicNumber mecanographicNumber;

    /**
     * cascade = CascadeType.NONE as the systemUser is part of another aggregate
     */
    @OneToOne()
    private SystemUser systemUser;

    @OneToOne(cascade = {CascadeType.ALL})
    private Account account;

    @OneToOne(cascade = {CascadeType.ALL})
    private Profile profile;

    public CafeteriaUser(SystemUser user, MecanographicNumber mecanographicNumber) {
        if (mecanographicNumber == null || user == null) {
            throw new IllegalArgumentException();
        }
        this.systemUser = user;
        this.mecanographicNumber = mecanographicNumber;
        this.account = new Account(0);
        this.profile = new Profile();
    }

    protected CafeteriaUser() {
        // for ORM only
    }

    public SystemUser user() {
        return this.systemUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CafeteriaUser)) {
            return false;
        }

        final CafeteriaUser other = (CafeteriaUser) o;
        return this.mecanographicNumber.equals(other.mecanographicNumber);
    }

    @Override
    public int hashCode() {
        return this.mecanographicNumber.hashCode();
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof CafeteriaUser)) {
            return false;
        }

        final CafeteriaUser that = (CafeteriaUser) other;
        if (this == that) {
            return true;
        }
        return (this.mecanographicNumber.equals(that.mecanographicNumber)
                && this.systemUser.sameAs(that.systemUser));
    }

    public MecanographicNumber mecanographicNumber() {
        return id();
    }

    @Override
    public MecanographicNumber id() {
        return this.mecanographicNumber;
    }

    public float getBalance() {
        return this.account.getBalance();
    }

    public boolean addCredits(float credits) {
        return this.account.addCredits(credits);
    }
    
    public boolean removeCredits(float credits) {
        return this.account.removeCredits(credits);
    }

    public Profile profile() {
        return this.profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public void setBalanceLimit(int limit) {
        account.setBalanceLimit(limit);
    }

    public int getBalanceLimit() {
        return account.getBalanceLimit();
    }

    public Profile changeProfile(Integer maxCaloriesPerMeal, Integer maxSaltPerMeal, Integer maxCaloriesPerWeek, Integer maxSaltPerWeek) {
        if (this.profile == null) {
            this.profile = new Profile(maxCaloriesPerMeal, maxSaltPerMeal, maxCaloriesPerWeek, maxSaltPerWeek);
        } else {
            this.profile.changeProfile(maxCaloriesPerMeal, maxSaltPerMeal, maxCaloriesPerWeek, maxSaltPerWeek);
        }
        return this.profile;
    }

}
