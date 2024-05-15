package raphael.recipesapi.services.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import raphael.recipesapi.entities.Category;
import raphael.recipesapi.entities.Recipe;
import raphael.recipesapi.repositories.RecipeRepository;

import java.util.List;
import java.util.Optional;

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

    @Override
    public void deleteRecipe(Long id){ recipeRepository.deleteById(id);}

    @Override
    public Recipe getRecipeById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        return recipe.orElse(null);
    }

    @Override
    public Page<Recipe> getRecipeByKeyWord(PageRequest page, String word) {
        return recipeRepository.findRecipeByNameContains(page, word);
    }

    @Override
    public Page<Recipe> getRecipeByCategory(PageRequest page, Long categoryId) {
        return recipeRepository.getRecipeByCategoriesId(page, categoryId);
    }

}
