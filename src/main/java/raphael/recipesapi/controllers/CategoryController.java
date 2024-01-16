package raphael.recipesapi.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.recipesapi.entities.Category;
import raphael.recipesapi.services.category.CategoryServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
@CrossOrigin("*")
public class CategoryController {
    private final CategoryServiceImpl categoryService;
    public CategoryController(CategoryServiceImpl categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> allCategories(){
        List<Category> allCategories = new ArrayList<>();
        try{
            allCategories = categoryService.getAllCategories();
        }
        catch (Exception e)
        {
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(allCategories);
    }
    @PostMapping("/categories")
    public ResponseEntity<Category> newCategory(@RequestBody Category category){
        return ResponseEntity.ok().body(categoryService.saveCategory(category));
    }

    @DeleteMapping("/category")
    public ResponseEntity<String> deleteCategory(@RequestParam("id") Long id){
        try{
            categoryService.deleteCategory(id);
        } catch (Exception e){
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body("Category deleted");
    }


}
