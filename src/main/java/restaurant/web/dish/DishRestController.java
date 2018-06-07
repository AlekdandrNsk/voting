package restaurant.web.dish;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restaurant.model.Dish;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/dishes")
public class DishRestController extends AbstractDishController{

    @CacheEvict(value = "dishes", allEntries = true)
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dish> getAllByDay(@RequestParam(value = "date", required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return super.getAllByDay(date);
    }

}
