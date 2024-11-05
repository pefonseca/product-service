package product.service.domain.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import product.service.app.rest.dto.response.ProductResponseDTO;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    private String imagesUrl;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    private Double averageRating;
    private Boolean isActive;

    public ProductResponseDTO responseDTO() {
        return ProductResponseDTO.builder()
                                 .id(this.id)
                                 .name(this.name)
                                 .description(this.description)
                                 .price(this.price)
                                 .category(this.category.responseDTO())
                                 .imagesUrl(this.imagesUrl)
                                 .createAt(this.createAt)
                                 .updatedAt(this.updatedAt)
                                 .averageRating(this.averageRating)
                                 .isActive(this.isActive)
                                 .build();
    }

}
