package raphael.recipesapi.ingredient;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import raphael.recipesapi.entities.Ingredient;
import raphael.recipesapi.repositories.IngredientRepository;
import raphael.recipesapi.services.ingredient.IngredientServiceImpl;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class IngredientServiceTest {
    private Ingredient ingredient;
    @Mock
    private IngredientRepository ingredientRepository;
    @InjectMocks
    private IngredientServiceImpl ingredientService;
    @BeforeEach
    public void initEntity(){
        ingredient = new Ingredient();
        ingredient.setId(1L);
        ingredient.setName("ingredient name");
    }
    @Test
    public void get_all_ingredients(){
        ingredientService.getAllIngredients();
        verify(ingredientRepository, times(1)).findAll();

    }
    @Test
    public void get_ingredient_by_id(){
        when(ingredientRepository.findById(ingredient.getId())).thenReturn(Optional.of(ingredient));
        Ingredient ingredient = ingredientService.getIngredientById(1L);
        assertThat(ingredient).isNotNull();
        assertThat(ingredient.getName()).isEqualTo("ingredient name");
    }
    @Test
    public void save_ingredient(){
        when(ingredientService.saveIngredient(ingredient)).thenReturn(ingredient);
        Ingredient newIngredient = ingredientService.saveIngredient(ingredient);
        assertThat(newIngredient.getId()).isNotNull();
        assertThat(newIngredient.getName()).isEqualTo("ingredient name");
    }
    @Test
    public void delete_category(){
        when(ingredientRepository.findById(ingredient.getId())).thenReturn(Optional.of(ingredient));
        ingredientService.deleteIngredient(ingredient);
        verify(ingredientRepository, times(1)).delete(ingredient);
    }
}
