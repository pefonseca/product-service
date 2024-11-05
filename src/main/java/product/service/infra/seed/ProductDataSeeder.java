package product.service.infra.seed;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import product.service.domain.model.entity.Category;
import product.service.domain.model.entity.Product;
import product.service.infra.repository.CategoryRepository;
import product.service.infra.repository.ProductRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ProductDataSeeder {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @PostConstruct
    public void seedData() {
        if (productRepository.count() == 0 && categoryRepository.count() == 0) {
            Category categoriaEletronicos = Category.builder()
                                                    .name("Eletrônicos")
                                                    .description("Dispositivos e gadgets")
                                                    .build();

            Category categoriaModa = Category.builder()
                                             .name("Moda")
                                             .description("Roupas e acessórios")
                                             .build();

            categoryRepository.save(categoriaEletronicos);
            categoryRepository.save(categoriaModa);

            Product produto1 = Product.builder()
                                      .name("Smartphone XYZ")
                                      .description("Um smartphone de alta qualidade com recursos avançados")
                                      .price(999.99)
                                      .category(categoriaEletronicos)
                                      .imagesUrl("http://exemplo.com/smartphone.jpg")
                                      .createAt(LocalDateTime.now())
                                      .updatedAt(LocalDateTime.now())
                                      .averageRating(4.5)
                                      .isActive(true)
                                      .build();

            Product produto2 = Product.builder()
                                      .name("Camiseta Designer")
                                      .description("Camiseta estilosa e confortável")
                                      .price(49.99)
                                      .category(categoriaModa)
                                      .imagesUrl("http://exemplo.com/camiseta.jpg")
                                      .createAt(LocalDateTime.now())
                                      .updatedAt(LocalDateTime.now())
                                      .averageRating(4.0)
                                      .isActive(true)
                                      .build();

            productRepository.save(produto1);
            productRepository.save(produto2);
        }
    }
}
