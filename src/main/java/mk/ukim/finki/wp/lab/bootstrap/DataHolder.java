package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.Gender;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();


    //tuka dodavame + polot na chef
    @PostConstruct
    public void init() {
        chefs.add(new Chef(1L, "Gordon", "Ramsay", "World-renowned chef with 16 Michelin stars", Gender.MALE));
        chefs.add(new Chef(2L, "Jamie", "Oliver", "British chef known for simple and healthy cooking", Gender.MALE));
        chefs.add(new Chef(3L, "Wolfgang", "Puck", "Austrian-born American chef and restaurateur", Gender.MALE));
        chefs.add(new Chef(4L, "Marco", "Pierre White", "First British chef to be awarded three Michelin stars", Gender.MALE));
        chefs.add(new Chef(5L, "Heston", "Blumenthal", "Innovative chef specializing in molecular gastronomy", Gender.MALE));

        dishes.add(new Dish("dish1", "Pasta Carbonara", "Italian", 30));
        dishes.add(new Dish("dish2", "Beef Wellington", "British", 45));
        dishes.add(new Dish("dish3", "Chicken Tikka Masala", "Indian", 35));
        dishes.add(new Dish("dish4", "Sushi Platter", "Japanese", 40));
        dishes.add(new Dish("dish5", "Beef Bourguignon", "French", 60));
    }
}