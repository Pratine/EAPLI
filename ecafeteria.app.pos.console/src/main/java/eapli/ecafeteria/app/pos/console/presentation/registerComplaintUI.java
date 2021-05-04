/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.pos.RegisterComplaintController;
import eapli.ecafeteria.domain.pos.Complaint;
import eapli.ecafeteria.domain.pos.Message;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Utilizador
 */
public class registerComplaintUI extends AbstractUI {

    private final RegisterComplaintController controller = new RegisterComplaintController();

    @Override
    protected boolean doShow() {
        Meal meal = null;
        System.out.println("\n");
        final String message = Console.readLine("please describe what you've experienced");
        System.out.println("\n");

        final int flag = Console.readInteger("To insert the meal press 1.\nTo exit press 0\n");
        if (flag == 1) {
            final Date mealDate = Console.readDate("insert the meal date (YYYY/MM/DD)");
            if (controller.checkMeals(mealDate)) {
                meal = controller.chooseMeal();

                //guardar a msg e a meal sem numero
            } else {
                System.out.println("\n No meals found for that day\n");
                return false;
            }
        }

        int flag2 = Console.readInteger("To insert your mecanographic number press 1.\nTo exit press 0\n");
        int number = -1;
        if (flag2 == 1) {

            do {
                number = Console.readInteger("Insert your mecanographic number\n 0- Exit");

            } while (!controller.ValidadeUser(number) || number == 0);
            if (number == 0) {
                flag2 = 0;
            }
        }
        if (flag == 1 && flag2 == 1) {
            //complaint c meal e user
            Message msg = new Message(message);
            Complaint c = null;
            try {
                c = new Complaint(meal, msg, controller.getUser(number));
            } catch (IllegalArgumentException ex) {
                System.out.println("Um dos parâmetros introduzidos é inválido");
            }
            try {
                controller.registerComplaint(c);
                System.out.println("\ncomplaint registed with sucess\n");
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                System.out.println("\n\nJá existe uma reclamação para essa refeição\n");
            }

        } else if (flag == 1 && flag2 != 1) {
            // complaint so c meal
            Message msg = new Message(message);
            Complaint c = null;
            try {
                c = new Complaint(meal, msg);
            } catch (IllegalArgumentException ex) {
                System.out.println("Um dos parâmetros introduzidos é inválido");
            }
            try {
                controller.registerComplaint(c);
                System.out.println("\ncomplaint registed with sucess\n");
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                System.out.println("\n\n  Error registing the complaint");
            }
        } else if (flag != 1 && flag2 == 1) {
            //complaint so c utilizador
            Message msg = new Message(message);
            Complaint c = null;
            try {
                c = new Complaint(msg, controller.getUser(number));
            } catch (IllegalArgumentException ex) {
                System.out.println("Um dos parâmetros introduzidos é inválido");
            }
            try {
                controller.registerComplaint(c);
                System.out.println("\ncomplaint registed with sucess\n");
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                System.out.println("\n\n  Error registing the complaint");
            }
        } else {
            // complaint so c msg
            Message msg = new Message(message);
            Complaint c = null;
            try {
                c = new Complaint(msg);
            } catch (IllegalArgumentException ex) {
                System.out.println("Um dos parâmetros introduzidos é inválido");
            }
            try {
                controller.registerComplaint(c);
                System.out.println("\ncomplaint registed with sucess\n");
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                System.out.println("\n\n  Error registing the complaint");
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "MAKING A COMPLAINT";
    }

}
