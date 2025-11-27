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
        // ako dishot postoe -> update
        if (dish.getId() != null) {
            Optional<Dish> existing = findById(dish.getId());
            if (existing.isPresent()) {
                DataHolder.dishes.removeIf(d -> d.getId().equals(dish.getId()));
                DataHolder.dishes.add(dish);
                return dish;
            }
        }

        if (dish.getId() == null) {

            Dish newDish = new Dish(dish.getDishId(), dish.getName(), dish.getCuisine(), dish.getPreparationTime());
            DataHolder.dishes.add(newDish);
            return newDish;
        } else {
            // id-to postoe no ne e pronajdeno -> samo add
            DataHolder.dishes.add(dish);
            return dish;
        }
    }

    @Override
    public void deleteById(Long id) {
        //gi briseme onie jadenja koi imaat isto id
        DataHolder.dishes.removeIf(d -> d.getId().equals(id));
    }
}
