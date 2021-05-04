/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.application.ratings;

import eapli.ecafetaria.domain.bookings.Booking;
import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.pos.MealType;
import eapli.ecafeteria.domain.rateMeal.Comment;
import eapli.ecafeteria.domain.rateMeal.Rating;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.CommentRepository;
import eapli.ecafeteria.persistence.MealRatingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.Console;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Utilizador
 */
public class RateMealController {

    private final MealRatingRepository ratingRepo = PersistenceContext.repositories().ratings();
    private final BookingRepository bookingRepo = PersistenceContext.repositories().bookings();
    private final CafeteriaUserRepository userRepo = PersistenceContext.repositories().cafeteriaUsers();
    private final CommentRepository commentRepo = PersistenceContext.repositories().comments();

    private CafeteriaUser user;
    private Rating rating;
    private Comment comment;
    private List<Booking> bookings;
    private Booking booking;

    public CafeteriaUser getUser() {
        return userRepo.findByUsername(AuthorizationService.session().authenticatedUser().username()).get();
    }

    public void setUser(CafeteriaUser user) {
        this.user = user;
    }

    public boolean checkBooking(Date mealDate, MealType mealType) {
        List<Booking> bookings = bookingRepo.checkBooking(user, mealType, mealDate, Booking.BookingState.Delivered);
        if (bookings.isEmpty()) {
            System.out.println("No bookings found for the day "+mealDate+" for the "+mealType);
            return false;

        } else {
            System.out.println("\n" + bookings.toString() + "\n");
            this.bookings=bookings;
            return true;
        }
    }
    
    public Booking chooseBooking(List<Booking> bookings){
        final int choise;
       // System.out.println("choose the bookings (insert the number)");
        for(int i=0;i<bookings.size();i++){
            System.out.println(""+i+". "+bookings.get(i).toString());
            
        }
        choise=Console.readInteger("choose the booking (insert the number)");
        this.booking=bookings.get(choise);
        return this.booking;
    }

//    public Booking getBooking(Date mealDate, MealType mealType) {
//        Booking bookings = bookingRepo.checkBooking(user, mealType, mealDate, Booking.BookingState.Delivered);
//        checkBooking(
//            return bookings;
//        }
//    }

//    public Rating newRating (Date mealDate,MealType mealType,int rate,int flag){
//        Rating rating = new Rating();
//        return rating;
//        
//    }

    public Rating registerRating(Meal meal,int rating) throws DataConcurrencyException, DataIntegrityViolationException{
//        this.rating=new Rating(user, mealDate, mealType, rating);    
            this.rating=new Rating(user, meal, rating);
          return  ratingRepo.save(this.rating);
             
         
    
    }
    
    public Comment registerComment(String comment,Rating rating) throws DataConcurrencyException, DataIntegrityViolationException{
        Comment com =new Comment(comment, rating);
        return commentRepo.save(com);
    }
    public List<Booking> getBookings(){
        return this.bookings;
    }
    public Booking getBooking(){
        return this.booking;
    }
    
}
