/**
 *
 */
package eapli.framework.domain.time;

import java.util.Calendar;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import eapli.framework.domain.ddd.ValueObject;
import eapli.framework.util.DateTime;
import java.text.SimpleDateFormat;

/**
 * A persistent date period. only the date part of calendar is relevant.
 * DateIntervals are closed ranges
 *
 * @author Paulo Gandra Sousa
 *
 */
@Embeddable
public class DateInterval implements ValueObject {

    private static final long serialVersionUID = -3829067411069027990L;

    @Temporal(TemporalType.DATE)
    private final Calendar start;

    @Temporal(TemporalType.DATE)
    private final Calendar end;

    @Transient
    private final TimeRange period;

    /**
     * constructs a closed range between start and end
     *
     * @param start
     * @param end
     */
    public DateInterval(Calendar start, Calendar end) {
        if (start == null || end == null) {
            throw new IllegalArgumentException();
        }
        this.start = DateTime.datePart(start);
        this.end = DateTime.datePart(end);
        period = new TimeRange(this.start, this.end);
    }

    /**
     * constructs a closed range starting at start ad infinitum
     *
     * @param start
     */
    public DateInterval(Calendar start) {
        if (start == null) {
            throw new IllegalArgumentException();
        }
        this.start = DateTime.datePart(start);
        end = null;
        period = new TimeRange(this.start);
    }

    public DateInterval() {
        // ORM
        start = end = null;
        period = null;
    }

    public Calendar start() {
        return start;
    }

    public Calendar end() {
        return end;
    }

    public boolean includes(Calendar target) {
        return period.includes(target);
    }

    public boolean valideDate(Calendar began, Calendar end) {
        if (start.compareTo(began) >= 0 && end.compareTo(end) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean validePeriod() {
        return start.compareTo(end) <= 0;
    }

    public String print() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = format1.format(start.getTime());
        return "Start: " + date1 + "\n";
    }
}
