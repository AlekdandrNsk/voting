package restaurant.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restaurant.model.Dish;
import restaurant.repository.datajpa.DishRepository;

import java.time.LocalDate;
import java.util.List;

import static restaurant.web.dish.DishRestController.REST_URL;

@RestController
@RequestMapping(REST_URL)
public class DishRestController{
    static final String REST_URL = "/rest/dishes";

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishRepository repository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllByDay(@RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        LocalDate requiredDate = (date == null ? LocalDate.now() : date);
        log.info("getAll dishes by {}", requiredDate);
        return repository.findAllByDate(requiredDate);
    }

}
