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
@RequestMapping("/api")
@CrossOrigin("*")
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
    @GetMapping("/quantity")
    public ResponseEntity<List<Quantity>> getQuantityByRecipeId(@RequestParam("id") Long recipeId){
        List<Quantity> quantities = null;
        try {
            quantities = quantityService.getQuantityByRecipeId(recipeId);
        } catch (Exception e){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(quantities);
    }

    @PostMapping("/quantity")
    public ResponseEntity<?> saveQuantity(@RequestBody Quantity qtity, @RequestParam("id") Long recipeId){

        Quantity newQuantity = new Quantity();
        Ingredient newIngredient;
        Quantity quantityResponse;

        try{
            newQuantity.setQuantity(qtity.getQuantity());
            newQuantity.setMeasure_unite(qtity.getMeasure_unite());
            newQuantity.setRecipe(recipeService.getRecipeById(recipeId));
            if (qtity.getIngredient().getId() == null){
                String name = qtity.getIngredient().getName().toLowerCase();
                Ingredient ingredientByName = ingredientService.getIngredientByName(name);
                if (ingredientByName == null){
                    newIngredient = ingredientService.saveIngredient(qtity.getIngredient());
                } else {
                    newIngredient = ingredientByName;
                }
            } else {
                newIngredient = ingredientService.getIngredientById(qtity.getIngredient().getId());
            }
            newQuantity.setIngredient(newIngredient);
            quantityResponse = quantityService.saveQuantity(newQuantity);
        } catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok().body(quantityResponse);
    }

    @DeleteMapping("/quantity")
    public ResponseEntity<String> deleteQuantity(@RequestParam("id") Long id){
        try {
            quantityService.deleteQuantity(id);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body("Quantity deleted");
    }
}
