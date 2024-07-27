package raphael.recipesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raphael.recipesapi.entities.Step;

import java.util.List;

@Repository
public interface StepRepository extends JpaRepository<Step, Long> {
    List<Step> findByRecipeId(Long id);

    void deleteByRecipeId(Long id);
}
