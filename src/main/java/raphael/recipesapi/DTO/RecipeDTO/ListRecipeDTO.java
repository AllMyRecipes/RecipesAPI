package raphael.recipesapi.DTO.RecipeDTO;

import raphael.recipesapi.entities.Category;
import raphael.recipesapi.entities.Recipe;

import java.util.List;

public class ListRecipeDTO {
    private Long id;
    private String name;
    private double time;
    private String picture;
    private List<Category> categories;

    public ListRecipeDTO(Recipe recipe){
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.time = recipe.getTime();
        this.picture = recipe.getPicture();
        this.categories = recipe.getCategories();
    }
}
