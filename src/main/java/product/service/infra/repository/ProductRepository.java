package product.service.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.service.domain.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
