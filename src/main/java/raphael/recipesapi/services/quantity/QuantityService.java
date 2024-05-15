package raphael.recipesapi.services.quantity;

import raphael.recipesapi.entities.Quantity;

public interface QuantityService {
    public Quantity saveQuantity(Quantity quantity);
    public Quantity getQuantityById(Long id);
    public void deleteQuantity(Long id);
}
