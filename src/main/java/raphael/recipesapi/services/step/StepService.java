package raphael.recipesapi.services.step;

import raphael.recipesapi.entities.Step;

import java.util.List;

public interface StepService {
    public Step saveSteps(Step step);
    public void deleteStep(Long id);
    public List<Step> getStepByRecipeId(Long id);
    public void deleteStepByRecipeId(Long id);
}
