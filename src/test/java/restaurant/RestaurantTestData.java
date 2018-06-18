package restaurant;

import restaurant.model.Restaurant;

public class RestaurantTestData {
    public static final int RESTAURANT_1_ID = 100002;
    public static final int RESTAURANT_2_ID = 100003;

    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_1_ID, "Restaurant1");
    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_2_ID, "Restaurant2");

    public static Restaurant getCreated() {
        return new Restaurant("Restaurant3");
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_2_ID, "Updated restaurant");
    }
}