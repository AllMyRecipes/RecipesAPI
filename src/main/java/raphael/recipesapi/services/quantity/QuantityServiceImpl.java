package raphael.recipesapi.services.quantity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import raphael.recipesapi.entities.Quantity;
import raphael.recipesapi.repositories.QuantityRepository;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
public class QuantityServiceImpl implements QuantityService {
    private final QuantityRepository quantityRepository;
    public QuantityServiceImpl(QuantityRepository quantityRepository){
        this.quantityRepository = quantityRepository;
    }
    @Override
    public Quantity saveQuantity(Quantity quantity) {
        return quantityRepository.save(quantity);
    }




    @Override
    public List<Quantity> getQuantityByRecipeId(Long id) {
        return quantityRepository.findByRecipeId(id);

    }

    @Override
    public void deleteQuantity(Long id) {
        quantityRepository.deleteById(id);
    }
}
