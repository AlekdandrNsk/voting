package restaurant;

import org.springframework.test.web.servlet.ResultMatcher;
import restaurant.model.Role;
import restaurant.model.User;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class UserTestData {
    public static final int USER_ID = 100000;
    public static final int ADMIN_ID = 100001;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password",  Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", Role.ROLE_ADMIN, Role.ROLE_USER);



    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "meals", "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "meals", "password").isEqualTo(expected);
    }

//    public static ResultMatcher contentJson(User... expected) {
//        return content().json(writeIgnoreProps(Arrays.asList(expected), "registered", "password"));
//    }
//
//    public static ResultMatcher contentJson(User expected) {
//        return content().json(writeIgnoreProps(expected, "registered", "password"));
//    }
//
//    public static String jsonWithPassword(User user, String passw) {
//        return JsonUtil.writeAdditionProps(user, "password", passw);
//    }
}