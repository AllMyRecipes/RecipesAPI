package raphael.recipesapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color = "#FFFFFF";
//    @JsonIgnore
//    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
//    private List<Recipe> recipes;
    @JsonIgnore
    @ManyToMany(mappedBy = "categories")
    private List<Recipe> recipes = new ArrayList<>();

}
