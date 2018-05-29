package restaurant.web;

import org.slf4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import restaurant.util.UserUtil;
import restaurant.web.user.UserRestController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends javax.servlet.http.HttpServlet {
    private static final Logger log = getLogger(RestaurantServlet.class);

    private UserRestController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        WebApplicationContext springContext = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        controller = springContext.getBean(UserRestController.class);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");

        //request.setAttribute("restaurants", repository.getMENU());
        request.setAttribute("users", controller.getAll());
        request.getRequestDispatcher("/users.jsp").forward(request, response);
    }
}
