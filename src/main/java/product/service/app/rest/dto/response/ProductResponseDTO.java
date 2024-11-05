package product.service.app.rest.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private CategoryResponseDTO category;
    private String imagesUrl;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private Double averageRating;
    private Boolean isActive;

}
