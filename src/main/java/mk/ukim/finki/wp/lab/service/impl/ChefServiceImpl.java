package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.Gender;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChefServiceImpl implements ChefService {

    private final ChefRepository chefRepository;
    private final DishService dishService;

    public ChefServiceImpl(ChefRepository chefRepository, DishService dishService) {
        this.chefRepository = chefRepository;
        this.dishService = dishService;
    }

    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).orElse(null);
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {
        Optional<Chef> chefOpt = chefRepository.findById(chefId);
        if (chefOpt.isEmpty()) throw new IllegalArgumentException("Chef not found");
        Chef chef = chefOpt.get();
        Dish dish = dishService.findByDishId(dishId);
        if (dish == null) throw new IllegalArgumentException("Dish not found");
        dish.setChef(chef);
        dishService.update(dish.getId(), dish.getDishId(), dish.getName(), dish.getCuisine(), dish.getPreparationTime(), dish.getRating(), chefId);
        return chefRepository.findById(chefId).orElseThrow();
    }

    @Override
    public Chef create(String firstName, String lastName, String bio, Gender gender) {
        Chef chef = new Chef(null, firstName, lastName, bio, gender);
        return chefRepository.save(chef);
    }

    @Override
    public Chef update(Long id, String firstName, String lastName, String bio, Gender gender) {
        Optional<Chef> chefOpt = chefRepository.findById(id);
        if (chefOpt.isEmpty()) {
            throw new IllegalArgumentException("Chef not found");
        }
        Chef chef = chefOpt.get();
        chef.setFirstName(firstName);
        chef.setLastName(lastName);
        chef.setBio(bio);
        chef.setGender(gender);
        return chefRepository.save(chef);
    }

    @Override
    public void delete(Long id) {
        chefRepository.deleteById(id);
    }

    @Override
    public void deleteById(Long id) {
        chefRepository.deleteById(id);
    }
}