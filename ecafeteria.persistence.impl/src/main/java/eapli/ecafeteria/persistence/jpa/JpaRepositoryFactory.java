package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.persistence.BatchRepository;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaShiftRepository;
import eapli.ecafeteria.persistence.CommentRepository;
import eapli.ecafeteria.persistence.ComplaintRepository;
import eapli.ecafeteria.persistence.DeliveryWorkSessionRepository;
import eapli.ecafeteria.persistence.DishReportingRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.MealExecutionRepository;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.ecafeteria.persistence.MealRatingRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.MessageRepository;
import eapli.ecafeteria.persistence.MovementsRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 *
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(TransactionalContext autoTx) {
        return new JpaUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public DishTypeRepository dishTypes() {
        return new JpaDishTypeRepository();
    }

    @Override
    public JpaCafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx) {
        return new JpaCafeteriaUserRepository(autoTx);
    }

    @Override
    public JpaCafeteriaUserRepository cafeteriaUsers() {
        return new JpaCafeteriaUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public DishRepository dishes() {
        return new JpaDishRepository();
    }

    @Override
    public MaterialRepository materials() {
        return new JpaMaterialRepository();
    }

    @Override
    public TransactionalContext buildTransactionalContext() {
        return JpaAutoTxRepository
                .buildTransactionalContext(Application.settings().getPersistenceUnitName(), Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public DishReportingRepository dishReporting() {
        return new JpaDishReportingRepository();
    }

    @Override
    public POSRepository pos() {
        return new JpaPOSRepository();
    }

    @Override
    public BookingRepository bookings() {
        return new JpaBookingRepository();
    }

    @Override
    public MealRepository meals() {
        return new JpaMealRepository();
    }

    @Override
    public DeliveryWorkSessionRepository dws() {
        return new JpaDeliveryWorkSessionRepository();
    }

    @Override
    public JpaDeliveryRepository deliveries() {
        return new JpaDeliveryRepository();
    }

    @Override
    public MealRatingRepository ratings() {
        return new JpaRatingRepository();
    }

    @Override
    public CommentRepository comments() {
        return new JpaCommentRepository();
    }

    @Override
    public MealExecutionRepository mealsExecution() {
        return new JpaMealsExecutionRepository();
    }

    @Override
    public CafeteriaShiftRepository cafeteriaShifts() {
        return new JpaCafeteriaShiftRepository();
    }

    @Override
    public MovementsRepository cardMovements(TransactionalContext autoTx) {
        return new JpaMovementsRepository(autoTx);
    }

    @Override
    public MenuPlanRepository menuPlans(TransactionalContext TxCtx) {
        return new JpaMenuPlanRepository();
    }

    @Override
    public MenuRepository menus() {
        return new JpaMenuRepository();
    }

    public ComplaintRepository complaints() {
        return new JpaComplaintRepository();
    }

    public MessageRepository messages() {
        return new JpaMessageRepository();
    }

    @Override
    public BatchRepository batches() {
        return new JpaBatchRepository();
    }

    @Override
    public MealPlanRepository mealPlans() {
        return new JpaMealPlanRepository();
    }
}
