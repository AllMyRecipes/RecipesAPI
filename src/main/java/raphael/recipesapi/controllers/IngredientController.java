package raphael.recipesapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.recipesapi.entities.Ingredient;
import raphael.recipesapi.exceptions.Exceptions;
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
            return ResponseEntity.ok().body("ingredient deleted");
        } catch ( Exceptions.CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("L'ingredient avec l'ID fourni n'existe pas");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de la suppression de l' ingredient");
        }

    }


}
