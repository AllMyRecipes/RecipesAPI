package raphael.recipesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raphael.recipesapi.entities.Quantity;

public interface QuantityRepository extends JpaRepository<Quantity, Long> {
}
