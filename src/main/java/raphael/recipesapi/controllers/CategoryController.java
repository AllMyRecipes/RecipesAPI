package raphael.recipesapi.controllers;

import ch.qos.logback.core.encoder.EchoEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raphael.recipesapi.entities.Category;
import raphael.recipesapi.exceptions.Exceptions;
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
    @PostMapping("/category")
    public ResponseEntity<Category> newCategory(@RequestBody Category category){
        return ResponseEntity.ok().body(categoryService.saveCategory(category));
    }

    @DeleteMapping("/category")
    public ResponseEntity<String> deleteCategory(@RequestParam("id") Long id){
        try{
            categoryService.deleteCategory(id);
            return ResponseEntity.ok().body("Category deleted");
        } catch ( Exceptions.CategoryNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La catégorie avec l'ID fourni n'existe pas");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de la suppression de la catégorie");
        }

    }
    @PutMapping("/category")
    public ResponseEntity<Category> updateCategory(@RequestBody Category category){
        Category categoryToUpdate = categoryService.getCategory(category.getId());
        try{
            categoryToUpdate.setName(category.getName());
            categoryToUpdate.setColor(category.getColor());
            categoryService.saveCategory(categoryToUpdate);
        }catch (Exception e) {
            log.info(e.getMessage());
        }
        return ResponseEntity.ok().body(categoryToUpdate);
    }


}
