package restaurant.web;

import org.slf4j.Logger;
import restaurant.util.DishUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class RestaurantServlet extends HttpServlet {
    private static final Logger log = getLogger(RestaurantServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to restaurants");

        request.setAttribute("restaurants",DishUtil.MENU);
        request.getRequestDispatcher("/restaurants.jsp").forward(request, response);
    }
}
