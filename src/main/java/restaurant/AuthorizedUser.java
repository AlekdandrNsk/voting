package restaurant;


import restaurant.model.AbstractBaseEntity;

import static restaurant.util.UserUtil.DEFAULT_CALORIES_PER_DAY;

public class AuthorizedUser {
    private static int id = AbstractBaseEntity.START_SEQ;

    public static int id() {
        return id;
    }

    public static void setId(int id) {
        AuthorizedUser.id = id;
    }

    public static int getCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }
}