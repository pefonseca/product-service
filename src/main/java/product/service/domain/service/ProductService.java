package product.service.domain.service;

import product.service.app.rest.dto.request.ProductRequestDTO;
import product.service.app.rest.dto.response.ProductDeleteResponseDTO;
import product.service.app.rest.dto.response.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO save(ProductRequestDTO request);
    ProductResponseDTO update(ProductRequestDTO request, Long id);
    ProductDeleteResponseDTO delete(Long id);
    ProductResponseDTO findById(Long id);
    List<ProductResponseDTO> findAll();

}
