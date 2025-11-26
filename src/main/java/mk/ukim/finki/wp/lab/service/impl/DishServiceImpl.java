package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {
    private final DishRepository dishRepository;

    public DishServiceImpl(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
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
    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime) {
        Optional<Dish> existing = this.dishRepository.findById(id);
        if (existing.isEmpty()) {
            throw new IllegalArgumentException("Dish not found");
        }
        Dish updated = new Dish(id, dishId, name, cuisine, preparationTime);
        return this.dishRepository.save(updated);
    }

    @Override
    public void delete(Long id) {
        this.dishRepository.deleteById(id);
    }
}
