package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Dish {
    private Long id;
    String dishId;
    String name;
    String cuisine;
    int preparationTime;

    private static Long counter = 0L;

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.id = ++counter;
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }

    public Dish(Long id, String dishId, String name, String cuisine, int preparationTime) {
        this.id = id;
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        if (id != null && id > counter) {
            counter = id;
        }
    }

}
