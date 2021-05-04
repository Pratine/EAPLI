package eapli.ecafeteria.application.pos;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.domain.pos.Delivery;
import eapli.ecafeteria.domain.pos.DeliveryWorkSession;
import eapli.ecafeteria.domain.pos.DeliveryWorkSessionState;
import eapli.ecafeteria.persistence.DeliveryRepository;
import eapli.ecafeteria.persistence.DeliveryWorkSessionRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author PC
 */
public class ClosePOSController implements Controller {

    private final DeliveryWorkSessionRepository repository = PersistenceContext.repositories().dws();
    private final DeliveryRepository deliveryRepository = PersistenceContext.repositories().deliveries();

    /**
     * Closes all DeliveryWorkSessions of this POS that are still open.
     *
     * Fecho de caixa – termina as operações para o turno actual (almoço ou
     * jantar) sendo apresentado um sumário dos pratos entregues. Faz logout do
     * utilizador. Após fecho do caixa não é possível registar mais operações
     * associadas à refeição em causa.
     *
     * @param dws the session to close
     * @return true if closed successfully
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     *
     *
     */
    public boolean closePOS(DeliveryWorkSession dws) throws DataConcurrencyException, DataIntegrityViolationException {

        if (dws.state() == DeliveryWorkSessionState.SESSION_STATE.OPEN) {
            dws.closePOS();
            repository.save(dws);
            return true;
        }
        return false;
    }

    //import eapli.cafeteria.app.common.console.presentation.authz.LogoutAction;
    // LogoutAction logoutAction = new LogoutAction();
    // logoutAction.execute();
    //logout pela consola (?)
    public ArrayList<DeliveryWorkSession> openedPOSsSessions() {
        ArrayList<DeliveryWorkSession> list = new ArrayList<>();

        Iterable<DeliveryWorkSession> dwss = repository.findAll();
        for (DeliveryWorkSession d : dwss) {
            if (d.state() == DeliveryWorkSessionState.SESSION_STATE.OPEN) {
                list.add(d);
            }
        }

        return list;
    }

    /**
     * Finds and returns the number of deliverys of each meal during that
     * seassion
     *
     * @param dws the session
     * @return the number of deliverys of each meal during that seassion
     */
    public Map<Meal, Integer> deliveredMeals(DeliveryWorkSession dws) {
        Map<Meal, Integer> delivered = new HashMap<>();

        Iterable<Delivery> deliveries = deliveryRepository.findByDeliveryWorkSession(dws);

        boolean exists;
        for (Delivery d : deliveries) {
            exists = false;
            if (d.booking().state() == Booking.BookingState.Delivered) {
                for (Entry<Meal, Integer> pair : delivered.entrySet()) {
                    if (pair.getKey().equals(d.booking().meal())) {
                        exists = true;
                        int nEntregas = pair.getValue();
                        pair.setValue(nEntregas + 1);
                    }
                }
                if (!exists) {
                    delivered.put(d.booking().meal(), 1);
                }
            }

        }

        return delivered;
    }
}
