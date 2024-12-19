package raphael.recipesapi.services.quantity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import raphael.recipesapi.entities.Quantity;
import raphael.recipesapi.entities.Recipe;

import java.util.List;

public interface QuantityService {
    public Quantity saveQuantity(Quantity quantity);
    public List<Quantity> getQuantityByRecipeId(Long id);
    public void deleteQuantity(Long id);
    public void deleteQuantityByRecipeId(Long id);
}
