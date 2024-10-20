package raphael.recipesapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import raphael.recipesapi.entities.Recipe;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    public Page<Recipe> findRecipeByNameContains(Pageable page,String word);
    public Page<Recipe> getRecipeByCategoriesId(Pageable page, Long id);
    @Query(value = "SELECT * FROM recipe ORDER BY RAND() LIMIT 4", nativeQuery = true)
    public List<Recipe> homeRandomRecipes();
}
