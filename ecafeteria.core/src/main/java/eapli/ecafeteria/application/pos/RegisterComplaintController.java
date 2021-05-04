/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.pos.Complaint;
import eapli.ecafeteria.domain.pos.Message;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.ComplaintRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MessageRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Utilizador
 */
public class RegisterComplaintController {

    private final MealRepository mealRepo = PersistenceContext.repositories().meals();
    private final CafeteriaUserRepository userRepo = PersistenceContext.repositories().cafeteriaUsers();
    private final ComplaintRepository complaintRepo = PersistenceContext.repositories().complaints();
    //  private final MessageRepository msgRepo = PersistenceContext.repositories().messages();
    private CafeteriaUser user;
    private Complaint complaint;
    private Message message;
    private List<Meal> meals;
    private Meal meal;

    public boolean checkMeals(Date mealDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(mealDate);
        List<Meal> meals = (List<Meal>) mealRepo.findByDate(cal);
        if (meals.isEmpty()) {
            return false;
        } else {
            this.meals = meals;
            return true;
        }
    }

    public Meal chooseMeal() {
        final int choise;
        for (int i = 0; i < meals.size(); i++) {
            System.out.println("" + i + ". " + meals.get(i).toString());
        }
        choise = Console.readInteger("choose the meal (insert the number");
        return this.meal = meals.get(choise);
    }

    public CafeteriaUser getUser(int number) {
        MecanographicNumber mn = new MecanographicNumber(Integer.toString(number));
        if (userRepo.findByMecanographicNumber(mn).isPresent()) {
            return userRepo.findByMecanographicNumber(mn).get();

        } else {
            return null;
        }
    }

    public boolean ValidadeUser(int number) {
        MecanographicNumber mn = new MecanographicNumber(Integer.toString(number));
        return userRepo.findByMecanographicNumber(mn).isPresent();

    }

    public void registerComplaint(Complaint c) throws DataConcurrencyException, DataIntegrityViolationException {

        // c.setMessage(msgRepo.save(c.message()));
        complaintRepo.save(c);

    }

    public void addMeal() {
        this.complaint.addMeal(meal);
    }

    public void addUser() {
        this.complaint.addUser(user);
    }

}
