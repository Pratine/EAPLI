/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.common.console.presentation.sales;

import eapli.ecafeteria.application.sales.CardChargementController;
import eapli.ecafeteria.domain.Movements;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VitorCardoso(1161895
 */
public class CardChargementUI extends AbstractUI {

    private final CardChargementController theController = new CardChargementController();
    private final Calendar date = new GregorianCalendar();
    private CafeteriaUser cUser;

    protected Controller controller() {
        return this.theController;
    }

    public CardChargementUI() {
    }

    @Override
    protected boolean doShow() {
        Scanner s = new Scanner(System.in);
        final String mecanographicNumber = Console.readLine("User's mecanographic number:");
        MecanographicNumber num = new MecanographicNumber(mecanographicNumber);
        try {
            cUser = theController.getUserByNumber(num);
        } catch (NoSuchElementException e) {
            System.out.println("No User found with this mecanographic number!");
            return false;
        }
        System.out.println("Amount to charge:");
        final float amount = s.nextFloat();
        try {
            theController.cardChargement(cUser, amount, date);
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
        System.out.println("Credits added sucessfully");
        return true;
    }

    @Override
    public String headline() {
        return "Card Chargement";
    }

}
