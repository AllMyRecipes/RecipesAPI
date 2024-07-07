package raphael.recipesapi.ingredient;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import raphael.recipesapi.entities.Ingredient;
import raphael.recipesapi.repositories.IngredientRepository;
import raphael.recipesapi.services.ingredient.IngredientServiceImpl;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class IngredientServiceTest {
    private Ingredient ingredient;
    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    private IngredientServiceImpl ingredientService;

    public void initEntity(){
        
    }
}
