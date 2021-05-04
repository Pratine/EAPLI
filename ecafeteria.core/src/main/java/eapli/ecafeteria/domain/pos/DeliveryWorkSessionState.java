package eapli.ecafeteria.domain.pos;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author PC
 */
@Embeddable
public class DeliveryWorkSessionState implements Serializable {
    
    protected DeliveryWorkSessionState(){
        //for ORM only
    }
    
    public SESSION_STATE dws_state = SESSION_STATE.CLOSED;
    
    public static enum SESSION_STATE{
        OPEN, CLOSED;
    }
}

