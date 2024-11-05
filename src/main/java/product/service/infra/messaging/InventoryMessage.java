package product.service.infra.messaging;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long productId;
    private int quantity;

}
