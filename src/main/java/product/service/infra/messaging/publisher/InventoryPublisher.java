package product.service.infra.messaging.publisher;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import product.service.domain.model.entity.Product;
import product.service.infra.config.RabbitMQConfig;
import product.service.infra.messaging.InventoryMessage;

@Service
public class InventoryPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void publishProductInventory(Product product, int quantity) {
        InventoryMessage message = InventoryMessage.builder()
                                                   .productId(product.getId())
                                                   .quantity(quantity)
                                                   .build();

        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.ROUTING_KEY, message);
    }

}
