package restaurant.model;

import java.time.LocalDateTime;

public class Dish extends AbstractNamedEntity{

    private Restaurant restaurant;

    private Float price;

    private LocalDateTime dateTime;
}
