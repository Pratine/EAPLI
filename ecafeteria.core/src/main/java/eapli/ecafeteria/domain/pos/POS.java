package eapli.ecafeteria.domain.pos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author PC
 */
@Entity
public class POS implements Serializable {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    
    private int number;

    public POS(int number) {
        this.number = number;
    }

    protected POS() {
        //for ORM only
    }
    
    public int id(){
        return id;
    }
    
    public int number(){
        return number;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof POS)) {
            return false;
        }

        final POS other = (POS) o;
        return number==other.number();
    }
}

