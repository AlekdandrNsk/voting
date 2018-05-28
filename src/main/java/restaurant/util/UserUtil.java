package restaurant.util;

import restaurant.model.Role;
import restaurant.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class UserUtil {
    public static final List<User> USERS = Arrays.asList(
            new User(100001, "User", "user@mail.com", "user", Role.ROLE_ADMIN),
            new User(100002, "Admin", "admin@mail.com", "admin", Role.ROLE_USER)
    );
}
