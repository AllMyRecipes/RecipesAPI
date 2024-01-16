package raphael.recipesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raphael.recipesapi.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
