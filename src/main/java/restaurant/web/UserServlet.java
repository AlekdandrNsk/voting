package restaurant.web;

import org.slf4j.Logger;
import restaurant.util.UserUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends javax.servlet.http.HttpServlet {
    private static final Logger log = getLogger(UserServlet.class);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to users");

        request.setAttribute("users", UserUtil.USERS);
        request.getRequestDispatcher("/users.jsp").forward(request, response);
          }
}
