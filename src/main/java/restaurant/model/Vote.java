package restaurant.model;

import java.time.LocalDateTime;

public class Vote extends AbstractBaseEntity{

    private User user;

    private LocalDateTime dateTime;

    private Restaurant restaurant;
}
