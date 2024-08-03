package raphael.recipesapi.services.ingredient;

import raphael.recipesapi.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    public List<Ingredient> getAllIngredients();
    public Ingredient saveIngredient(Ingredient ingredient);
    public Ingredient getIngredientById(Long id);
    public void deleteIngredient(Ingredient ingredient);
    public Ingredient getIngredientByName(String name);
}
