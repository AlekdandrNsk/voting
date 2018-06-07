package restaurant.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import restaurant.model.Dish;
import restaurant.repository.datajpa.DishRepository;

import java.time.LocalDate;
import java.util.List;

public abstract class AbstractDishController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishRepository repository;

    public List<Dish> getAllByDay(@RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate requiredDate = (date == null ? LocalDate.now() : date);
        log.info("getAll dishes by {}", requiredDate);
        return repository.findAllByDate(requiredDate);
    }

    public Dish save(Dish dish) {
        log.info("create {}", dish);
        return repository.save(dish);
    }

    public void update(@RequestBody Dish dish, @PathVariable("id") int id) {
        log.info("update {} with id={}", dish, id);
        repository.save(dish);
    }

    public void delete(@PathVariable("id") int id) {
        log.info("delete dish {}", id);
        repository.delete(id);
    }

    public Dish get(@PathVariable("id") int id) {
        log.info("get dish {}", id);
        return repository.findById(id).orElse(null);
    }

}
