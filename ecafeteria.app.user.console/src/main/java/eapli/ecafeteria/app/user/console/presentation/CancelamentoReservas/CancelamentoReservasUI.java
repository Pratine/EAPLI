/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.CancelamentoReservas;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafeteria.app.common.console.presentation.sales.CardChargementUI;
import eapli.ecafeteria.domain.Movements;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import eaplic.ecafetaria.application.bookings.CancelamentoReservasController;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Santos <1150639@isep.ipp.pt>
 */
public class CancelamentoReservasUI extends AbstractUI {

    private final CancelamentoReservasController theController = new CancelamentoReservasController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        System.out.println("List of non canceled reservations");
        Iterable<Booking> reservasDisponiveis = theController.bookingsByUser();
        int indice = 0;
        for (Booking reservasDisponivel : reservasDisponiveis) {
            if (reservasDisponivel.bookingUndelivered() && !reservasDisponivel.bookingCanceled()) {
                indice++;
                System.out.println(indice + ")-----> Reserva: " + reservasDisponivel.toString());
            }
        }
        if (indice == 0) {
            System.out.println("You don´t have any reservatin");
        } else {

            int indiceSelecionado = Console.readInteger("\nSelect the index of the reservation for cancel!");
            if (indiceSelecionado <= indice && indiceSelecionado > 0) {
                List<Booking> array = new ArrayList<>();
                for (Booking b : reservasDisponiveis) {
                    array.add(b);
                }

                Booking r = array.get(indiceSelecionado - 1); // O array começa a 0! CUIDADO 

                String confirma = Console.readLine("Do you really want to cancel the reservation? Digit(yes/no)");
                if (confirma.equalsIgnoreCase("yes")) {

                    try {
                        theController.setBooking(r);
                        theController.cancelBooking(r);
                        theController.cardChargement(r, theController.getUserByNumber(), r.meal().price(), Calendar.getInstance());

                    } catch (Movements.NegativeAmountException ex) {
                        Logger.getLogger(CardChargementUI.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Error adding credits");
                        return false;
                    } catch (DataConcurrencyException ex) {
                        Logger.getLogger(CardChargementUI.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Error adding credits");
                        return false;
                    } catch (DataIntegrityViolationException ex) {
                        Logger.getLogger(CardChargementUI.class.getName()).log(Level.SEVERE, null, ex);
                        System.out.println("Error adding credits");
                        return false;
                    }

                    System.out.println("Reservation (" + r.toString() + ") canceled with success!");

                } else {
                    System.out.println("Reservation canceled!");
                }
            } else {
                System.out.println("Index incorrect!");
            }
        }

        return true;
    }

    @Override
    public String headline() {
        return "Booking cancelation was successful!";
    }

}
