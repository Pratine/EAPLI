package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.pos.DeliveryWorkSession;
import eapli.ecafeteria.domain.pos.DeliveryWorkSessionState;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.DeliveryWorkSessionRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.cafeteria.domain.cafeteriashift.CafeteriaShift;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.persistence.CafeteriaShiftRepository;
import java.util.Calendar;
import java.util.Optional;

/**
 *
 * @author PC
 */
public class OpenPOSController implements Controller {

    private final DeliveryWorkSessionRepository repository = PersistenceContext.repositories().dws();
    private final POSRepository POSrepository = PersistenceContext.repositories().pos();
    private final CafeteriaShiftRepository CafShiftRepository = PersistenceContext.repositories().cafeteriaShifts();

    /**
     * Opens a DeliveryWorkSession
     *
     * @param numberPOS number of the POS to be opened
     * @param cal the time it was opened
     * @return the work session created
     * @throws DataIntegrityViolationException
     * @throws DataConcurrencyException
     */
    public DeliveryWorkSession openPOS(Integer numberPOS, Calendar cal) throws DataIntegrityViolationException, DataConcurrencyException {
        //Encontrar o pos com o numero POS
        //ver worksessions se alguma esta aberta naquele POS
        //caso esteja retorna null, senao retorna a ws

        int nOpenedPOS = numberOfOpenedPOS();

        DeliveryWorkSession ws = null;
        boolean canOpen = true;
        Optional<POS> pos = POSrepository.findByNumber(numberPOS);
        if (pos != null) {
            Iterable<DeliveryWorkSession> dwss = repository.findAll();
            for (DeliveryWorkSession d : dwss) {
                if (d.pos().equals(pos.get())) {
                    if (d.state() == DeliveryWorkSessionState.SESSION_STATE.OPEN) {
                        canOpen = false;
                    }
                }
            }
        }

        if (canOpen && pos.get() != null) {
            ws = new DeliveryWorkSession(pos.get(), cal);
            if (nOpenedPOS == 0) {
                CafeteriaShift cs = new CafeteriaShift(cal, ws.mealType());
                this.CafShiftRepository.save(cs);
            }
            return this.repository.save(ws);
        }
        
        return null;
    }

    /**
     *
     * @return the number of opened POS's
     */
    private int numberOfOpenedPOS() {
        int count = 0;
        Iterable<DeliveryWorkSession> dwss = repository.findAll();
        for (DeliveryWorkSession dws : dwss) {
            if (dws.state() == DeliveryWorkSessionState.SESSION_STATE.OPEN) {
                count++;
            }
        }
        return count;
    }
}
