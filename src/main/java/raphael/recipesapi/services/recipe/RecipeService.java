package raphael.recipesapi.services.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import raphael.recipesapi.entities.Recipe;

public interface RecipeService {
    public Page<Recipe> getAllRecipes(PageRequest pageRequest);
    public Recipe saveRecipe(Recipe newRecipe);
}
