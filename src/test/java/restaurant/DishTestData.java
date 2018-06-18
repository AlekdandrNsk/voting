package restaurant;

import restaurant.model.Dish;

import java.time.LocalDate;

import static restaurant.RestaurantTestData.*;

public class DishTestData {
    public static final int DISH_1_ID = 100010;
    public static final int DISH_2_ID = 100011;
    public static final int DISH_3_ID = 100012;
    public static final int DISH_4_ID = 100013;
    public static final int DISH_5_ID = 100014;
    public static final int DISH_6_ID = 100015;

    public static final LocalDate DATEOFVOTE = LocalDate.of(2018, 6, 7);

    public static final Dish DISH_1 = new Dish(DISH_1_ID, "soup1", RESTAURANT_1, 50099, DATEOFVOTE);
    public static final Dish DISH_2 = new Dish(DISH_2_ID, "salad1", RESTAURANT_1, 50099, DATEOFVOTE);
    public static final Dish DISH_3 = new Dish(DISH_3_ID, "coffee1", RESTAURANT_1, 50099, DATEOFVOTE);
    public static final Dish DISH_4 = new Dish(DISH_4_ID, "soup2", RESTAURANT_2, 50099, DATEOFVOTE);
    public static final Dish DISH_5 = new Dish(DISH_5_ID, "salad2", RESTAURANT_2, 50099, DATEOFVOTE);
    public static final Dish DISH_6 = new Dish(DISH_6_ID, "coffee2", RESTAURANT_2, 50099, DATEOFVOTE);

    public static Dish getCreated() {
        return new Dish("Created Dish", RESTAURANT_1, 50099, DATEOFVOTE);
    }

    public static Dish getUpdated() {
        return new Dish(DISH_3_ID, "Updated Dish", RESTAURANT_2, 9966, DATEOFVOTE);
    }

}