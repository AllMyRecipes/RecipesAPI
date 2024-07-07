package raphael.recipesapi.services.quantity;

import raphael.recipesapi.entities.Quantity;

import java.util.List;

public interface QuantityService {
    public Quantity saveQuantity(Quantity quantity);
    public List<Quantity> getQuantityByRecipeId(Long id);
    public void deleteQuantity(Long id);
}
