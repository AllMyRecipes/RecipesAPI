package raphael.recipesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raphael.recipesapi.entities.Recipe;
@Repository
public interface RecipeRepository extends JpaRepository<Recipe,Long> {

}
