package raphael.recipesapi.services.category;


import org.springframework.stereotype.Service;
import raphael.recipesapi.entities.Category;
import raphael.recipesapi.repositories.CategoryRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category.get();
        }else{
            throw new NoSuchElementException("Category with Id:" + id + "Not found");
        }

    }

    @Override
    public void deleteCategory(Long id){
        Category category = getCategory(id);
        categoryRepository.delete(category);
    }

}
