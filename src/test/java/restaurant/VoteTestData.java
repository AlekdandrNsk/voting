package restaurant;

import restaurant.model.Vote;

import java.time.LocalDate;

import static restaurant.RestaurantTestData.RESTAURANT_1;
import static restaurant.RestaurantTestData.RESTAURANT_2;
import static restaurant.UserTestData.USER;

public class VoteTestData {
    public static final int VOTE_1_ID = 100017;
    public static final int VOTE_2_ID = 100019;

    public static final LocalDate DATEOFVOTE = LocalDate.of(2018, 6, 7);

    public static final Vote VOTE_1 = new Vote(VOTE_1_ID, DATEOFVOTE, RESTAURANT_1);
    public static final Vote VOTE_2 = new Vote(VOTE_2_ID, DATEOFVOTE, RESTAURANT_2);

    public static Vote getCreated() {
        return new Vote(USER, LocalDate.now(), RESTAURANT_2);
    }

    public static Vote getUpdated() {
        return new Vote(VOTE_1_ID, USER, LocalDate.now(), RESTAURANT_2);
    }
}