package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Dish;

import java.util.List;

public interface DishService {
    List<Dish> listDishes();
    List<Dish> listDishes(Integer minRating);
    Dish findByDishId(String dishId);
    Dish findById(Long id);
    Dish create(String dishId, String name, String cuisine, int preparationTime, int rating);
    Dish create(String dishId, String name, String cuisine, int preparationTime, int rating, Long chefId);
    Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, int rating);
    Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, int rating, Long chefId);
    void delete(Long id);
}
