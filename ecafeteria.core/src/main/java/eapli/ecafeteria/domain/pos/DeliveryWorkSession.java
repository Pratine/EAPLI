package eapli.ecafeteria.domain.pos;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.Version;

/**
 *
 * @author PC
 */
@Entity
public class DeliveryWorkSession implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private final POS pos;
    //@OneToOne
    @Embedded
    private DeliveryWorkSessionState session_state = new DeliveryWorkSessionState();

    //@OneToOne
    private MealType mealType;

    private static final int HOUR_LIMIT = 15;

    @Temporal(javax.persistence.TemporalType.DATE)
    private final Calendar cal;

    protected DeliveryWorkSession() {
        pos = null;
        cal = null;
        //for ORM only (need to be null)
    }

    /*
    QUEM IRÁ INSTANCIAR ESTA CLASSE SERÁ UM CONTROLLER, QUE TEM DE VERIFICAR QUE NÃO HÁ NENHUMA SESSAO ATIVA NESTE POS
    
     */
    public DeliveryWorkSession(POS pos, Calendar cal) {
        this.cal = cal;
        if (cal.get(Calendar.HOUR_OF_DAY) >= HOUR_LIMIT) {
            mealType = MealType.DINNER;
        } else {
            mealType = MealType.LUNCH;
        }

        this.pos = pos;
        this.session_state.dws_state = DeliveryWorkSessionState.SESSION_STATE.OPEN;
    }

    public void closePOS() {
        session_state.dws_state = DeliveryWorkSessionState.SESSION_STATE.CLOSED;
    }

    public Integer id() {
        return id;
    }

    public POS pos() {
        return pos;
    }

    public DeliveryWorkSessionState.SESSION_STATE state() {
        return session_state.dws_state;
    }

    public Calendar cal() {
        return cal;
    }
    
    public MealType mealType(){
        return mealType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof POS)) {
            return false;
        }

        final DeliveryWorkSession other = (DeliveryWorkSession) o;
        return id == other.id();
    }
}
