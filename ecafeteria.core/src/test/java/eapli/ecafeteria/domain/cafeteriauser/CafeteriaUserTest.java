package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUserBuilder;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nuno Bettencourt [NMB] on 03/04/16.
 */


public class CafeteriaUserTest {

    private final String anEmail = "a@a.en";
    private final String aMecanographicNumber = "abcd";
    private final String anotherMecanographicNumber = "xyzt";

    @Test
    public void ensureCafeteriaUserEqualsPassesForTheSameMecanographicNumber() throws Exception {
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.CAFETERIA_USER);

        final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber("123456")
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        final CafeteriaUser anotherCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber("123456")
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        final boolean expected = aCafeteriaUser.equals(anotherCafeteriaUser);

        assertTrue(expected);
    }

    @Test
    public void ensureCafeteriaUserEqualsFailsForDifferenteMecanographicNumber() throws Exception {
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.CAFETERIA_USER);

        final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber(aMecanographicNumber)
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        final CafeteriaUser anotherCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber(anotherMecanographicNumber)
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        final boolean expected = aCafeteriaUser.equals(anotherCafeteriaUser);

        assertFalse(expected);
    }

    @Test
    public void ensureCafeteriaUserEqualsAreTheSameForTheSameInstance() throws Exception {
        final CafeteriaUser aCafeteriaUser = new CafeteriaUser();

        final boolean expected = aCafeteriaUser.equals(aCafeteriaUser);

        assertTrue(expected);
    }

    @Test
    public void ensureCafeteriaUserEqualsFailsForDifferenteObjectTypes() throws Exception {
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.CAFETERIA_USER);

        final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber("DUMMY")
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        final Set<RoleType> systemUserRoles = new HashSet<>();
        systemUserRoles.add(RoleType.CAFETERIA_USER);
        final SystemUser systemUser = new SystemUser("userName", "passwordB1", "firsName", "lastName", anEmail,
                systemUserRoles);
        final boolean expected = aCafeteriaUser.equals(systemUser);

        assertFalse(expected);
    }

    @Test
    public void ensureCafeteriaUserIsTheSameAsItsInstance() throws Exception {
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.ADMIN);
        final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber("DUMMY")
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        final boolean expected = aCafeteriaUser.sameAs(aCafeteriaUser);

        assertTrue(expected);
    }

    @Test
    public void ensureTwoCafeteriaUserWithDifferentMecanographicNumbersAreNotTheSame() throws Exception {
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.ADMIN);
        final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber(aMecanographicNumber)
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        final CafeteriaUser anotherCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber(anotherMecanographicNumber)
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        final boolean expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

        assertFalse(expected);
    }

    @Test
    public void ensureTwoCafeteriaUsersWithDifferentSystemUsersAreNotTheSame() throws Exception {
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.ADMIN);
        final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber("DUMMY")
                .withSystemUser(new SystemUser("one-dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        final CafeteriaUser anotherCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber("DUMMY")
                .withSystemUser(new SystemUser("two-dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        final boolean expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

        assertFalse(expected);
    }

    @Test
    public void ensureTwoCafeteriaUsersWithDifferentOrganicUnitsAreNotTheSame() throws Exception {
        boolean expected = false;

        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.ADMIN);

        final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber("DUMMY")
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        final CafeteriaUser anotherCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber("DUMMY")
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        expected = aCafeteriaUser.sameAs(anotherCafeteriaUser);

        assertFalse(expected);
    }

    //ensure it doesn't have accountCard / balance
    @Test
    public void testAddCredits() {

        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.ADMIN);

        final CafeteriaUser aCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber("DUMMY")
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        float expected1 = 10;
        aCafeteriaUser.addCredits(10);
        float result1 = aCafeteriaUser.getBalance();

        assertEquals(expected1, result1, 0.0);

        final CafeteriaUser anotherCafeteriaUser = new CafeteriaUserBuilder()
                .withMecanographicNumber("DUMMY")
                .withSystemUser(new SystemUser("dummy", "duMMy1", "dummy", "dummy", "a@b.ro", roles)).build();

        float expected2 = 0;
        anotherCafeteriaUser.addCredits(-10);
        float result2 = anotherCafeteriaUser.getBalance();

        assertEquals(expected2, result2, 0.0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMecanographicNumberEmployeeIsInvalidOnNumbers() throws Exception {
        new MecanographicNumber("1111");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMecanographicNumberEmployeeIsInvalidFor5Char() throws Exception {
        new MecanographicNumber("aaaaa");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMecanographicNumberEmployeeIsInvalidWithCharAndNumbers() throws Exception {
        new MecanographicNumber("aa11");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMecanographicNumberStudentsIsInvalidIfLessThan6Char() throws Exception {
        new MecanographicNumber("12345");
    }

    @Test(expected = IllegalArgumentException.class)
    public void ensureMecanographicNumberStudentsIsInvalidIfMoreThan6Char() throws Exception {
        new MecanographicNumber("1234567");
    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void ensureMecanographicNumberStudentsIsInvalidIfFirstTwoCharIsGreaterThatCurrentYear() throws Exception {
//        new MecanographicNumber("191234");
//    }
}
