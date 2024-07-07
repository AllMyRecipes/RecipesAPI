package raphael.recipesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raphael.recipesapi.entities.Quantity;

import java.util.List;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {
    public List<Quantity> findByRecipeId(Long id);
}
