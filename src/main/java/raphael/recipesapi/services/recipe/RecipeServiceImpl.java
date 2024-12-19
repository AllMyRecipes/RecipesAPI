package raphael.recipesapi.services.recipe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raphael.recipesapi.entities.Recipe;
import raphael.recipesapi.repositories.RecipeRepository;
import raphael.recipesapi.services.quantity.QuantityServiceImpl;
import raphael.recipesapi.services.step.StepServiceImpl;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final StepServiceImpl stepService;
    private final QuantityServiceImpl quantityService;

    public RecipeServiceImpl(RecipeRepository recipeRepository, StepServiceImpl stepService, QuantityServiceImpl quantityService){
        this.recipeRepository = recipeRepository;
        this.stepService = stepService;
        this.quantityService = quantityService;
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
    @Transactional
    public void deleteRecipe(Long id){
        stepService.deleteStepByRecipeId(id);
        quantityService.deleteQuantityByRecipeId(id);
        recipeRepository.deleteById(id);}

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

    @Override
    public List<Recipe> homeRandomRecipes() {
        return recipeRepository.homeRandomRecipes();
    }

    @Override
    public Page<Recipe> getRecipesByIngredientId(PageRequest page,Long ingredientId) {
        return recipeRepository.findRecipesByIngredientId(page ,ingredientId);
    }


}
