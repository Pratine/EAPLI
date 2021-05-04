/**
 *
 */
package eapli.ecafeteria.persistence;

import eapli.framework.persistence.repositories.TransactionalContext;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the
     * repositories
     *
     * @return
     */
    TransactionalContext buildTransactionalContext();

    /**
     *
     * @param autoTx the transactional context to enrol
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();
    
    /**
     * repository will be created in auto transaction mode
     * @return 
    */
    BookingRepository bookings();
    
    
    DishTypeRepository dishTypes();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    CafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    CafeteriaUserRepository cafeteriaUsers();
    
    /**
     * repository will be created in auto transaction mode
     * 
     * @return 
     */
    MealExecutionRepository  mealsExecution();
    
    /**
     * repository will be created in auto transaction mode
     * 
     * @return 
     */
    CafeteriaShiftRepository cafeteriaShifts();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();

    DishRepository dishes();

    MaterialRepository materials();
    
    BatchRepository batches();
    
    POSRepository pos();
    
    DeliveryWorkSessionRepository dws();
    
    DeliveryRepository deliveries();

    /**
     * ************************
     * reporting
     *************************
     */
    /**
     * @return
     */
    DishReportingRepository dishReporting();
    
    MealRatingRepository ratings();
    
    CommentRepository comments();

    MealRepository meals();
    
    MovementsRepository cardMovements(TransactionalContext autoTx);

    MenuPlanRepository menuPlans(TransactionalContext TxCtx);

    public MenuRepository menus();
    
    MealPlanRepository mealPlans();

    public ComplaintRepository complaints();
    

}
