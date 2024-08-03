package raphael.recipesapi.services.ingredient;

import org.springframework.stereotype.Service;
import raphael.recipesapi.entities.Ingredient;
import raphael.recipesapi.repositories.IngredientRepository;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;
    public IngredientServiceImpl(IngredientRepository ingredientRepository){this.ingredientRepository = ingredientRepository;}
    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        String name = ingredient.getName().toLowerCase();
        ingredient.setName(name);
        return ingredientRepository.save(ingredient);
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        Optional<Ingredient> ingredient = ingredientRepository.findById(id);
        if(ingredient.isPresent()){
            return ingredient.get();
        } else {
            throw  new NoSuchElementException("Ingredient with Id:" + id + "Not found");
        }
    }

    @Override
    public void deleteIngredient(Ingredient ingredient) {
         ingredientRepository.delete(ingredient);
    }

    @Override
    public Ingredient getIngredientByName(String name) {
        return ingredientRepository.findByName(name);
    }
}
