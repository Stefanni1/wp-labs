package mk.ukim.finki.wp.lab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "chef")
public class Chef {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    private String bio;
    
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @OneToMany(mappedBy = "chef", fetch = FetchType.EAGER)
    private List<Dish> dishes = new ArrayList<>();

    public Chef(Long id, String firstName, String lastName, String bio, Gender gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.gender = gender;
        this.dishes = new ArrayList<>();
    }
}
