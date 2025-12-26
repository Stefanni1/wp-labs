package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.Gender;
import mk.ukim.finki.wp.lab.model.User;
import mk.ukim.finki.wp.lab.model.enums.Role;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Chef> chefs = null;
    public static List<Dish> dishes = null;
    public static List<User> users = null;

    private final ChefRepository chefRepository;
    private final DishRepository dishRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(ChefRepository chefRepository, 
                     DishRepository dishRepository,
                     UserRepository userRepository,
                     PasswordEncoder passwordEncoder) {
        this.chefRepository = chefRepository;
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        if (chefRepository.findAll().isEmpty()) {
            chefs = new ArrayList<>();
            chefs.add(new Chef(null, "Gordon", "Ramsay", "World-renowned chef with 16 Michelin stars", Gender.MALE));
            chefs.add(new Chef(null, "Jamie", "Oliver", "British chef known for simple and healthy cooking", Gender.MALE));
            chefs.add(new Chef(null, "Wolfgang", "Puck", "Austrian-born American chef and restaurateur", Gender.MALE));
            chefs.add(new Chef(null, "Marco", "Pierre White", "First British chef to be awarded three Michelin stars", Gender.MALE));
            chefs.add(new Chef(null, "Heston", "Blumenthal", "Innovative chef specializing in molecular gastronomy", Gender.MALE));
            chefRepository.saveAll(chefs);
        }

        if (dishRepository.findAll().isEmpty()) {
            dishes = new ArrayList<>();
            List<Chef> savedChefs = chefRepository.findAll();
            if (!savedChefs.isEmpty()) {
                dishes.add(new Dish("dish1", "Pasta Carbonara", "Italian", 30, 5));
                dishes.get(0).setChef(savedChefs.get(0));
                dishes.add(new Dish("dish2", "Beef Wellington", "British", 45, 5));
                dishes.get(1).setChef(savedChefs.get(1));
                dishes.add(new Dish("dish3", "Chicken Tikka Masala", "Indian", 35, 4));
                dishes.get(2).setChef(savedChefs.get(2));
                dishes.add(new Dish("dish4", "Sushi Platter", "Japanese", 40, 5));
                dishes.get(3).setChef(savedChefs.get(3));
                dishes.add(new Dish("dish5", "Beef Bourguignon", "French", 60, 4));
                dishes.get(4).setChef(savedChefs.get(4));
            } else {
                dishes.add(new Dish("dish1", "Pasta Carbonara", "Italian", 30, 5));
                dishes.add(new Dish("dish2", "Beef Wellington", "British", 45, 5));
                dishes.add(new Dish("dish3", "Chicken Tikka Masala", "Indian", 35, 4));
                dishes.add(new Dish("dish4", "Sushi Platter", "Japanese", 40, 5));
                dishes.add(new Dish("dish5", "Beef Bourguignon", "French", 60, 4));
            }
            dishRepository.saveAll(dishes);
        }

        if (userRepository.findAll().isEmpty()) {
            users = new ArrayList<>();
            users.add(new User(
                    "elena.atanasoska",
                    passwordEncoder.encode("ea"),
                    "Elena",
                    "Atanasoska",
                    Role.ROLE_USER));
            users.add(new User(
                    "darko.sasanski",
                    passwordEncoder.encode("ds"),
                    "Darko",
                    "Sasanski",
                    Role.ROLE_USER));
            users.add(new User(
                    "ana.todorovska",
                    passwordEncoder.encode("at"),
                    "Ana",
                    "Todorovska",
                    Role.ROLE_USER));
            users.add(new User(
                    "admin",
                    passwordEncoder.encode("admin"),
                    "admin",
                    "admin",
                    Role.ROLE_ADMIN));
            userRepository.saveAll(users);
        }
    }
}