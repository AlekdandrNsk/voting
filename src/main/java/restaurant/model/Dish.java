package restaurant.model;

import java.time.LocalDate;

public class Dish extends AbstractNamedEntity{

    private String restaurant;

    private Double price;

    private LocalDate date;

    public Dish(Integer id, String name, String restaurant, Double price, LocalDate date) {
        super(id, name);
        this.restaurant = restaurant;
        this.price = price;
        this.date = date;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        this.restaurant = restaurant;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
