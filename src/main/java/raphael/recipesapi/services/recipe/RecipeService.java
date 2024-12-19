package raphael.recipesapi.services.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import raphael.recipesapi.entities.Category;
import raphael.recipesapi.entities.Recipe;

import java.util.List;

public interface RecipeService {
    public Page<Recipe> getAllRecipes(PageRequest pageRequest);
    public Recipe saveRecipe(Recipe newRecipe);
    public void deleteRecipe(Long id);
    public Recipe getRecipeById(Long id);
    public Page<Recipe> getRecipeByKeyWord(PageRequest page, String word);
    public Page <Recipe> getRecipeByCategory(PageRequest page,Long categoryId);
    public List<Recipe> homeRandomRecipes();
    public Page<Recipe> getRecipesByIngredientId(PageRequest pagerequest,Long ingredientId);


}
