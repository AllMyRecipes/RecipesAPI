package raphael.recipesapi.services.step;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raphael.recipesapi.entities.Recipe;
import raphael.recipesapi.entities.Step;
import raphael.recipesapi.repositories.StepRepository;
import raphael.recipesapi.services.recipe.RecipeServiceImpl;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class StepServiceImpl implements StepService {
    private final StepRepository stepRepository;
    public StepServiceImpl(StepRepository stepRepository){
        this.stepRepository = stepRepository;
    }
    public RecipeServiceImpl recipeService;
    @Override
    public Step saveSteps(Step step) {
        return stepRepository.save(step);
    }

    @Override
    public void deleteStep(Long id) {
    try {
        stepRepository.deleteById(id);
    } catch (Exception e) {
        log.info( e.getMessage());
    }
    }

    @Override
    public List<Step> getStepByRecipeId(Long id) {
        return stepRepository.findByRecipeId(id);
    }

    @Override
    public void deleteStepByRecipeId(Long id) {
        stepRepository.deleteByRecipeId(id);
    }
}
