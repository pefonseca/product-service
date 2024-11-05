package product.service.app.rest.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    private String name;
    private String description;
    private Double price;
    private Long categoryId;
    private Integer stockQuantity;
    private String imagesUrl;
    private Double averageRating;
    private Boolean isActive;

}
