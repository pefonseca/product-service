package product.service.domain.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import product.service.app.rest.dto.request.ProductRequestDTO;
import product.service.app.rest.dto.response.ProductDeleteResponseDTO;
import product.service.app.rest.dto.response.ProductResponseDTO;
import product.service.domain.model.entity.Category;
import product.service.domain.model.entity.Product;
import product.service.domain.service.ProductService;
import product.service.infra.messaging.publisher.InventoryPublisher;
import product.service.infra.repository.CategoryRepository;
import product.service.infra.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final CategoryRepository categoryRepository;
    private final InventoryPublisher inventoryPublisher;

    /**
     * @param request
     * @return
     */
    @Override
    public ProductResponseDTO save(ProductRequestDTO request) {
        Product productSaved = repository.save(buildToProduct(request));

        inventoryPublisher.publishProductInventory(productSaved, request.getStockQuantity());

        return productSaved.responseDTO();
    }

    /**
     * @param productRequestDTO
     * @return
     */
    @Override
    public ProductResponseDTO update(ProductRequestDTO productRequestDTO, Long id) {
        Product productDB = repository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));

        buildProductToUpdate(productDB, productRequestDTO, id);

        var savedProduct = repository.save(productDB);

        inventoryPublisher.publishProductInventory(savedProduct, productRequestDTO.getStockQuantity());

        return savedProduct.responseDTO();
    }

    private void buildProductToUpdate(Product productDB, ProductRequestDTO productRequestDTO, Long id) {

        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found."));

        productDB.setId(id);
        productDB.setName(productRequestDTO.getName());
        productDB.setDescription(productRequestDTO.getDescription());
        productDB.setPrice(productRequestDTO.getPrice());
        productDB.setCategory(category);
        productDB.setImagesUrl(productRequestDTO.getImagesUrl());
        productDB.setCreateAt(productDB.getCreateAt());
        productDB.setUpdatedAt(LocalDateTime.now());
        productDB.setAverageRating(productRequestDTO.getAverageRating());
        productDB.setIsActive(productRequestDTO.getIsActive());

    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProductDeleteResponseDTO delete(Long id) {
        ProductDeleteResponseDTO productDeleteResponseDTO = new ProductDeleteResponseDTO();

        if(repository.findById(id).isPresent()) {
            repository.deleteById(id);
            productDeleteResponseDTO.setStatus("Produto Deletado");
        }

        return productDeleteResponseDTO;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ProductResponseDTO findById(Long id) {
        Product productDB = repository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found"));

        return productDB.responseDTO();
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
                      .imagesUrl(requestDTO.getImagesUrl())
                      .averageRating(requestDTO.getAverageRating())
                      .isActive(requestDTO.getIsActive())
                      .createAt(LocalDateTime.now())
                      .updatedAt(LocalDateTime.now())
                      .build();
    }
}
