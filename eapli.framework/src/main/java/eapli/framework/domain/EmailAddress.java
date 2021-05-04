/**
 *
 */
package eapli.framework.domain;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.Embeddable;

import eapli.framework.domain.ddd.ValueObject;
import eapli.framework.util.Strings;

/**
 * @author Paulo Gandra Sousa
 */
@Embeddable
public class EmailAddress implements ValueObject, Serializable {

    private static final long serialVersionUID = 1L;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    private final String email;

    protected EmailAddress(final String address) {
        if (Strings.isNullOrEmpty(address)) {
            throw new IllegalArgumentException("email address should neither be null nor empty");
        }
        final Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(address);
        if (!matcher.find()) {
            throw new IllegalArgumentException("Invalid E-mail");
        }

        email = address;
    }

    protected EmailAddress() {
        // for ORM
        email = "";
    }

    public static EmailAddress valueOf(final String address) {
        return new EmailAddress(address);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmailAddress)) {
            return false;
        }

        final EmailAddress that = (EmailAddress) o;

        return email.equals(that.email);

    }

    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return email;
    }
}
