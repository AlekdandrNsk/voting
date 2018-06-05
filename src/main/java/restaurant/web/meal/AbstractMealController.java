package restaurant.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import restaurant.AuthorizedUser;
import restaurant.model.Meal;
import restaurant.repository.MealRepository;
import restaurant.to.MealWithExceed;
import restaurant.util.DateTimeUtil;
import restaurant.util.MealsUtil;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static restaurant.util.Util.orElse;


public abstract class AbstractMealController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealRepository repository;

    public Meal get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get meal {} for user {}", id, userId);
        return repository.get(id, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete meal {} for user {}", id, userId);
        repository.delete(id, userId);
    }

    public List<MealWithExceed> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for user {}", userId);
        return MealsUtil.getWithExceeded(repository.getAll(userId), AuthorizedUser.getCaloriesPerDay());
    }

    public Meal create(Meal meal) {
        int userId = AuthorizedUser.id();
        log.info("create {} for user {}", meal, userId);
        return repository.save(meal, userId);
    }

    public void update(Meal meal, int id) {
        int userId = AuthorizedUser.id();
        log.info("update {} for user {}", meal, userId);
        repository.save(meal, userId);
    }

    /**
     * <ol>Filter separately
     * <li>by date</li>
     * <li>by time for every date</li>
     * </ol>
     */
    public List<MealWithExceed> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = AuthorizedUser.id();
        log.info("getBetween dates({} - {}) time({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);

//        List<Meal> mealsDateFiltered = repository.getBetween(
//                orElse(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(DateTimeUtil.MIN_DATE, LocalTime.MIN))
//                , orElse(LocalDateTime.of(endDate, LocalTime.MAX), LocalDateTime.of(DateTimeUtil.MAX_DATE, LocalTime.MAX)), userId);

        List<Meal> mealsDateFiltered = repository.getBetween(
                LocalDateTime.of(orElse(startDate, DateTimeUtil.MIN_DATE), LocalTime.MIN),
                LocalDateTime.of(orElse(endDate, DateTimeUtil.MAX_DATE), LocalTime.MAX),
                userId);

        return MealsUtil.getFilteredWithExceeded(mealsDateFiltered, AuthorizedUser.getCaloriesPerDay(),
                orElse(startTime, LocalTime.MIN), orElse(endTime, LocalTime.MAX)
        );
    }
}



























