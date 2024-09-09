package raphael.recipesapi.category;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import raphael.recipesapi.entities.Category;
import raphael.recipesapi.repositories.CategoryRepository;
import raphael.recipesapi.services.category.CategoryServiceImpl;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class CategoryServiceTest {
    private Category category;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryServiceImpl categoryService;
    @BeforeEach
    public void initEntity(){
        category = new Category();
        category.setId(1L);
        category.setName("category test");
        category.setColor("f0000");
    }
    @Test
    public void save_category_test(){
        when(categoryService.saveCategory(category)).thenReturn(category);

        Category newCategory = categoryService.saveCategory(category);
        assertThat(newCategory.getId()).isNotNull();
        assertThat(newCategory.getName()).isEqualTo("category test");
        assertThat(newCategory.getColor()).isEqualTo("f0000");
    }
    @Test
    public void get_category_by_id_test(){
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));

        Category categoryById = categoryService.getCategory(category.getId());
        assertThat(categoryById).isNotNull();
        assertThat(categoryById.getName()).isEqualTo("category test");
        assertThat(categoryById.getColor()).isEqualTo("f0000");
    }
    @Test
    public void delete_category_test(){
        when(categoryRepository.findById(category.getId())).thenReturn(Optional.of(category));

        categoryService.deleteCategory(category.getId());
        verify(categoryRepository, times(1)).delete(category);
    }
    @Test
    public void get_all_category_test(){
        categoryService.getAllCategories();
        verify(categoryRepository, times(1)).findAll();
    }
}
