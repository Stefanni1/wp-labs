package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;
    private final ChefRepository chefRepository;

    public DishServiceImpl(DishRepository dishRepository, ChefRepository chefRepository) {
        this.dishRepository = dishRepository;
        this.chefRepository = chefRepository;
    }

    @Override
    public List<Dish> listDishes() {
        return this.dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return this.dishRepository.findByDishId(dishId);
    }

    @Override
    public Dish findById(Long id) {
        return this.dishRepository.findById(id).orElse(null);
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime) {
        Dish dish = new Dish(dishId, name, cuisine, preparationTime);
        return this.dishRepository.save(dish);
    }

    @Override
    public Dish create(String dishId, String name, String cuisine, int preparationTime, Long chefId) {
        Dish dish = new Dish(dishId, name, cuisine, preparationTime);
        if (chefId != null) {
            Optional<Chef> chef = chefRepository.findById(chefId);
            chef.ifPresent(dish::setChef);
        }
        return this.dishRepository.save(dish);
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime) {
        Optional<Dish> existing = this.dishRepository.findById(id);
        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Dish not found");
        }
        Dish dish = existing.get();
        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);
        return this.dishRepository.save(dish);
    }

    @Override
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime, Long chefId) {
        Optional<Dish> existing = this.dishRepository.findById(id);
        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Dish not found");
        }
        Dish dish = existing.get();
        dish.setDishId(dishId);
        dish.setName(name);
        dish.setCuisine(cuisine);
        dish.setPreparationTime(preparationTime);
        if (chefId != null) {
            Optional<Chef> chef = chefRepository.findById(chefId);
            chef.ifPresent(dish::setChef);
        } else {
            dish.setChef(null);
        }
        return this.dishRepository.save(dish);
    }

    @Override
    public void delete(Long id) {
        this.dishRepository.deleteById(id);
    }
}
