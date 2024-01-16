package raphael.recipesapi.services.category;

import raphael.recipesapi.entities.Category;

import java.util.List;

public interface CategoryService {
    public Category saveCategory(Category category);
    public List<Category> getAllCategories();
    public Category getCategory(Long id);
    public void deleteCategory(Long id);
}
