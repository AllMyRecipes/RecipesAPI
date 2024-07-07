package raphael.recipesapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.recipesapi.entities.Ingredient;
import raphael.recipesapi.entities.Quantity;
import raphael.recipesapi.services.category.CategoryServiceImpl;
import raphael.recipesapi.services.ingredient.IngredientServiceImpl;
import raphael.recipesapi.services.quantity.QuantityServiceImpl;
import raphael.recipesapi.services.recipe.RecipeServiceImpl;

import java.util.List;

@RestController
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api")
public class QuantityController {
    private final RecipeServiceImpl recipeService;
    private final CategoryServiceImpl categoryService;
    private final IngredientServiceImpl ingredientService;
    private final QuantityServiceImpl quantityService;
    public QuantityController (RecipeServiceImpl recipeService, CategoryServiceImpl categoryService, IngredientServiceImpl ingredientService, QuantityServiceImpl quantityService){
        this.recipeService = recipeService;
        this.categoryService = categoryService;
        this.ingredientService = ingredientService;
        this.quantityService = quantityService;
    }
    @GetMapping("/quantity/{recipeId}")
    public ResponseEntity<List<Quantity>> getQuantityByRecipeId(@PathVariable("recipeId") Long recipeId){
        List<Quantity> quantities = null;
        try {
            quantities = quantityService.getQuantityByRecipeId(recipeId);
        } catch (Exception e){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(quantities);
    }

    @PostMapping("/quantity/{recipeId}")
    public ResponseEntity<Quantity> saveQuantity(@RequestBody Quantity quantity, @PathVariable("recipeId") Long recipeId, @RequestBody Long ingredientId){
        Quantity newQuantity = new Quantity();
        try{

                newQuantity.setMeasure_unite(quantity.getMeasure_unite());
                newQuantity.setRecipe(recipeService.getRecipeById(recipeId));
                newQuantity.setIngredient(ingredientService.getIngredientById(ingredientId));
                newQuantity.setQuantity(newQuantity.getQuantity());
                newQuantity = quantityService.saveQuantity(newQuantity);

        } catch (Exception e ){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(newQuantity);
    }
    @DeleteMapping("/quantity")
    public ResponseEntity<String> deleteQuantity(@RequestParam("id") Long id){
        try {

        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Quantity deleted");
    }
}
