package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.bootstrap.DataHolder;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.model.Gender;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Chef chef = findById(chefId);
        if (chef == null) throw new IllegalArgumentException("Chef not found");
        Dish dish = dishService.findByDishId(dishId);
        if (dish == null) throw new IllegalArgumentException("Dish not found");
        chef.getDishes().add(dish);
        return chefRepository.save(chef);
    }
    @Override
    public Chef create(String firstName, String lastName, String bio, Gender gender) {
        //so ova generirame nov ID za nov chef
        Long newId = DataHolder.chefs.isEmpty() ? 1L : //ako listata e prazna prviot id ke bide 1
                DataHolder.chefs.get(DataHolder.chefs.size() - 1).getId() + 1; //ako ne e prazna ja gledame koja brojka ni e posledna i pravime taa brojka +1
        //kreirame nov objekt Chef so site podastoci + novoto ID
        Chef chef = new Chef(newId, firstName, lastName, bio, gender);
        //tuka go dodavame vo nasiot Dataholder
        DataHolder.chefs.add(chef);
        //pravime return na noviot chef
        return chef;
    }

    @Override
    public Chef update(Long id, String firstName, String lastName, String bio, Gender gender) {
        //go barame chefot po ID
        Chef chef = findById(id);
        if (chef == null) throw new IllegalArgumentException("Chef not found");
        chef.setFirstName(firstName);
        chef.setLastName(lastName);
        chef.setBio(bio);
        chef.setGender(gender);
        return chefRepository.save(chef);
    }

    @Override
    public void delete(Long id) {

    }

//tuka ako sakame da go izbriseme
    @Override
    public void deleteById(Long id) {
        DataHolder.chefs.removeIf(c -> c.getId().equals(id));
    }
}