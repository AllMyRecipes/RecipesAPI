package raphael.recipesapi.services.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import raphael.recipesapi.entities.Recipe;
import raphael.recipesapi.repositories.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository){
        this.recipeRepository = recipeRepository;
    }
    @Override
    public Page<Recipe> getAllRecipes(PageRequest pageRequest) {
        return recipeRepository.findAll(pageRequest);
    }

    @Override
    public Recipe saveRecipe(Recipe newRecipe) {
        return recipeRepository.save(newRecipe);
    }
}
