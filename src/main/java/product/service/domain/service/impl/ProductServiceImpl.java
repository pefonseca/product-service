package product.service.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import product.service.app.rest.dto.request.ProductRequestDTO;
import product.service.app.rest.dto.response.ProductDeleteResponseDTO;
import product.service.app.rest.dto.response.ProductResponseDTO;
import product.service.domain.model.entity.Category;
import product.service.domain.model.entity.Product;
import product.service.domain.service.ProductService;
import product.service.infra.repository.CategoryRepository;
import product.service.infra.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;

    /**
     * @param request
     * @return
     */
    @Override
    public ProductResponseDTO save(ProductRequestDTO request) {
        var productSaved = repository.save(buildToProduct(request));
        return productSaved.responseDTO();
    }

    /**
     * @param request
     * @return
     */
    @Override
    public ProductResponseDTO update(ProductRequestDTO request, Long id) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProductDeleteResponseDTO delete(Long id) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProductResponseDTO findById(Long id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<ProductResponseDTO> findAll() {
        return repository.findAll().stream().map(product -> ProductResponseDTO.builder()
                                                                              .id(product.getId())
                                                                              .name(product.getName())
                                                                              .description(product.getDescription())
                                                                              .price(product.getPrice())
                                                                              .category(product.getCategory().responseDTO())
                                                                              .stockQuantity(product.getStockQuantity())
                                                                              .imagesUrl(product.getImagesUrl())
                                                                              .createAt(product.getCreateAt())
                                                                              .updatedAt(product.getUpdatedAt())
                                                                              .averageRating(product.getAverageRating())
                                                                              .isActive(product.getIsActive())
                                                                              .build()).toList();
    }

    private Product buildToProduct(ProductRequestDTO requestDTO) {
        Category category = categoryRepository.findById(requestDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category Not Found"));

        return Product.builder()
                      .name(requestDTO.getName())
                      .description(requestDTO.getDescription())
                      .price(requestDTO.getPrice())
                      .category(category)
                      .stockQuantity(requestDTO.getStockQuantity())
                      .imagesUrl(requestDTO.getImagesUrl())
                      .averageRating(requestDTO.getAverageRating())
                      .isActive(requestDTO.getIsActive())
                      .createAt(LocalDateTime.now())
                      .updatedAt(LocalDateTime.now())
                      .build();
    }
}
