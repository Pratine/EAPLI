package eapli.ecafeteria.domain.pos;

import eapli.ecafetaria.domain.bookings.Booking;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author 1161016
 */
@Entity
public class Delivery implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private DeliveryWorkSession session;

    @OneToOne
    private Booking booking;

    protected Delivery() {
        //for ORM only
    }

    public Delivery(DeliveryWorkSession session, Booking booking) {
        this.session = session;
        this.booking = booking;
    }
    
    public DeliveryWorkSession deliveryWorkSession(){
        return session;
    }
    
    public Booking booking(){
        return booking;
    }
}
