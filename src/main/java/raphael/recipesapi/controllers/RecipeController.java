package raphael.recipesapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.recipesapi.entities.Category;
import raphael.recipesapi.entities.Ingredient;
import raphael.recipesapi.entities.Quantity;
import raphael.recipesapi.entities.Recipe;
import raphael.recipesapi.services.category.CategoryServiceImpl;
import raphael.recipesapi.services.ingredient.IngredientServiceImpl;
import raphael.recipesapi.services.quantity.QuantityService;
import raphael.recipesapi.services.quantity.QuantityServiceImpl;
import raphael.recipesapi.services.recipe.RecipeServiceImpl;
import raphael.recipesapi.services.step.StepServiceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin("*")
public class RecipeController {
    private final RecipeServiceImpl recipeService;
    private final CategoryServiceImpl categoryService;
    private final IngredientServiceImpl ingredientService;
    private final QuantityServiceImpl quantityService;
    private final StepServiceImpl stepService;
    public RecipeController (RecipeServiceImpl recipeService, CategoryServiceImpl categoryService, IngredientServiceImpl ingredientService, QuantityServiceImpl quantityService, StepServiceImpl stepService){
        this.recipeService = recipeService;
        this.categoryService = categoryService;
        this.ingredientService = ingredientService;
        this.quantityService = quantityService;
        this.stepService = stepService;
    }
    @GetMapping("/recipes")
    public ResponseEntity<Page<Recipe>> allRecipes(@RequestParam("page") int page){
        Page<Recipe> listRecipes = null;
    try {
        PageRequest pageRequest = PageRequest.of(page, 10);
        listRecipes = recipeService.getAllRecipes(pageRequest);
    }catch (Exception e){
        log.info(e.getMessage());
    }
    return ResponseEntity.ok().body(listRecipes);
    }

    @GetMapping("/recipe/{word}")
    public ResponseEntity<Page<Recipe>> recipeByKeyWord(@PathVariable("word") String word, @RequestParam("page") int page){
        Page<Recipe> listRecipes = null;
        try {
            PageRequest pageRequest = PageRequest.of(page, 5);
            listRecipes = recipeService.getRecipeByKeyWord(pageRequest, word);
        } catch (Exception e){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(listRecipes);
    }
    @GetMapping("/recipe-by-category")
    public ResponseEntity<Page<Recipe>> recipeByCategory(@RequestParam("categoryId")Long categoryId, @RequestParam("page")int page){
        Page<Recipe> listRecipes = null;
        try {
            PageRequest pageRequest= PageRequest.of(page, 5);
            listRecipes = recipeService.getRecipeByCategory(pageRequest, categoryId);
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
            if(recipe.getPicture() == null) {
                recipeToSave.setPicture("default.png");
            } else {
                recipeToSave.setPicture(recipe.getPicture());
            }
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

    @GetMapping("/recipe")
    public ResponseEntity<Recipe> getRecipeById(@RequestParam("id")Long id){
        Recipe recipe = new Recipe();
        try{
            recipe = recipeService.getRecipeById(id);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(recipe);
    }
    @DeleteMapping("/recipe")
    public ResponseEntity<String> deleteRecipe(@RequestParam("id")Long id){
        try{
            recipeService.deleteRecipe(id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Recipe deleted");
    }
    @PutMapping("/recipe")
    public ResponseEntity<Recipe> updateRecipe(@RequestBody Recipe recipe){
        Recipe recipeUpdated = new Recipe();
        try {
            Recipe recipeToUpdate = recipeService.getRecipeById(recipe.getId());
            recipeToUpdate.setName(recipe.getName());
            recipeToUpdate.setTime(recipe.getTime());
            recipeToUpdate.setPicture(recipe.getPicture());
            if (recipe.getCategories() != null){
                List<Category> categories = new ArrayList<>();
                for (Category category : recipe.getCategories()){
                    Category categoryToAdd = categoryService.getCategory(category.getId());
                    categories.add(categoryToAdd);
                }
                recipeToUpdate.setCategories(categories);
            }
            recipeUpdated = recipeService.saveRecipe(recipeToUpdate);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(recipeUpdated);
    }

    @GetMapping(value = "/recipe/picture/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<?>getPicture(@PathVariable("id")Long id) throws IOException {
        byte[] picture = null;
        try{
            Recipe recipe = recipeService.getRecipeById(id);
            picture = Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/pictures/"+recipe.getPicture()));
        } catch (Exception e){
            log.error( e.getMessage());
            return ResponseEntity.internalServerError().body(e.getCause());

        }

        return ResponseEntity.ok().body(picture);
    }

    @GetMapping("/recipe/home")
    public ResponseEntity<List<Recipe>> getHomeRecipes(){
        List<Recipe> recipes = null;
        try {
            recipes = recipeService.homeRandomRecipes();
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(recipes);
    }



}
