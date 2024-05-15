package raphael.recipesapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.recipesapi.entities.Ingredient;
import raphael.recipesapi.services.ingredient.IngredientServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin("*")
public class IngredientController {
    private final IngredientServiceImpl ingredientService;

    public IngredientController(IngredientServiceImpl ingredientService){
        this.ingredientService = ingredientService;
    }
    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> getAllIngredients(){
        return ResponseEntity.ok().body(ingredientService.getAllIngredients());
    }
    @GetMapping("/ingredient")
    public ResponseEntity<Ingredient> getIngredientById(@RequestParam("id") Long id) throws Exception {

        try{
            return ResponseEntity.ok().body(ingredientService.getIngredientById(id));
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
    @PostMapping("/ingredient")
    public ResponseEntity<Ingredient> newIngredient(@RequestBody  Ingredient ingredient){
        return ResponseEntity.ok().body(ingredientService.saveIngredient(ingredient));
    }
    @DeleteMapping("/ingredient")
    public ResponseEntity<String> deleteIngredient(@RequestParam("id")Long id){
        try {
            Ingredient ingredientToDelete = ingredientService.getIngredientById(id);
            ingredientService.deleteIngredient(ingredientToDelete);
        } catch (Exception e){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body("ingredient deleted");
    }


}
