package raphael.recipesapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import raphael.recipesapi.entities.Quantity;
import raphael.recipesapi.entities.Recipe;

import java.util.List;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {
    public List<Quantity> findByRecipeId(Long id);

    void deleteByRecipeId(Long id);

}
