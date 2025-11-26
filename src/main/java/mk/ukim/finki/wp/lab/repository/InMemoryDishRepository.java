package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryDishRepository implements DishRepository {

    @Override
    public List<Dish> findAll() {
        return DataHolder.dishes;
    }

    @Override
    public Dish findByDishId(String dishId) {
        return DataHolder.dishes.stream()
                .filter(d -> d.getDishId().equals(dishId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return DataHolder.dishes.stream()
                .filter(d -> d.getId().equals(id))
                .findFirst();
    }

    @Override
    public Dish save(Dish dish) {
        // if dish has id and exists -> update
        if (dish.getId() != null) {
            Optional<Dish> existing = findById(dish.getId());
            if (existing.isPresent()) {
                // remove old and add updated (keep order simple)
                DataHolder.dishes.removeIf(d -> d.getId().equals(dish.getId()));
                DataHolder.dishes.add(dish);
                return dish;
            }
        }
        // new dish: ensure id assigned (Dish constructor without id does this)
        if (dish.getId() == null) {
            // create new Dish instance to ensure id increment - or assign manually
            Dish newDish = new Dish(dish.getDishId(), dish.getName(), dish.getCuisine(), dish.getPreparationTime());
            DataHolder.dishes.add(newDish);
            return newDish;
        } else {
            // id present but not found -> just add
            DataHolder.dishes.add(dish);
            return dish;
        }
    }

    @Override
    public void deleteById(Long id) {
        DataHolder.dishes.removeIf(d -> d.getId().equals(id));
    }
}
