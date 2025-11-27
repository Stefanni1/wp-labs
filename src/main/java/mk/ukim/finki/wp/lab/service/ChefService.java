package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Gender;

import java.util.List;

public interface ChefService {
    List<Chef> listChefs();
    Chef findById(Long id);
    Chef addDishToChef(Long chefId, String dishId);

    //dodavame novi crud metodi
    Chef create(String firstName, String lastName, String bio, Gender gender);
    Chef update(Long id, String firstName, String lastName, String bio, Gender gender);
    void delete(Long id);
    void deleteById(Long id);
}