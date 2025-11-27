package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Chef {
    Long id;
    String firstName;
    String lastName;
    String bio;
    Gender gender; //dodavame enumeracijata
    List<Dish> dishes;

    public Chef(Long id, String firstName, String lastName, String bio, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.gender = gender; //go zacuvuvame tuka polot
        this.dishes = new ArrayList<>();
    }
}
