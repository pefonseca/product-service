package product.service.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.service.domain.model.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
