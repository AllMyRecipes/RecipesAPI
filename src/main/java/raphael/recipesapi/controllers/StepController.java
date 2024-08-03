package raphael.recipesapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.recipesapi.entities.Recipe;
import raphael.recipesapi.entities.Step;
import raphael.recipesapi.services.recipe.RecipeServiceImpl;
import raphael.recipesapi.services.step.StepServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin("*")
public class StepController {
    private final StepServiceImpl stepService;
    private final RecipeServiceImpl recipeService;

    public StepController(StepServiceImpl stepService, RecipeServiceImpl recipeService){
        this.stepService = stepService;
        this.recipeService = recipeService;
    }
    @GetMapping("/step")
    public ResponseEntity<List<Step>> getStepsByRecipeId(@RequestParam("id") Long recipeId){
        List<Step> steps = stepService.getStepByRecipeId(recipeId);
        return ResponseEntity.ok().body(steps);
    }
    @PostMapping("/step")
    public ResponseEntity<Step> newSteps(@RequestBody Step step, @RequestParam("id") Long recipeId){
        Step newStepSaved = null;
        try {
            Recipe recipe = recipeService.getRecipeById(recipeId);
            Step newStep = new Step(step.getTitle(), step.getDescription(),recipe);
            newStepSaved = stepService.saveSteps(newStep);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(newStepSaved);
    }
    @DeleteMapping("/step")
    public ResponseEntity<String> deleteStep(@RequestParam("id") Long id){
        try{
            stepService.deleteStep(id);
            return ResponseEntity.ok().body("Step deleted");
        } catch (Exception e){
            return ResponseEntity.status(500).body("An error occurred while deleting the step"+ e.getMessage());
        }
    }
    @PutMapping("/step")
    public ResponseEntity<Step> updateStep(@RequestBody Step step){
        Step stepUpdated = null;
        try {
            Step stepToUpdate = stepService.getStepById(step.getId());
            stepUpdated = stepService.updateStep(step);
        } catch (Exception e){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(stepUpdated);
    }
}
