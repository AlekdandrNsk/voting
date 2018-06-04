package restaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import restaurant.AuthorizedUser;
import restaurant.repository.MealRepository;
import restaurant.util.MealsUtil;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RootController {

    @Autowired
    private MealRepository repository1;

    @GetMapping("/")
    public String root() {
        return "redirect:meals";
    }

    @GetMapping("/users")
    public String users() {
        return "users";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @GetMapping("/meals")
    public String meals(Model model) {
        model.addAttribute("meals",
                MealsUtil.getWithExceeded(repository1.getAll(AuthorizedUser.id()), AuthorizedUser.getCaloriesPerDay()));
        return "meals";
    }
}
