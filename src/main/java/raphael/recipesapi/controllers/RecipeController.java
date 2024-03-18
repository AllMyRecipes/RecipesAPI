package raphael.recipesapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.recipesapi.entities.Category;
import raphael.recipesapi.entities.Recipe;
import raphael.recipesapi.services.category.CategoryServiceImpl;
import raphael.recipesapi.services.recipe.RecipeServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin("*")
public class RecipeController {
    private final RecipeServiceImpl recipeService;
    private final CategoryServiceImpl categoryService;
    public RecipeController (RecipeServiceImpl recipeService, CategoryServiceImpl categoryService){
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }
    @GetMapping("/recipes")
    public ResponseEntity<Page<Recipe>> allRecipes(@RequestParam("page") int page){
        Page<Recipe> listRecipes = null;
    try {
        PageRequest pageRequest = PageRequest.of(page, 5);
        listRecipes = recipeService.getAllRecipes(pageRequest);
    }catch (Exception e){
        log.info(e.getMessage());
    }
    return ResponseEntity.ok().body(listRecipes);
    }

    @PostMapping("/recipe")
    public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe recipe){
        Recipe recipeToSave = new Recipe();
        try {
            recipeToSave.setName(recipe.getName());
            recipeToSave.setTime(recipe.getTime());
            if (recipe.getCategories() != null){
                List<Category>categories = new ArrayList<>();
                for (Category category : recipe.getCategories()){
                    Category categoryToAdd = categoryService.getCategory(category.getId());
                    categories.add(categoryToAdd);
                }

                recipeToSave.setCategories(categories);
            }
            recipeService.saveRecipe(recipeToSave);
        } catch (Exception e){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(recipeToSave);
    }

}
